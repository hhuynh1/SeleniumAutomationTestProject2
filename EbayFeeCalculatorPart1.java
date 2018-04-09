/**
 *
 *  ITEC 4260
 *  Dr. Im
 *  Georgia Gwinnett College
 *
 *  Project 2: Real World System Testing with Selenium
 *
 *  Created by Henry Huynh
 *  Date: 4/09/2018
 */

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

// Declaring class name
public class EbayFeeCalculatorPart1 {

    // Declaring instance of WebElement and WebDriver objects
    public static WebDriver driver;
    public static WebElement category;
    public static WebElement category1;
    public static WebElement camerasAndPhotos;
    public static WebElement camerasAndPhotos1;
    public static WebElement sellingFormat;
    public static WebElement startingPrice;
    public static WebElement salePrice;
    public static WebElement auctionStyle;
    public static WebElement galleryPlusCheckBox;
    public static WebElement reservePriceCheckBox;
    public static WebElement internationalSiteVisibility;
    public static WebElement reservePrice;
    public static WebElement domesticShipping;
    public static WebElement estimatedTotal;
    public static WebElement calculate;


    // Implementing test method and creating a initialize Driver method
    @BeforeClass
    public static void initializeDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Software Development\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.fees.ebay.com/feeweb/feecalculator");

        setupWebElement();
        executeBehavior();
    }

    public static void setupWebElement() throws InterruptedException {
        // Initializing WebElement objects to avoid null pointer exceptions
        category = driver.findElement(By.xpath("//*[@id=\"btn_cat-level1\"]"));
        camerasAndPhotos = driver.findElement(By.xpath("//*[@id=\"cat-level1_5\"]"));
        category1 = driver.findElement(By.xpath("//*[@id=\"btn_cat-level2\"]"));
        startingPrice = driver.findElement(By.id("startprice"));
        salePrice = driver.findElement(By.id("finalsaleprice"));
        sellingFormat = driver.findElement(By.id("dropdown_dd_listing_format"));
        auctionStyle = driver.findElement(By.id("dd_listing_format_0"));
        galleryPlusCheckBox = driver.findElement(By.xpath("//*[@id=\"feetype\"]/ul/li[1]/span[1]"));
        reservePriceCheckBox = driver.findElement(By.xpath("//*[@id=\"feetype\"]/ul/li[7]/span[1]"));
        internationalSiteVisibility = driver.findElement(By.xpath("//*[@id=\"feetype\"]/ul/li[8]/span[1]"));
        reservePrice = driver.findElement(By.id("reserveprice"));
        domesticShipping = driver.findElement(By.id("domesticshipping"));
        estimatedTotal = driver.findElement(By.id("estimated_total"));
        calculate = driver.findElement(By.id("calcbutton"));
    }

    public static void executeBehavior() throws InterruptedException {
        // Implementing behavior
        category.click();
        camerasAndPhotos.click();
        category1.click();

        // Web Driver must be slowed down
        // Implementing WebDriverWait class to slow down action
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"options_cat-level2\"]/li[18]"))));
        camerasAndPhotos1 = driver.findElement(By.xpath("//*[@id=\"options_cat-level2\"]/li[18]"));
        Thread.sleep(1000);
        camerasAndPhotos1.click();

        // Implementing behavior
        sellingFormat.click();
        auctionStyle.click();
        startingPrice.sendKeys("199");
        salePrice.sendKeys("7800");
        galleryPlusCheckBox.click();
        reservePriceCheckBox.click();
        internationalSiteVisibility.click();
        reservePrice.sendKeys("400");
        domesticShipping.sendKeys("30");

        // Slowing down process by 3 seconds then calculate the listing fees
        wait.until(ExpectedConditions.elementToBeClickable((By.id("calcbutton"))));
        calculate = driver.findElement(By.id("calcbutton"));
        Thread.sleep(4000);
        calculate.click();
    }

    // Testing method with Asset.equals
    @Test
    public void testEbayFees() throws InterruptedException {
        Thread.sleep(3000);
        String totalStr = estimatedTotal.getText();
        Double total = Double.parseDouble(totalStr);

        double expected = 767.10;
        double actual = total;
        double delta = actual - expected ;

        Assert.assertEquals(expected,actual,delta);
    }



}
