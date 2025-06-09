package tests;

import model.Group;
import org.junit.jupiter.api.Test;

public class DeleteGroupTestTest extends TestBase {

    @Test
    public void CanDeleteGroupTest() {
        if (app.groups().isGroupPresent()) {
            app.groups().createGroup(new Group("new", "new", "new"));
        }
        app.groups().deleteGroup();

    }

}
