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


import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThirdReqTest {

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
    public void ThirdRequirement() {
        driver.get("https://www.leyaonline.com/pt/");
        
        closeCookiesPrompt();
        WebElement searchBar = driver.findElement(By.cssSelector("#searchbar-large"));
        searchBar.sendKeys("1984");
        
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div/div[1]/div[1]/a/img")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        
        WebElement author = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bookcard_96097\"]/a[1]/div[2]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", author);
        String authorText = author.getText();
        assertTrue(authorText.contains("GEORGE ORWELL"), "Description does not contain 'GEORGE ORWELL'.");
        assertTrue(authorText.contains("A Quinta dos Animais"), "Description does not contain 'A Quinta dos Animais'.");
        
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    public void closeCookiesPrompt() {
        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cookiescript_close")));
            cookiesButton.click();
        } catch (Exception e) {
            System.out.println("No cookies prompt found.");
        }
    }  
}
