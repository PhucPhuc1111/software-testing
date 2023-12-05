package BT;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import driver.driverFactory;

public class tc04 {
    @Test
    public void testTC04() {
        int scc = 0;
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

    try {
        //Step 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");
        
        // Step 2. Click on �MOBILE� menu
        driver.findElement(By.linkText("MOBILE")).click();
        // for debug only
        Thread.sleep(2000);

        // Step 3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
        driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
        driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
        //for debug only
        Thread.sleep(3000);

        // Step 4. Click on �COMPARE� button. A popup window opens
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        //for debug only
        Thread.sleep(2000);

        // Step 5. Verify the pop-up window and check that the products are reflected in it
        //Verify the popup window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(2000);
        WebElement popupHeading = driver.findElement(By.cssSelector("div[class='page-title title-buttons'] h1"));
        String expectedPopupHeading = "COMPARE PRODUCTS";
        String actualPopupHeading = popupHeading.getText();
        System.out.println(actualPopupHeading);
        if (actualPopupHeading.equals(expectedPopupHeading)) {
            System.out.println("Popup window opened with heading: " + actualPopupHeading);
        } else {
            System.out.println("Popup window heading verification failed.");
        }

        // check that the products are reflected in it
        WebElement product1Element = driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']"));
        String expectedProduct1 = "SONY XPERIA";
        String actualProduct1 = product1Element.getText();
        System.out.println(actualProduct1);
        WebElement product2Element = driver.findElement(By.xpath("//a[normalize-space()='IPhone']"));
        String expectedProduct2 = "IPHONE";
        String actualProduct2 = product2Element.getText();
        System.out.println(actualProduct2);
        if (actualProduct1.equals(expectedProduct1) && actualProduct2.equals(expectedProduct2)) {
            System.out.println("Popup window have correct product reflected in it: " + actualProduct1 + ", " + actualProduct2);
        } else {
            System.out.println("Popup window doesn't have correct product reflected in it.");
        }


        // Step 6. Close the Popup Windows
        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        //for debug only
        Thread.sleep(2000);

        // Step 7. Switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(2000);

        //this will take screenshot after success
        scc = (scc + 4);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String png = ("D:\\SWT301\\SWT301-Ecommerce-project\\selenium-webdriver-java-master\\src\\test\\resources\\testcase" + scc + ".png");
        FileUtils.copyFile(scrFile, new File(png));

    } catch (Exception e){
        e.printStackTrace();
    }

    // Quit browser
        driver.quit();
    }
}
