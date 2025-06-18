package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteGroupTest extends TestBase {

    @Test
    public void CanDeleteGroupTest() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("new", "new", "new"));
        }
        int groupCount = app.groups().getCount();
        app.groups().deleteGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);

    }

    @Test
    void canDeleteAllGroupsAtOnce(){
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new Group("new", "new", "new"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}
