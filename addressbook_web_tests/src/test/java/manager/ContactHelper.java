package manager;

import model.Contact;
import model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs){
            List<WebElement> cells = tr.findElements(By.tagName("td"));
            var lastName = cells.get(1).getText();
            var firstName = cells.get(2).getText();
            var id = tr.findElement(By.cssSelector("input[type='checkbox']")).getAttribute("id");
            contacts.add(new Contact()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)

            );
        }
        return contacts;
    }



}
