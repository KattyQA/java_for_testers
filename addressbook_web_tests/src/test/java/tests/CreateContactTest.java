package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;

public class CreateContactTest extends TestBase {

  public static List<Contact> contactProvider () throws IOException {
    var result = new ArrayList<Contact>();
   /* var json = "";
    try (var reader = new FileReader("contacts.xml");
         var breader = new BufferedReader(reader)
    ) {
      var line = breader.readLine();
      while (line != null){
        json = json + line;
        line = breader.readLine();
      }
    }*/

    ObjectMapper mapper = new XmlMapper();
    var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<Contact>>(){});
    result.addAll(value);
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateContactTest(Contact contact) {
    var oldContacts = app.contacts().getList();
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
  public void canCreateContactFile() {
    var contact = new Contact()
            .withFirstName(CommonFunctions.randomString(10))
            .withLastName(CommonFunctions.randomString(10))
            .withPhoto(randomFile("src/test/resources/images"));
    app.contacts().createContact(contact);
  }


}
