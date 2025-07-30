package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expectedPhones = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expectedPhones, phones);


    }

    @Test
    void testAddress(){
        var contacts = app.hbm().getContactList();
        var expectedAddress = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !s.trim().isEmpty())
                        .collect(Collectors.joining("\n"))
                        .trim()
        ));
        var address = app.contacts().getAddress();
        Assertions.assertEquals(expectedAddress, address);
    }

    @Test
    void testEmails(){
        var contacts = app.hbm().getContactList();
        var expectedEmails = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !s.trim().isEmpty())
                        .collect(Collectors.joining("\n"))
                        .trim()
        ));
        var emails = app.contacts().getEmails();
        Assertions.assertEquals(expectedEmails, emails);
    }

    @Test
    void testInfo(){
        var contacts = app.hbm().getContactList();
        var expectedPhones = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expectedPhones, phones);
        var expectedAddress = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !s.trim().isEmpty())
                        .collect(Collectors.joining("\n"))
                        .trim()
        ));
        var address = app.contacts().getAddress();
        Assertions.assertEquals(expectedAddress, address);
        var expectedEmails = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !s.trim().isEmpty())
                        .collect(Collectors.joining("\n"))
                        .trim()
        ));
        var emails = app.contacts().getEmails();
        Assertions.assertEquals(expectedEmails, emails);
    }

}
