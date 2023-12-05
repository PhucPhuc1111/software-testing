package BT;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import POM.checkOutPage;
import POM.loginPage;
import POM.registerPage;
import driver.driverFactory;

public class tc08 {
    @Test
    public void testTC08() {
        int scc = 0;
        // Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        registerPage registerPage = new registerPage(driver);
        loginPage loginPage = new loginPage(driver);
        checkOutPage checkOutPage = new checkOutPage(driver);
        StringBuffer verificationError = new StringBuffer();
        // Init input value
        // Values for LoginPage
        String email_address = "thuan123@gmail.com";
        String password = "thuan123";

        // Values for QTY
        String qty = "10";

        // Values for checkOutPage - Billing - Shipping (also include postcode above)
        String firstName = "Pham";
        String lastName = "Thuan";
        String address = "456a 123b";
        String city = "123 City";
        String telephone = "123456789";
        String postcode = "12345";

        try {
            // Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2. Click on My Account link
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

            // Step 4. Click on 'REORDER' link , change QTY & click Update
            driver.findElement(
                    By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']"))
                    .click();
            Thread.sleep(2000);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            // change QTY & click Update
            // take product price
            WebElement baseProductPrice = driver.findElement(By.cssSelector("strong span[class='price']"));
            String TextBaseProductPrice = baseProductPrice.getText();
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys(qty);
            // for debug only
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[title='Update']")).click();
            // for debug only
            Thread.sleep(2000);

            // Step 5. Verify Grand Total is changed
            WebElement changedProductPrice = driver.findElement(By.cssSelector("strong span[class='price']"));
            String TextChangedProductPrice = changedProductPrice.getText();
            System.out.println(TextChangedProductPrice);

        try {
            AssertJUnit.assertNotSame(TextChangedProductPrice, TextBaseProductPrice);
        } catch (Error e) {
            verificationError.append(e.toString());
        }
            // Step 6. Complete Billing & Shipping Information
            // Click "Proceed to Checkout"
            checkOutPage.clickCheckOutButton();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(0);
Thread.sleep(2000);
            // Enter Billing Information, and click Continue
            // Choose new address dropdown
            WebElement billingDropdownElement = driver
                    .findElement(By.xpath("//select[@id='billing-address-select']"));
            Select billingDropdownOption = new Select(billingDropdownElement);
            billingDropdownOption.selectByVisibleText("New Address");
            //input values
            checkOutPage.enterFirstName(firstName);
            checkOutPage.enterLastName(lastName);
            checkOutPage.enterAddress(address);
            checkOutPage.enterCity(city);

            // Choose country dropdown
            WebElement countryDropdownElementBilling = driver
                    .findElement(By.xpath("//select[@id='billing:country_id']"));
            Select countrySelectOptionBilling = new Select(countryDropdownElementBilling);
            countrySelectOptionBilling.selectByVisibleText("United States");
            // Choose region dropdown
            WebElement regionDropdownElementBilling = driver.findElement(By.xpath("//select[@id='billing:region_id']"));
            Select regionSelectOptionBilling = new Select(regionDropdownElementBilling);
            regionSelectOptionBilling.selectByVisibleText("California");

            checkOutPage.enterPostcode(postcode);
            checkOutPage.enterTelephone(telephone);

            // check"Ship to different address"
            driver.findElement(By.xpath("//label[@for='billing:use_for_shipping_no']")).click();
            driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();
            Thread.sleep(2000);

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Enter Shipping Information, and click Continue
            // Choose new address dropdown
            WebElement shippingDropdownElement = driver
                    .findElement(By.xpath("//select[@id='shipping-address-select']"));
            Select shippingDropdownOption = new Select(shippingDropdownElement);
            shippingDropdownOption.selectByVisibleText("New Address");
            //input values
            checkOutPage.enterShippingFirstName(firstName);
            checkOutPage.enterShippingLastName(lastName);
            checkOutPage.enterShippingAddress(address);
            checkOutPage.enterShippingCity(city);
            checkOutPage.enterShippingTelephone(telephone);
            driver.findElement(By.xpath("//button[@onclick='shipping.save()']//span//span[contains(text(),'Continue')]")).click();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
Thread.sleep(2000);
            // In Shipping Method, Click Continue
            driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]")).click();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // In Payment Information select 'Check/Money Order' radio button.
            // select 'Check/Money Order'
            driver.findElement(By.xpath("//label[@for='p_method_checkmo']")).click();
            // debug
            Thread.sleep(0);
            // Click Continue
            driver.findElement(By.xpath("//button[@class='button']//span//span[contains(text(),'Continue')]")).click();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Click 'PLACE ORDER' button
            driver.findElement(By.xpath("//button[@title='Place Order']")).click();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Step 7. Verify order is generated and note the order number
            // Verify Order is generated
            WebElement orderGeneratedMsg = driver.findElement(By.cssSelector("div[class='page-title'] h1"));
            String expectedOrderGeneratedMsg = "YOUR ORDER HAS BEEN RECEIVED.";
            String actualOrderGeneratedMsg = orderGeneratedMsg.getText();
            System.out.println(actualOrderGeneratedMsg);
            if (actualOrderGeneratedMsg.equals(expectedOrderGeneratedMsg)) {
                System.out.println("Verify Oder is generated is done with the message " + actualOrderGeneratedMsg);
            } else {
                System.out.println("Verify Oder is generated is not done.");
            }
            // Note the order Number
            String orderNum = driver
                    .findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]/a[1]"))
                    .getText();
            System.out.println("*** Your order number is:  " + orderNum);

            // this will take screenshot after success
            scc = (scc + 8);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String png = ("D:\\SWT301\\SWT301-Ecommerce-project\\selenium-webdriver-java-master\\src\\test\\resources\\testcase"
                    + scc + ".png");
            FileUtils.copyFile(scrFile, new File(png));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit browser
        driver.quit();
    }
}
