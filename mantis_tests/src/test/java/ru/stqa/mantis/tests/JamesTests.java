package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.manager.common.CommonFunctions;

public class JamesTests extends TestBase{

    @Test
    void canCreateUser(){
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctions.randomString(6)),
                "password");
    }
}
