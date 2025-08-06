package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    public WebDriver driver;

    // Locators
    By shoppingCart = By.xpath("//*[@class='shopping_cart_link']");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page Methods
    public void AddProductToCart(String ProductName) throws InterruptedException {
        By addCart = By.xpath("//*[@class='inventory_item_label']/a/div[text()='"
                + ProductName + "']/parent::*/parent::*/following-sibling::*/button");
        driver.findElement(addCart).click();
        
       // new WebDriverWait(driver, Duration.ofSeconds(5))
        //.until(ExpectedConditions.elementToBeClickable(addCart))
        //.click();
        
        Thread.sleep(5000);
    }

    public void clickOnShoppingCart() {
        driver.findElement(shoppingCart).click();
    }
}

