package bibfrog;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class StepDefs {

    String baseUrl = "http://localhost:8080";

    WebDriver driver = new HtmlUnitDriver();

    @Given("^add inproceeding is selected$")
    public void add_inproceeding_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.name("createInproceeding"));
        element.click();
    }

    @Given("^add book is selected$")
    public void add_book_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.name("createBook"));
        element.click();
    }

    @Given("^add article is selected$")
    public void add_article_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.name("createArticle"));
        element.click();
    }

    @When("^correct input for title \"([^\"]*)\", correct booktitle \"([^\"]*)\", correct year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void correct_inputs_for_inproceeding_are_given(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("bookTitle"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("givenAuthors"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @When("^correct input for title \"([^\"]*)\", correct booktitle \"([^\"]*)\", incorrect year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void incorrect_year_for_inproceeding_is_entered(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("bookTitle"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("givenAuthors"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @When("^correct input for publisher \"([^\"]*)\", correct title \"([^\"]*)\", correct year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void correct_inputs_for_book_are_given(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("publisher"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("title"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("author"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @When("^correct input for publisher \"([^\"]*)\", correct title \"([^\"]*)\", incorrect year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void incorrect_year_for_book_is_entered(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("publisher"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("title"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("author"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @When("^correct input for title \"([^\"]*)\", correct journal \"([^\"]*)\", correct year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void correct_inputs_for_article_are_given(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("journal"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("author"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @When("^correct input for title \"([^\"]*)\", correct journal \"([^\"]*)\", incorrect year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void incorrect_year_for_article_is_entered(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("journal"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("author"));
        element.sendKeys(authors);
        element = driver.findElement(By.className("btn"));
        element.submit();
    }

    @When("^download button is pressed$")
    public void download_button_is_pressed() {
        WebElement element = driver.findElement(By.className("btn"));
        element.click();
    }
    @Then("^a file with correct author \"([^\"]*)\" is exported$")
    public void a_file_with_correct_author(String author) throws FileNotFoundException {
        File file = new File("src/bibtex.bib");
        Scanner reader = new Scanner(file);
        String fileData = "";
        while (reader.hasNextLine()) {
            fileData += reader.nextLine();
        }
        System.out.println(fileData);
        assertTrue(fileData.contains(author));
    }

    @Then("^a new inproceeding is added to the site and a list of inproceedings is shown$")
    public void inproceeding_is_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("Inproceedings"));
    }

    @Then("^the inproceeding is not added to the site and create an improceeding page is shown$")
    public void inproceeding_is_not_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("an inproceeding."));
    }

    @Then("^a new book is added to the site and a list of books is shown$")
    public void book_is_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("books"));
    }

    @Then("^the book is not added to the site and create a book page is shown$")
    public void book_is_not_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("a book."));
    }

    @Then("^a new article is added to the site and a list of articles is shown$")
    public void article_is_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("articles"));
    }

    @Then("^the article is not added to the site and create an article page is shown$")
    public void article_is_not_added() throws Throwable {
        assertTrue(driver.getPageSource().contains("an article."));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
