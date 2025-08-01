package manager;

import model.Contact;
import model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

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

    public void createContact(Contact contact, Group group) {
        openContactsPage();
        fillContactForm(contact);
        selectGroup(group);
        saveNewContact();
        returnToHomePage();

    }

    private void selectGroup(Group group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void modifyContact(Contact contact, Contact modifiedContact) {
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    public void submitContactModification() {
        manager.driver.findElement(By.cssSelector("input:nth-child(74)")).click();
    }

    public void deleteContact(Contact contact){
        selectContact(contact);
        deleteSelectedContact();
        manager.handleAlertIfPresent();

    }

    public void initContactModification(Contact contact){
        manager.driver.findElement(By.id(contact.id())).findElement(By.xpath("../../td[8]/a/img")).click();
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
        if (!contact.photo().isEmpty()) {
            attach(By.name("photo"), contact.photo());
        }
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void selectContact(Contact contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));;
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

    public void addContactToGroup(Contact contact) {
        selectContact(contact);
        addContact();
    }

    private void addContact() {
        click(By.name("add"));
    }

    public void removeContactFromGroup(Group group, Contact contact) {

        selectContactGroup(group);
        selectContact(contact);
        removeContact();
    }

    private void removeContact() {
        click(By.name("remove"));
    }

    private void selectContactGroup(Group group) {
        var wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        var selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("group")));
        var select = new Select(selectElement);
        select.selectByValue(group.id());

    }

    public String getPhones(Contact contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id = '%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }

    public Map<String, String> getInfo() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;
    }
}
