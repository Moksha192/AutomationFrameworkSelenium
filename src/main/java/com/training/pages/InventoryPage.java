package com.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    private final WebDriver driver;
    private final SideMenu sideMenu;
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        sideMenu=new SideMenu(driver);
    }
    private final By inventoryList = By.cssSelector("div#inventory_container.inventory_container .inventory_list");
    private final By inventoryItem=By.className("inventory_item");
    private final By hamburgerIcon=By.className("bm-burger-button");
    private final By sidebarCloseIcon=By.id("react-burger-cross-btn");
    public List<WebElement> getInventoryItems()
    {
        return driver.findElement(inventoryList).findElements(inventoryItem);
    }

    public void openSidebar()
    {
        sideMenu.open();
    }

    public void closeSidebar()
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closeBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(sidebarCloseIcon)
        );
        wait.until(ExpectedConditions.elementToBeClickable(sidebarCloseIcon));
        closeBtn.click();
    }
}
