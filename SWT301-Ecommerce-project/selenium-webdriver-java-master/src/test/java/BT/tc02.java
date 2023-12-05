package BT;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import driver.driverFactory;

public class tc02 {
    @Test
    public void testTC02() {

        int scc = 0;
        StringBuffer verificationError = new StringBuffer();

        // init web driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            // Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2. Click on �MOBILE� menu
            driver.findElement(By.linkText("MOBILE")).click();
            // for debug only
            Thread.sleep(2000);

            // Step 3.In the list of all mobile , read the cost of Sony Xperia mobile (which
            // is $100)
            String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
            System.out.println(XPeriaPrice);
            // for debug only
            Thread.sleep(2000);

            // Step 4. Click on Sony Xperia mobile
            driver.findElement(By.id("product-collection-image-1")).click();
            // for debug only
            Thread.sleep(2000);

            // Step 5. Read the Sony Xperia mobile from detail page.
            String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();

            // Step 6. Compare Product value in list and details page should be equal
            // ($100).
            try {
                AssertJUnit.assertEquals(XPeriaPrice, detailPrice);
            } catch (Error e) {
                verificationError.append(e.toString());
            }
            // for debug only
            Thread.sleep(3000);

            // Step 7. This will take screenshot after success
            scc = (scc + 2);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String png = ("D:\\SWT301\\SWT301-Ecommerce-project\\selenium-webdriver-java-master\\src\\test\\resources\\testcase" + scc + ".png");
            FileUtils.copyFile(scrFile, new File(png));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit browser
        driver.quit();

    }
}
