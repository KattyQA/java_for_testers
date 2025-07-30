package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTest extends TestBase {

    @Test
    void canModifyGroup(){
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new Group("", "new", "new", "new"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new Group().withName(CommonFunctions.randomString(5));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

}
