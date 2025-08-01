package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class MantisHelper extends HelperBase{

    public MantisHelper(ApplicationManager manager) {
        super(manager);
    }

    void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
        click(By.cssSelector("input:nth-child(7)"));
    }

    public void signUp(String username, String email){
        click(By.className("back-to-login-link"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void fillRegistrationForm(String realName, String password){
        type(By.name("realname"), realName);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("span.bigger-110"));
    }
}
