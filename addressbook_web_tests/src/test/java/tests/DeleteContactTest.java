package tests;
import model.Contact;
import org.junit.jupiter.api.Test;

public class DeleteContactTest extends TestBase {

  @Test
  public void deleteContactTest() {
    if (app.contacts().isContactPresent()) {
      app.contacts().createContact(new Contact("name", "last", "Perm", "s@rty.ru", "2342563654"));
    }
    app.contacts().deleteContact();
  }
}
