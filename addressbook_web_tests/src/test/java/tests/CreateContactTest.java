package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreateContactTest extends TestBase {

  public static List<Contact> contactProvider() {
    var result = new ArrayList<Contact>();
    for (int i = 0; i < 5; i++){
      result.add(new Contact()
              .withFirstName(randomString(i))
              .withLastName(randomString(i))
              .withEmail(randomString(i))
              .withAddress(randomString(i))
              .withHomePhone(randomString(i)));

    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateContactTest(Contact contact) {
    var oldContacts = app.contacts().getList();
    System.out.println(oldContacts);
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getList();
    System.out.println(newContacts);
    Comparator<Contact> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withEmail("").withAddress("").withHomePhone(""));
    expectedList.sort(compareById);
    System.out.println(expectedList);
    Assertions.assertEquals(newContacts, expectedList);

  }

  @Test
  public void test(){
    var con = app.contacts().getList();
    System.out.println(con);
  }


}
