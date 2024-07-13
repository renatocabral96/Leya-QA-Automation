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

public class SecondReq {

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
    public void SecondRequirement() {
        driver.get("https://www.leyaonline.com/pt/");
        
        closeCookiesPrompt();
        WebElement searchBar = driver.findElement(By.cssSelector("#searchbar-large"));
        searchBar.sendKeys("1984");
        
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div/div[1]/div[1]/a/img")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        
        WebElement author = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pjax-container\"]/section[1]/div/div[1]/div[2]/div/h3/a")));
        String authorText = author.getText();
        assertTrue(authorText.contains("GEORGE ORWELL"), "Description does not contain 'GEORGE ORWELL'.");
        
        WebElement isbn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"pjax-container\"]/section[2]/div/div[3]/div/div")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", isbn);
        String isbnText = isbn.getText();
        assertTrue(isbnText.contains("9789722071550"), "Description does not contain '9789722071550'.");
        
        WebElement pages = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"pjax-container\"]/section[2]/div/div[3]/div/div")));
        String pagesText = pages.getText();
        assertTrue(pagesText.contains("344"), "Description does not contain '344'.");
        
        WebElement dimensions = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"pjax-container\"]/section[2]/div/div[3]/div/div")));
        String dimensionsText = dimensions.getText();
        assertTrue(dimensionsText.contains("235 x 157 x 23 mm"), "Description does not contain 'GEORGE ORWELL'.");
       
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    public void closeCookiesPrompt() {
        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cookiescript_close"))); // Replace with the actual selector
            cookiesButton.click();
        } catch (Exception e) {
            System.out.println("No cookies prompt found.");
        }
    }  
}
