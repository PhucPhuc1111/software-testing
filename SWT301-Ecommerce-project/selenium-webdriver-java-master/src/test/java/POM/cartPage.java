package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class cartPage {
    WebDriver driver;

    By postcodeInputLocator = By.id("postcode");

    //Constructor with required parameter as a WedDriver
    public cartPage(WebDriver driver) {
        this.driver = driver;
    }
//method
    public void enterPostcodeInput(String postcode){
        WebElement postcodeElement = driver.findElement(postcodeInputLocator);
        postcodeElement.clear();
        postcodeElement.sendKeys(postcode);
    }


}