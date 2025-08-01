package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mail;
    private MantisHelper mantis;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }


    public WebDriver driver() {
        if (driver == null) {
            if ("firefox".equals(browser)){
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            } else if ("chrome".equals(browser)){
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            driver.get(properties.getProperty("web.mantisUrl"));
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.manage().window().setSize(new Dimension(1936, 1048));
        }
        return driver;
    }

    public SessionHelper session(){
        if (sessionHelper == null){
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http(){
        if (httpSessionHelper == null){
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public String property(String name){
        return properties.getProperty(name);
    }

    public JamesCliHelper jamesCli(){
        if (jamesCliHelper == null){
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail(){
        if (mail == null){
            mail = new MailHelper(this);
        }
        return mail;
    }

    public MantisHelper mantis(){
        if (mantis == null){
            mantis = new MantisHelper(this);
        }
        return mantis;
    }

}
