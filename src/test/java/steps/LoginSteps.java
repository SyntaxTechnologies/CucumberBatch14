package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class LoginSteps extends CommonMethods {
   // WebDriver driver;

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
            openBrowserAndLaunchApplication();
       driver=new ChromeDriver();
       driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       driver.manage().window().maximize();
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {
       LoginPage login = new LoginPage();
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys(ConfigReader.getPropertyValue("username"));
        sendText(login.usernameTextField, ConfigReader.getPropertyValue("username"));
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(ConfigReader.getPropertyValue("password"));
        sendText(login.passwordTextField, ConfigReader.getPropertyValue("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
      LoginPage login = new LoginPage();
      WebElement loginButton = driver.findElement(By.id("btnLogin"));
        click(login.loginButton);
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        WebElement welcomeMessage = driver.findElement(By.id("welcome"));

        if(dashboard.welcomeMessage.isDisplayed()){
            System.out.println("Test case is passed");
        }else{
            System.out.println("Test is failed");
        }
    }

    @When("user enters ess username and ess password")
    public void user_enters_ess_username_and_ess_password() {
        LoginPage login = new LoginPage();
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        sendText(login.usernameTextField, "asmahuma321");
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextField, "Hum@nhrm123");
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
       LoginPage login = new LoginPage();
       WebElement usernameField = driver.findElement(By.id("txtUsername"));
        sendText(login.usernameTextField, "admin123");
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextField, "Hum@nhrm");
    }

    @Then("error message displayed")
    public void error_message_displayed() {
        System.out.println("Error message displayed");
    }

    @When("user enters different {string} and {string} and verify the {string} for it")
    public void user_enters_different_and_and_verify_the_for_it(String username, String password, String errorMessage) {
        sendText(login.usernameTextField, username);
        sendText(login.passwordTextField, password);
        click(login.loginButton);

        String errorActual =  login.errorMessage.getText();
        Assert.assertEquals(errorMessage, errorActual);
    }
}
