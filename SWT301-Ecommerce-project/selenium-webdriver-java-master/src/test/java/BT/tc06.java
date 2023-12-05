package BT;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import POM.cartPage;
import POM.checkOutPage;
import POM.loginPage;
import POM.registerPage;
import driver.driverFactory;

public class tc06 {
    @Test
    public void testTC06() {
        int scc = 0;

        // Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        registerPage registerPage = new registerPage(driver);
        loginPage loginPage = new loginPage(driver);
        cartPage cartPage = new cartPage(driver);
        checkOutPage checkOutPage = new checkOutPage(driver);

        // Init input value
        // Values for LoginPage
        String email_address = "thuan123@gmail.com";
        String password = "thuan123";

        // Values for cartPage
        String postcode = "12345";

        // Values for checkOutPage - Billing - Shipping (also include postcode above)
        String firstName = "Pham";
        String lastName = "Thuan";
        String address = "456a 123b";
        String city = "123 City";
        String telephone = "123456789";

        try {
            // Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2. Click on my account link
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
            Thread.sleep(0);
            // Go to TV menu
            driver.findElement(By.linkText("TV")).click();
            // for debug only
            Thread.sleep(0);
            // Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();
            Thread.sleep(0);

            // Step 4. Click on MY WISHLIST link
            driver.findElement(By.linkText("MY WISHLIST")).click();
            // debug
            Thread.sleep(2000);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(0);

            // Step 5. In next page, Click ADD TO CART link
            driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
            // debug
            Thread.sleep(2000);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(0);

            // Step 6. Enter general shipping country, state/province and zip for the
            // shipping cost estimate
            // Choose country dropdown
            WebElement countryDropdownElement = driver.findElement(By.xpath("//select[@id='country']"));
            Select countrySelectOption = new Select(countryDropdownElement);
            countrySelectOption.selectByVisibleText("United States");
            // Choose region dropdown
            WebElement regionDropdownElement = driver.findElement(By.xpath("//select[@id='region_id']"));
            Select regionSelectOption = new Select(regionDropdownElement);
            regionSelectOption.selectByVisibleText("California");
            cartPage.enterPostcodeInput(postcode);

            // Step7. Click Estimate
            driver.findElement(By.xpath(".//*[@id='shipping-zip-form']/div/button")).click();
            // debug
            Thread.sleep(0);

            // Step 8. Verify Shipping cost generated
            // Verify flat rate
            WebElement flatRate = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt"));
            String expectedFlatRate = "Flat Rate";
            String actualFlatRate = flatRate.getText();
            System.out.println(actualFlatRate);
            if (actualFlatRate.equals(expectedFlatRate)) {
                System.out.println("Flat Rate verified " + actualFlatRate);
            } else {
                System.out.println("Flat Rate not verified");
            }
            // Verify flat rate cost
            WebElement flatRateCost = driver
                    .findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label"));
            String expectedFlatRateCost = "Fixed - $5.00";
            String actualFlatRateCost = flatRateCost.getText();
            System.out.println(actualFlatRateCost);
            if (actualFlatRateCost.equals(expectedFlatRateCost)) {
                System.out.println("Flat Rate Cost verified " + actualFlatRateCost);
            } else {
                System.out.println("Flat Rate Cost not verified");
            }

            // Step 9. Select Shipping Cost, Update Total
            driver.findElement(By.id("s_method_flatrate_flatrate")).click();
            driver.findElement(By.xpath("//button[@title='Update Total']")).click();
            // debug
            Thread.sleep(0);

            // Step 10. Verify shipping cost is added to total
            WebElement shippingCostIncluded = driver
                    .findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span"));
            String expectedShippingCostIncluded = "$5.00";
            String actualShippingCostIncluded = shippingCostIncluded.getText();
            System.out.println(actualShippingCostIncluded);
            if (actualShippingCostIncluded.equals(expectedShippingCostIncluded)) {
                System.out.println("shipping cost is added verified " + actualShippingCostIncluded);
            } else {
                System.out.println("shipping cost is added not verified");
            }

            // Step 11. Click "Proceed to Checkout"
            checkOutPage.clickCheckOutButton();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(0);

            // Step 12a. Enter Billing Information, and click Continue
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

            // Step 12b. Enter Shipping Information, and click Continue
            // Choose new address dropdown
            WebElement shippingDropdownElement = driver
                    .findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/ol[1]/li[2]/div[2]/form[1]/ul[1]/li[1]/div[1]/select[1]"));
            Select shippingDropdownOption = new Select(shippingDropdownElement);
            shippingDropdownOption.selectByVisibleText("New Address");
            //input values
            checkOutPage.enterShippingFirstName(firstName);
            checkOutPage.enterShippingLastName(lastName);
            checkOutPage.enterShippingAddress(address);
            checkOutPage.enterShippingCity(city);
            checkOutPage.enterShippingTelephone(telephone);
            driver.findElement(By.xpath(".//*[@id='shipping-buttons-container']/button")).click();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Step 13. In Shipping Method, Click Continue
            driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]")).click();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Step 14. In Payment Information select 'Check/Money Order' radio button.
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

            // Step 15. Click 'PLACE ORDER' button
            driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();
            // debug
            Thread.sleep(0);
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            
            // 16. Verify Oder is generated. Note the order number
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
            String orderNum = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]/a[1]"))
                    .getText();
            System.out.println("*** Your order number is:  " + orderNum);

            // this will take screenshot after success
            scc = (scc + 6);
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
