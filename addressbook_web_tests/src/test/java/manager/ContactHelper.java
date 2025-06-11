package manager;

import model.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        click(By.linkText("home page"));
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



}
