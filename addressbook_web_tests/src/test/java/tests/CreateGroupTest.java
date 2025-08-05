package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.qameta.allure.Allure;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        /*var json = "";
        try (var reader = new FileReader("groups.json");
        var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null){
                json = json + line;
                line = breader.readLine();
            }
        }*/

        ObjectMapper mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<Group>>(){});
        result.addAll(value);
        return result;
    }

    public static Stream<Group> randomGroups() {
        Supplier<Group> randomGroup = () -> new Group()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));
        return Stream.generate(randomGroup).limit(1);
    }


    @ParameterizedTest
    @MethodSource("randomGroups")
    public void CanCreateMGroup(Group group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
        Allure.step("Validating group creation", step -> {
            Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
        });
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
        Allure.step("Validating group was not created", step -> {
            Assertions.assertEquals(newGroups, oldGroups);
        });

    }
}
