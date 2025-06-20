package manager;

import model.Contact;
import model.Group;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager){
        super(manager);

    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.name("new")))
        click(By.linkText("add new"));
    }


    public void createContact(Contact contact) {
        openContactsPage();
        fillContactForm(contact);
        saveNewContact();
        returnToHomePage();

    }

    public void deleteContact(){
        selectContact();
        deleteSelectedContact();
        manager.handleAlertIfPresent();

    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void saveNewContact() {
        click(By.cssSelector("input:nth-child(75)"));
    }

    private void fillContactForm(Contact contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("home"), contact.homePhone());
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void deleteSelectedContact(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public int getCountContact(){
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<Contact> getList() {
        returnToHomePage();
        var contacts = new ArrayList<Contact>();
        var trs = manager.driver.findElements(By.cssSelector("tr.odd[name='entry']"));
        for (var tr : trs){
            var firstName = tr.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            var lastname = tr.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new Contact()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastname)

            );
        }
        return contacts;
    }



}
