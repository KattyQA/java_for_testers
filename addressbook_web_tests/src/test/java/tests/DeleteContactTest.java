package tests;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeleteContactTest extends TestBase {

  @Test
  public void deleteContactTest() {
    if (app.contacts().isContactPresent()) {
      app.contacts().createContact(new Contact("", "name", "last", "Perm", "s@rty.ru", "2342563654"));
    }
    var oldContacts = app.contacts().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().deleteContact(oldContacts.get(index));
    var newContacts = app.contacts().getList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);

  }
}
