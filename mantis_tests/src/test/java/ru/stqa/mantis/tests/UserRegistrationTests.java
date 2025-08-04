package ru.stqa.mantis.tests;

import io.swagger.client.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.manager.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(){
        //создать пользователя на почтовом сервере(JS)
        var email = String.format("%s@localhost", CommonFunctions.randomString(6));
        var username = CommonFunctions.randomString(4);
        var password = "password";
        app.jamesCli().addUser(
                email,password);
        // заполняем форму создания и отправляем(браузер)
        app.mantis().signUp(username, email);
        //ждем почту(mail)
        var messages= app.mail().receive(email, password, Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        //извлекаем ссылку
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        Assertions.assertTrue(matcher.find(), "Не найдена ссылка в письме");
        var url = text.substring(matcher.start(), matcher.end());
        //проходим по ссылке и завершаем регистрацию(браузер)
        app.driver().get(url);
        app.mantis().fillRegistrationForm(username, "password");
        //проверяем, что пользователь может залог(http)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegisterApiUser(){
        //создать пользователя на почтовом сервере(JS)
        var email = String.format("%s@localhost", CommonFunctions.randomString(6));
        var username = CommonFunctions.randomString(4);
        var password = "password";
        app.jamesCli().addUser(
                email,password);
        // заполняем форму создания и отправляем(браузер)
        app.rest().addUser(username, email);
        //ждем почту(mail)
        var messages= app.mail().receive(email, password, Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        //извлекаем ссылку
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        Assertions.assertTrue(matcher.find(), "Не найдена ссылка в письме");
        var url = text.substring(matcher.start(), matcher.end());
        //проходим по ссылке и завершаем регистрацию(браузер)
        app.driver().get(url);
        app.mantis().fillRegistrationForm(username, "password");
        //проверяем, что пользователь может залог(http)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
