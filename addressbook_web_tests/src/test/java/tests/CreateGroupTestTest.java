package tests;

import model.Group;
import org.junit.jupiter.api.Test;

public class CreateGroupTestTest extends TestBase {

    @Test
    public void CanCreateGroupTest() {
        app.groups().createGroup(new Group("new", "new", "new"));

    }

    @Test
    public void CanCreateGroupTestWithEmptyName() {
        app.groups().createGroup(new Group());

    }

    @Test
    public void CanCreateGroupTestWithNameOnly() {
        app.groups().createGroup(new Group().withName("some name"));

    }
}
