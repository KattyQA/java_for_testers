package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CreateGroupTest extends TestBase {

    public static List<Group> groupProvider() throws IOException {
        var result = new ArrayList<Group>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")){
                for (var footer : List.of("", "group footer")){
                    result.add(new Group()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        }
        var json = "";
        try (var reader = new FileReader("groups.json");
        var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null){
                json = json + line;
                line = breader.readLine();
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<Group>>(){});
        result.addAll(value);
        return result;
    }


    @ParameterizedTest
    @MethodSource("groupProvider")
    public void CanCreateMultipleGroups(Group group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<Group> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }

    public static List<Group> negativeGroupProvider() {
        var result = new ArrayList<Group>(List.of(
                new Group("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void CanNotCreateGroup(Group group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);

    }
}
