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

public class tc03 {
    @Test
    public void testTC03() {
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

        // Step 3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
        WebElement addToCartButton = driver.findElement(By.xpath("//a[contains(text(), 'Sony Xperia')]/following::button[@title='Add to Cart']"));
        addToCartButton.click();
        //for debug only
        Thread.sleep(3000);

        // Step 4. Change "QTY" value to 1000 and click "UPDATE" button.
        WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
        qtyInput.clear();
        qtyInput.sendKeys("1000");
        //for debug only
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[title='Update']")).click();
        //for debug only
        Thread.sleep(2000);

        // Step 5. Verify the error message "The requested quantity for "Sony Xperia" is not available.
        WebElement errorMessage = driver.findElement(By.xpath("//p[@class='item-msg error']"));
        String actualErrorMessage = errorMessage.getText();
        System.out.println(actualErrorMessage);
        String expectedErrorMessage = "The requested quantity for \"Sony Xperia\" is not available.";
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Error message verified: " + actualErrorMessage);
        } else {
            System.out.println("Error message verification failed.");
        }

        // Step 6. Click on "EMPTY CART" link in the footer of the list of all mobiles.
        driver.findElement(By.cssSelector("button[id='empty_cart_button']")).click();
        //for debug only
        Thread.sleep(2000);

        // Step 7. Verify cart is empty
        WebElement emptyCartMessage = driver.findElement(By.cssSelector("div[class='page-title'] h1"));
        String actualEmptyCartMessage = emptyCartMessage.getText();
        System.out.println(actualEmptyCartMessage);
        String expectedEmptyCartMessage = "SHOPPING CART IS EMPTY";
        if (actualEmptyCartMessage.equals(expectedEmptyCartMessage)) {
            System.out.println("Empty cart verified: " + actualEmptyCartMessage);
        } else {
            System.out.println("Empty cart verification failed.");
        }
        Thread.sleep(2000);

        //this will take screenshot after success
        scc = (scc + 3);
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
