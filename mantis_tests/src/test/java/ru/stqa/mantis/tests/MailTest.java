package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTest extends TestBase {

    @Test
    void canDrainInbox(){
        app.mail().drain("user1@localhost", "password");
    }

    @Test
    void canReceiveEmail(){
        var messages= app.mail().receive("user1@localhost", "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
    }

    @Test
    void canExtractUrl(){
        var messages= app.mail().receive("user1@localhost", "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = text.substring(matcher.start(), matcher.end());
        System.out.println(url);
    }

}
