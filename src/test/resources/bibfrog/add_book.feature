Feature: As a user I want to add books to the site

  Scenario: user can add a book with a correct input
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "title", correct year "2016" and correct Authors "Arton lasit" are given
    Then a new book is added to the site and a list of books is shown

  Scenario: user can not add a book with an incorrect input
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When incorrect input for publisher "", correct title "title", incorrect year "asdf" and correct Authors "Arton lasit" are given
    Then the book is not added to the site and create a book page is shown
