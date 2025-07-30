package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.manager.ApplicationManager;

public class LoginTests extends TestBase{
        @Test
    void canLogin(){
        app.session().login("administrator", "root");
        Assertions.assertTrue(app.session().isLoggedIn());
    }
}
