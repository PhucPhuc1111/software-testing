package BT;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import driver.driverFactory;
import java.time.Duration;

public class tc09 {
    @Test
    public void testTC09() {
        int scc = 0;

        // Set your custom timeout in seconds
        int customTimeoutInSeconds = 20; 
        Duration customTimeout = Duration.ofSeconds(customTimeoutInSeconds);
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        //Coupon code
        String couponCode = "GURU50";

        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //Step 2. Go to Mobile and add IPHONE to cart
            driver.findElement(By.linkText("MOBILE")).click();

            // Wait for the iPhone element to be clickable
            WebDriverWait wait = new WebDriverWait(driver, customTimeout);
            WebElement IphoneAddToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]")));
            IphoneAddToCart.click();

            // Switch to the new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //Step 3. Enter Coupon Code
            WebElement basePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tfoot[1]/tr[1]/td[2]/strong[1]/span[1]")));
            String basePriceStr = basePrice.getText();
            System.out.println(basePriceStr);

            WebElement couponInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='coupon_code']")));
            couponInput.clear();
            couponInput.sendKeys(couponCode);

            WebElement apply = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Apply')]")));
            apply.click();

            WebElement newPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tfoot[1]/tr[1]/td[2]/strong[1]/span[1]")));
            String newPriceStr = newPrice.getText();

            //Step 4. Verify the discount generated
            System.out.println(newPriceStr);
            Assert.assertNotEquals(newPriceStr, basePriceStr);

            //this will take screenshot after success
            scc = (scc + 9);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String png = ("D:\\SWT301\\SWT301-Ecommerce-project\\selenium-webdriver-java-master\\src\\test\\resources\\testcase" + scc + ".png");
            FileUtils.copyFile(scrFile, new File(png));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit browser
            driver.quit();
        }
    }
}
