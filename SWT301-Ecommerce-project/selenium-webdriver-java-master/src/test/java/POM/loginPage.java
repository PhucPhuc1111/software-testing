package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {
    WebDriver driver;

    By emailInputLocator = By.id("email");
    By passwordInputLocator = By.id("pass");

    By loginButton = By.xpath("//button[@id='send2']");

    public loginPage(WebDriver driver) {
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