Feature: As a user I want be able to view all added refrences in a single page

  Scenario: user can add inproceedings, books and articles and view them in a single page
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article1", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "title1", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add inproceeding is selected
    When correct input for title "inproceeding1", correct booktitle "booktitle", correct year "2016" and correct Authors "Arton lasit" are given
    When List all in NavBar is clicked
    Then page contains "article1", "title1" and "inproceeding1"
