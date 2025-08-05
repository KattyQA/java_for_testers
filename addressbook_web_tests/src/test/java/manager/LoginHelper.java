package manager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }
    @Step
    void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
        click(By.cssSelector("input:nth-child(7)"));
    }
}
