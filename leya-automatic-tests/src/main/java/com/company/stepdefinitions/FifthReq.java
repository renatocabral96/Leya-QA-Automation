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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.time.Duration;


public class FifthReq {

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
    public void FifthRequirement() {
        driver.get("https://www.leyaonline.com/pt/");
        
        WebElement sun = driver.findElement(By.cssSelector("#darkmode > a > i"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sun);
        
        String classAttribute = sun.getAttribute("class");
        assertTrue(classAttribute.contains("icon-moon"), "The class attribute does not contain 'icon-moon'");
       
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
 
}
