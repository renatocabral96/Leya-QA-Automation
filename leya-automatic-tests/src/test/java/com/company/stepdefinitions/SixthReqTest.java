package com.company.stepdefinitions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.time.Duration;


public class SixthReqTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
    	String chromeDriverPath = Paths.get("drivers", "chromedriver.exe").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @Test
    //Tests the login of a user account
    public void userLogin() {
        driver.get("https://www.leyaonline.com/pt/");
        
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/header/div/div/div[3]/div[2]/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", login);
        
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-email\"]")));
        username.sendKeys("machspeed1996@gmail.com");
        
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-pass\"]")));
        password.sendKeys("password1996");
        
        WebElement loginButton = driver.findElement(By.cssSelector("#pjax-container > section > div > div > div > form > div.form-group > button"));
        loginButton.click();
        
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dropdownMenuLink20\"]")));
        String dataTagValue = name.getAttribute("data-tag");
        
        assertEquals("testacc", dataTagValue, "The data-tag attribute is not 'testacc'");
    
      
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
