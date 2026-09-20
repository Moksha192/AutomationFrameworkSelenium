package com.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final By username=By.id("user-name");
    private final By password=By.id("password");
    private final By loginBtn=By.id("login-button");
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public void fillUserName(String userName){
        driver.findElement(username).sendKeys(userName);
    }
    public void fillPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }
    public InventoryPage clickLoginButton(){
        driver.findElement(loginBtn).click();
        return new InventoryPage(driver);
    }
}
