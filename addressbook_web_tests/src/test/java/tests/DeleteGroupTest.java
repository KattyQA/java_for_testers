package tests;

import io.qameta.allure.Allure;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeleteGroupTest extends TestBase {

    @Test
    public void CanDeleteGroupTest() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new Group("", "new", "new", "new"));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validating group deletion", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });

    }

    @Test
    void canDeleteAllGroupsAtOnce(){
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new Group("", "new", "new", "new"));
            }
        });
        app.groups().removeAllGroups();
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(0, app.hbm().getGroupCount());
        });
    }

}
