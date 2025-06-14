package manager;

import model.Group;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager){
        super(manager);

    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))){
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(Group group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void deleteGroup() {
        openGroupsPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(Group modifiedGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }



    private void initGroupCreation() {
        click(By.name("new"));
    }



    private void removeSelectedGroup() {
        click(By.name("delete"));
    }



    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));

    }

    private void fillGroupForm(Group group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());

    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

}
