package bibfrog;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class StepDefs {

    String baseUrl = "http://localhost:8080";

    WebDriver driver = new HtmlUnitDriver(true);

    @Given("^BibFrog link to frontpage is clicked$")
    public void navBar_bibfrog_frontpage() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.name("frontpage"));
        element.click();
    }

    //WHEN_______________________________________________________________________________________
    @When("^add inproceeding is selected$")
    public void add_inproceeding_selected() throws Throwable {
        WebElement element = driver.findElement(By.name("createInproceeding"));
        element.click();
    }

    @When("^add book is selected$")
    public void add_book_selected() throws Throwable {
        WebElement element = driver.findElement(By.name("createBook"));
        element.click();
    }

    @When("^add article is selected$")
    public void add_article_selected() throws Throwable {
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
        element = driver.findElement(By.name("authors"));
        element.sendKeys(authors);
        element = driver.findElement(By.name("addButton"));
        element.submit();
    }

    @When("^incorrect input for title \"([^\"]*)\", correct booktitle \"([^\"]*)\", incorrect year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void incorrect_title_for_inproceeding_is_entered(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("bookTitle"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("authors"));
        element.sendKeys(authors);
        element = driver.findElement(By.name("addButton"));
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
        element = driver.findElement(By.name("authors"));
        element.sendKeys(authors);
        element = driver.findElement(By.name("addButton"));
        element.submit();
    }

    @When("^incorrect input for publisher \"([^\"]*)\", correct title \"([^\"]*)\", incorrect year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void incorrect_year_for_book_is_entered(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("publisher"));
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("title"));
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.sendKeys(year);
        element = driver.findElement(By.name("authors"));
        element.sendKeys(authors);
        element = driver.findElement(By.name("addButton"));
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
        element = driver.findElement(By.name("authors"));
        element.sendKeys(authors);
        element = driver.findElement(By.name("addButton"));
        element.submit();
    }

    @When("^incorrect input for title \"([^\"]*)\", correct journal \"([^\"]*)\", incorrect year \"([^\"]*)\" and correct Authors \"([^\"]*)\" are given$")
    public void incorrect_year_for_article_is_entered(String inproceeding, String book, String year, String authors) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.clear();
        element.sendKeys(inproceeding);
        element = driver.findElement(By.name("journal"));
        element.clear();
        element.sendKeys(book);
        element = driver.findElement(By.name("publishYear"));
        element.clear();
        element.sendKeys(year);
        element = driver.findElement(By.name("authors"));
        element.clear();
        element.sendKeys(authors);
        element = driver.findElement(By.name("addButton"));
        element.submit();
    }

    @When("^manage button is pressed$")
    public void manage_button_is_pressed() throws InterruptedException {
        WebElement element = driver.findElement(By.name("downloadBibtex"));
        element.click();
    }

    @When("^edit button is pressed$")
    public void edit_button_is_pressed() throws InterruptedException {
        driver.switchTo().activeElement();
        sleep(1000);
        WebElement element = driver.findElement(By.name("editButton"));
        element.click();
    }

    @When("^confirm button is pressed$")
    public void confirm_button_is_pressed() throws InterruptedException {
        driver.switchTo().activeElement();
        sleep(1000);
        WebElement element = driver.findElement(By.name("confirmDownload"));
        element.click();

    }

    @When("^confirm all button is pressed$")
    public void confirm_all_button_is_pressed() throws InterruptedException {
        driver.switchTo().activeElement();
        sleep(1000);
        WebElement element = driver.findElement(By.name("confirmAllDownload"));
        element.click();

    }

    @When("^BibFrog in NavBar is clicked$")
    public void navBar_bibfrog() {
        WebElement element = driver.findElement(By.name("frontpage"));
        element.click();
    }

    @When("^Articles in NavBar is clicked$")
    public void navBar_article() {
        WebElement element = driver.findElement(By.linkText("Articles"));
        element.click();
    }

    @When("^Inproceedings in NavBar is clicked$")
    public void navBar_inpro() {
        WebElement element = driver.findElement(By.linkText("Inproceedings"));
        element.click();
    }

    @When("^Books in NavBar is clicked$")
    public void navBar_books() {
        WebElement element = driver.findElement(By.linkText("Books"));
        element.click();
    }

    @When("^List all in NavBar is clicked$")
    public void navBar_ListAll() {
        WebElement element = driver.findElement(By.linkText("List all"));
        element.click();
    }

    @When("^download all button is pressed$")
    public void downloadAllReferences() {
        WebElement element = driver.findElement(By.name("downloadAll"));
        element.click();
    }

    @When("^search is clicked$")
    public void search_clicked() {
        WebElement element = driver.findElement(By.name("searchButton"));
        element.click();
    }

    @When("^\"([^\"]*)\" is entered to the search bar$")
    public void searchWordEntered(String word) throws InterruptedException {
        driver.switchTo().activeElement();
        sleep(1000);
        WebElement element = driver.findElement(By.name("query"));
        element.sendKeys(word);
        element = driver.findElement(By.name("confirmSearch"));
        element.click();
    }

    @When("^\"([^\"]*)\" is entered to ACMquery$")
    public void ACMsearchWordEntered(String url) throws InterruptedException {
        driver.switchTo().activeElement();
        sleep(1000);
        WebElement element = driver.findElement(By.name("queryACM"));
        element.sendKeys(url);
        element = driver.findElement(By.name("confirmSearchACM"));
        element.click();
    }

    // THEN_______________________________________________________________________
    @Then("^frontpage is shown$")
    public void frontpage_shown() {
        assertTrue(driver.getPageSource().contains("Create article references"));
    }

    @Then("^a file with correct author \"([^\"]*)\" is exported$")
    public void a_file_with_correct_author(String author) throws FileNotFoundException {
        String fileData = readFile();
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

    @Then("^article is not edited and edit page is shown$")
    public void article_is_not_edited() throws Throwable {
        assertTrue(driver.getPageSource().contains("an article."));
    }

    @Then("^book is not edited and edit page is shown$")
    public void book_is_not_edited() throws Throwable {
        assertTrue(driver.getPageSource().contains("a book."));
    }

    @Then("^inproceeding is not edited and edit page is shown$")
    public void inproceeding_is_not_edited() throws Throwable {
        assertTrue(driver.getPageSource().contains("an inproceeding."));
    }

    @Then("^a file with correct fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" is exported$")
    public void optional_fields_check(String arg1, String arg2, String arg3, String arg4, String arg5) throws FileNotFoundException {
        String fileData = readFile();
        System.out.println(fileData);
        assertTrue(fileData.contains(arg1));
        assertTrue(fileData.contains(arg2));
        assertTrue(fileData.contains(arg3));
        assertTrue(fileData.contains(arg4));
        assertTrue(fileData.contains(arg5));
    }

    @Then("^list all does not contain \"([^\"]*)\"$")
    public void unimplemented_reference(String title) {
        assertTrue(driver.getPageSource().contains(title));
    }

    @Then("^a list of all references is shown$")
    public void references_shown() throws Throwable {
        assertTrue(driver.getPageSource().contains("References"));
    }

    @Then("^list page contains \"([^\"]*)\" and \"([^\"]*)\"")
    public void search_tool_works(String arg1, String arg2) {
        assertTrue(driver.getPageSource().contains(arg1));
        assertTrue(driver.getPageSource().contains(arg2));

    }

    @Then("^list page does not contain \"([^\"]*)\" and \"([^\"]*)\"$")
    public void search_tool_limits_correctly(String arg1, String arg2) {
        assertTrue(!driver.getPageSource().contains(arg1));
        assertTrue(!driver.getPageSource().contains(arg2));
    }

    @Then("^a list of inproceedings is shown$")
    public void inproceedings_shown() throws Throwable {
        assertTrue(driver.getPageSource().contains("Inproceedings"));
    }

    @Then("^page contains \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void optionalfields_are_shown(String arg1, String arg2, String arg3, String arg4, String arg5) {
        assertTrue(driver.getPageSource().contains(arg1));
        assertTrue(driver.getPageSource().contains(arg2));
        assertTrue(driver.getPageSource().contains(arg3));
        assertTrue(driver.getPageSource().contains(arg4));
        assertTrue(driver.getPageSource().contains(arg5));

    }

    @Then("^a list of books is shown$")
    public void books_shown() throws Throwable {
        assertTrue(driver.getPageSource().contains("Books"));
    }

    @Then("^no results list page shown$")
    public void no_results() throws Throwable {
        assertTrue(driver.getPageSource().contains("No results"));
    }

    @Then("^a list of articles is shown$")
    public void articles_shown() throws Throwable {
        assertTrue(driver.getPageSource().contains("Articles"));
    }

//    @Then("^listing page contains fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
//    public void successful_edit(String field1, String field2, int year, String field3) {
//        assertTrue(driver.getPageSource().contains("field1"));
//        assertTrue(driver.getPageSource().contains("field2"));
//        assertTrue(driver.getPageSource().contains("year"));
//        assertTrue(driver.getPageSource().contains("field3"));
//
//    }
    @Then("^page contains \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void added_references_shown(String article, String book, String inproceeding) throws Throwable {
        assertTrue(driver.getPageSource().contains(article));
        assertTrue(driver.getPageSource().contains(book));
        assertTrue(driver.getPageSource().contains(inproceeding));

    }

    @Then("^a file that contains \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" is created$")
    public void added_references_downloaded(String title1, String title2, String title3) throws FileNotFoundException {
        String fileData = readFile();
        assertTrue(fileData.contains(title1));
        assertTrue(fileData.contains(title2));
        assertTrue(fileData.contains(title3));
    }

    public void sleep(int ms) throws InterruptedException {
        Thread.sleep(ms);
    }

    public String readFile() throws FileNotFoundException {
        File file = new File("src/bibtex.bib");
        Scanner reader = new Scanner(file);
        String fileData = "";
        while (reader.hasNextLine()) {
            fileData += reader.nextLine();
        }
        return fileData;
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
