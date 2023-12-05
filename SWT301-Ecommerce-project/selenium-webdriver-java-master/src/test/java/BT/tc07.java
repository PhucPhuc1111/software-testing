package BT;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import POM.loginPage;
import POM.registerPage;
import driver.driverFactory;

public class tc07 {
    @Test
    public void testTC07() {
        int scc = 0;
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        registerPage registerPage = new registerPage(driver);
        loginPage loginPage = new loginPage(driver);

        // Init input value
        // Values for LoginPage
        String email_address = "thuan123@gmail.com";
        String password = "thuan123";

    try {
        //Step 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");
        
        //Step 2. Click on My Account link
        registerPage.clickMyAccountLink();
        // Switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Step 3. Login in application using previously created credential
        loginPage.enterEmail(email_address);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        // Switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        //Step 4. Click on 'My Orders'
        driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).click();
        Thread.sleep(2000);
        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        //Step 5. Click on 'View Order'
        driver.findElement(By.xpath("//tr[@class='first odd']//a[contains(text(),'View Order')]")).click();
        Thread.sleep(2000);
        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        //Step 6. Click on 'Print Order' link
        driver.findElement(By.xpath("//a[@class='link-print']")).click();
        Thread.sleep(2000);
        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(3000);

        //this will take screenshot after success
        scc = (scc + 7);
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
