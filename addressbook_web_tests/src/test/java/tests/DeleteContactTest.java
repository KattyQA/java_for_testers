package tests;
import io.qameta.allure.Allure;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeleteContactTest extends TestBase {

  @Test
  public void deleteContactTest() {
    Allure.step("Checking precondition", step -> {
      if (app.hbm().getContactCount() == 0) {
        app.contacts().createContact(new Contact("", "name", "last", "Perm", "s@rty.ru", "2342563654", "", "", "", "", "", ""));
      }
    });
    var oldContacts = app.hbm().getContactList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().deleteContact(oldContacts.get(index));
    var newContacts = app.hbm().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Allure.step("Validating contact deletion", step -> {
      Assertions.assertEquals(newContacts, expectedList);
    });

  }
}
