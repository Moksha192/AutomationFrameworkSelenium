package com.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SideMenu {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By openMenu =
            By.id("react-burger-menu-btn");

    private final By closeMenu =
            By.id("react-burger-cross-btn");

    private final By menu = By.cssSelector(".bm-menu");

    public SideMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        wait.until(
                ExpectedConditions.elementToBeClickable(openMenu)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(menu)
        );
    }

    public void close() {
        wait.until(
                ExpectedConditions.elementToBeClickable(closeMenu)
        ).click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(menu)
        );
    }
}