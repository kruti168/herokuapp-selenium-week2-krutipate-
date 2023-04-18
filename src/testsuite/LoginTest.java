package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){

        openBrowser(baseUrl);

    }
    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials(){

        //find userName field
        WebElement emailfield = driver.findElement(By.xpath("//input[@name='username']"));
        emailfield.sendKeys("tomsmith");

        //find password field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword!");

        //find the login button and click
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();

        String expectedMessage ="Secure Area";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[text()=' Secure Area']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals(actualMessage,expectedMessage);

    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //find username field
        WebElement emailfield = driver.findElement(By.xpath("//input[@name='username']"));
        emailfield.sendKeys("tomsmith1");

        //find password field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword!");

        //find the login button and click
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();

        //Verify the error message "Your username is Invalid!"
        String expectedMessage = "Your username is invalid!\n" +
                "×";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals(actualMessage,expectedMessage);

    }
    @Test
    public void verifyThePasswordErrorMessage(){


        //find userName field
        WebElement emailfield = driver.findElement(By.xpath("//input[@name='username']"));
        //Type the Email address to email field element
        emailfield.sendKeys("tomsmith");

        //find password field Element and send password on password field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword");

        //find the login button and click
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        //Verify the error message "Your username is invalid!"
        String expectedMessage = "Your password is invalid!\n" + "×";

        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals(actualMessage,expectedMessage);
    }
    @After
    public void tearDown(){
        driver.quit();
    }



}
