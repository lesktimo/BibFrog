package bibfrog;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StepDefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://bibfrog.herokuapp.com";

    @Given("^add inproceeding is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl + "/inpro/add");
//        WebElement element = driver.findElement(By.name("Add improceeding"));      
//        element.click();          
    }

    @When("^correct input for title \"([^\"]*)\", correct booktitle \"([^\"]*)\", correct year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void correct_inputs_are_given(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("bookTitle"));
        element.sendKeys(book);
        element = driver.findElement(By.name("year"));
        element.sendKeys(year);
        element = driver.findElement(By.name("givenAuthors"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @When("^correct input for title \"([^\"]*)\", correct booktitle \"([^\"]*)\", incorrect year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void incorrect_year_is_entered(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("bookTitle"));
        element.sendKeys(book);
        element = driver.findElement(By.name("year"));
        element.sendKeys(year);
        element = driver.findElement(By.name("givenAuthors"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @Then("^a new inproceeding is added to the site and a list of inproceedings is shown$")
    public void inproceeding_is_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("Inproceedings"));
    }

    @Then("^the inproceeding is not added to the site and create an improceeding page is shown$")
    public void inproceeding_is_not_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("an inproceeding."));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
