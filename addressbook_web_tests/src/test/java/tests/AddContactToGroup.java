package tests;

import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AddContactToGroup extends TestBase{

    @Test
    public void canAddContactToGroup(){
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new Contact("", "name", "last", "Perm", "s@rty.ru", "2342563654", "", "", "", "", "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new Group("", "new", "new", "new"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldContactsInGroup = app.hbm().getContactsInGroup(group);
        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        app.contacts().addContactToGroup(new Contact().withId(contacts.get(index).id()));
        var newContactsInGroup = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldContactsInGroup.size() + 1, newContactsInGroup.size());

    }

    @Test
    public void testAddContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new Contact("", "name", "last", "Perm", "s@rty.ru", "2342563654", "", "", "", "", "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new Group("", "new", "new", "new"));
        }

        var group = app.hbm().getGroupList().get(0);
        int groupId = group.getId();
        var contactIds = app.hbm().getContactIds();
        var oldContactsInGroup = app.hbm().getContactsInGroup(group);
        System.out.println(oldContactsInGroup);
        boolean contactAdded = false;


        for (int contactId : contactIds) {
            if (!app.jdbc().isInGroup(contactId, groupId)) {
                app.contacts().addContactToGroup(new Contact().withId(String.valueOf(contactId)));
                contactAdded = true;
                break;
            }
        }


        if (!contactAdded) {
            Contact newContact = new Contact("", "newName", "newLast", "Perm", "new@email.ru", "987654321", "", "", "", "", "", "");
            app.contacts().createContact(newContact);
            app.contacts().addContactToGroup(newContact);
        }

        var newContactsInGroup = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldContactsInGroup.size() + 1, newContactsInGroup.size());
    }

}
