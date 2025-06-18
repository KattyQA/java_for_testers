package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class CreateContactTest extends TestBase {

  public static List<Contact> contactProvider() {
    var result = new ArrayList<Contact>();
    for (int i = 0; i < 5; i++){
      result.add(new Contact(randomString(i), randomString(i), randomString(i), randomString(i), randomString(i)));

    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateContactTest(Contact contact) {
    int contactCount = app.contacts().getCountContact();

    app.contacts().createContact(contact);

    int newContactCount = app.contacts().getCountContact();
    Assertions.assertEquals(contactCount, newContactCount);

  }


}
