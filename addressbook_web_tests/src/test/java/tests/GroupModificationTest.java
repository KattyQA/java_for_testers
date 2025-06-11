package tests;

import model.Group;
import org.junit.jupiter.api.Test;

public class GroupModificationTest extends TestBase {

    @Test
    void canModifyGroup(){
        if (app.groups().isGroupPresent()) {
            app.groups().createGroup(new Group("new", "new", "new"));
        }
        app.groups().modifyGroup(new Group().withName("modified name"));
    }

}
