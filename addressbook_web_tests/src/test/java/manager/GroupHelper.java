package manager;

import io.qameta.allure.Step;
import model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);

    }
    @Step
    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    @Step
    public void createGroup(Group group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }
    @Step
    public void deleteGroup(Group group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }
    @Step
    public void modifyGroup(Group group,Group modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }
    @Step
    private void submitGroupCreation() {
        click(By.name("submit"));
    }
    @Step
    private void initGroupCreation() {
        click(By.name("new"));
    }
    @Step
    private void removeSelectedGroups() {
        click(By.name("delete"));
    }
    @Step
    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }
    @Step
    private void submitGroupModification() {
        click(By.name("update"));

    }
    @Step
    private void fillGroupForm(Group group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());

    }
    @Step
    private void initGroupModification() {
        click(By.name("edit"));
    }
    @Step
    private void selectGroup(Group group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public int getCount() {
        openGroupsPage();
         return manager.driver.findElements(By.name("selected[]")).size();
    }
    @Step
    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }
    @Step
    private void selectAllGroups() {
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }
    @Step
    public List<Group> getList() {
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    return new Group().withId(id).withName(name);
                })
                .collect(Collectors.toList());

    }

    public void removeContactFromGroup() {
        openGroupsPage();

    }
}
