Feature: As a user I want to export books from the site

  Scenario: User can export an book after it is succesfully added
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "title", correct year "2016" and correct Authors "Arton lasit" are given
    When download button is pressed
    When confirm button is pressed
    Then a file with correct author "Arton lasit" is exported

  Scenario: U can export all books in a single file
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "book1", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "book2", correct year "2015" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "book3", correct year "2014" and correct Authors "Arton lasit" are given
    When download all button is pressed
    Then a file that contains page contains "book1", "book2" and "book3" is created
