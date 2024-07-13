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
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.nio.file.Paths;
import java.time.Duration;


public class SeventhReqTest {

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
    //Tests if a selected book filter is applied on the website
    public void checkFilterApplication() {
        driver.get("https://www.leyaonline.com/pt/");
        
        WebElement bars = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/header/div/div/div[2]/div[1]/a/i")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bars);
        
        WebElement books = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"offcanvasMenu1\"]/div[2]/div/ul/li[1]/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", books);
        
        WebElement filters = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pjax-container\"]/section/div/div[1]/div/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filters);
        
        WebElement order = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dropdownOrderButton\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", order);
        
        WebElement alphabeticOrder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-filter-offcanvast\"]/div[2]/div/div[2]/div/div[2]/div/ul/li[1]/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", alphabeticOrder);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
                
        WebElement filters1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pjax-container\"]/section/div/div[1]/div\r\n")));
        filters1.click();
        
        WebElement order1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dropdownOrderButton\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", order1);
        
        String buttonText = order1.getText();
        assertTrue(buttonText.contains("Ordem alfabética"), "The button text does not contain 'Ordem alfabética'");    
        
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
