package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class CreateGroupTest extends TestBase {

    public static List<Group> groupProvider() {
        var result = new ArrayList<Group>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")){
                for (var footer : List.of("", "group footer")){
                    result.add(new Group(name, header, footer));
                }
            }
        }
        for (int i = 0; i < 5; i++){
           result.add(new Group(randomString(i), randomString(i), randomString(i)));

        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("groupProvider")
    public void CanCreateMultipleGroups(Group group) {
        int groupCount = app.groups().getCount();

        app.groups().createGroup(group);


        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);

    }

    public static List<Group> negativeGroupProvider() {
        var result = new ArrayList<Group>(List.of(
                new Group("group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void CanNotCreateGroup(Group group) {
        int groupCount = app.groups().getCount();

        app.groups().createGroup(group);


        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);

    }
}
