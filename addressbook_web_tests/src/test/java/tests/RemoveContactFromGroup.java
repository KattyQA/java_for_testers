package tests;

import io.qameta.allure.Allure;
import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveContactFromGroup extends TestBase {
    @Test
    public void canRemoveContactFromGroup() {
        Allure.step("Checking preconditions", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContact(new Contact("", "name", "last", "Perm", "test@mail.ru", "1234567890", "", "", "", "", "", ""));
            }
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new Group("", "new", "header", "footer"));
            }
        });
        var group = app.hbm().getGroupList().get(0);
        var contactsInGroup = app.hbm().getContactsInGroup(group);
        if (contactsInGroup.isEmpty()) {
            var contact = app.hbm().getContactList().get(0);
            app.contacts().addContactToGroup(contact);
            contactsInGroup = app.hbm().getContactsInGroup(group);
        }
        var contactToRemove = contactsInGroup.get(0);
        app.contacts().removeContactFromGroup(group, contactToRemove);
        var contactsAfterRemove = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(contactsInGroup.size() - 1, contactsAfterRemove.size());
    }
}
