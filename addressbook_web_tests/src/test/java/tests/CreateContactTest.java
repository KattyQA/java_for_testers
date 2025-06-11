package tests;

import model.Contact;
import org.junit.jupiter.api.Test;
public class CreateContactTest extends TestBase {

  @Test
  public void canCreateContactTest() {
    app.contacts().createContact(new Contact("first", "middle", "last"));

  }


}
