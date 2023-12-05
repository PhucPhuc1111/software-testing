package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class beLoginPage {
    WebDriver driver;

    By emailInputLocator = By.id("username");
    By passwordInputLocator = By.id("login");

    By loginButton = By.xpath("//input[@title='Login']");

    public beLoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void enterEmail(String email){
        WebElement emailElement = driver.findElement(emailInputLocator);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password){
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
