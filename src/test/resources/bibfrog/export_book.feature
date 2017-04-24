Feature: As a user I want to export books from the site

  Scenario: User can export an book after it is succesfully added
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "title", correct year "2016" and correct Authors "Arton lasit" are given
    When download button is pressed
    When confirm button is pressed
    Then a file with correct author "Arton lasit" is exported
