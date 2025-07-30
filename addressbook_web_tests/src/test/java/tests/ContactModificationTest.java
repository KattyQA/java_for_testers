package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {


  @Test
  public void canModifyContact() {
    if (app.hbm().getContactCount() == 0) {
      app.contacts().createContact(new Contact("", "name", "last", "Perm", "s@rty.ru", "2342563654", "", "", "", "", "", ""));
    }
    var oldContacts = app.hbm().getContactList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    var testData = new Contact().withFirstName("modified name");
    app.contacts().modifyContact(oldContacts.get(index), testData);
    var newContacts = app.hbm().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.set(index, testData.withId(oldContacts.get(index).id()));
    Comparator<Contact> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);



  }


}
