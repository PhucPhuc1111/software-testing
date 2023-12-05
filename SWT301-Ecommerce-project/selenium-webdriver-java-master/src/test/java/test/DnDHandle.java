package test;

import driver.driverFactory;
import element.ElementController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
@Test
public class DnDHandle {
    public static void testDnDHandle () {
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        By leftColumnSelector = By.id("column-a");
        By rightColumnSelector = By.id("column-b");

        WebElement leftColumnElem = driver.findElement(leftColumnSelector);
        WebElement rightColumnElem = driver.findElement(rightColumnSelector);

        try {
            ElementController.dnd(leftColumnElem, rightColumnElem);
            ElementController.dnd(rightColumnElem, leftColumnElem);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
