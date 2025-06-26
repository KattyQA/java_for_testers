package tests;
import model.Contact;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ContactModificationTest extends TestBase {


  @Test
  public void canModifyContact() {
    if (!app.contacts().isContactPresent()) {
      app.contacts().createContact(new Contact("", "name", "last", "Perm", "s@rty.ru", "2342563654", ""));
    }
    var oldContacts = app.contacts().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    var testData = new Contact().withFirstName("modified name");
    app.contacts().modifyContact(oldContacts.get(index), testData);
    var newContacts = app.contacts().getList();
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
