Feature: As a user I want to be able to edit books after adding them to the site

  Scenario: User can add a book and edit it afterwards
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "title", correct year "2016" and correct Authors "Arton lasit" are given
    When manage button is pressed
    When edit button is pressed
    When correct input for publisher "editedPublisher", correct title "editedTitle", correct year "2015" and correct Authors "Arton muokatut lasit" are given
    Then a list of articles is shown

  Scenario: User can add a book and cannot edit it with incorrect inputs afterwards
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When correct input for publisher "publisher", correct title "title", correct year "2016" and correct Authors "Arton lasit" are given
    When manage button is pressed
    When edit button is pressed
    When incorrect input for publisher "", correct title "title", incorrect year "asdf" and correct Authors "Arton lasit" are given
    Then book is not edited and edit page is shown
