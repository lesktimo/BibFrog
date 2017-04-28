Feature: As a user I want to be able to edit inproceedings after adding them to the site

  Scenario: User can add an inproceeding and edit it afterwards
    Given BibFrog link to frontpage is clicked
    When add inproceeding is selected
    When correct input for title "inproceeding", correct booktitle "booktitle", correct year "2016" and correct Authors "Arton lasit" are given
    When manage button is pressed
    When edit button is pressed
    When correct input for title "editedInproceeding", correct booktitle "editedBooktitle", correct year "2015" and correct Authors "Arton muokatut lasit" are given
    Then a list of articles is shown

  Scenario: User can add an inproceeding and cannot edit it with incorrect inputs afterwards
    Given BibFrog link to frontpage is clicked
    When add inproceeding is selected
    When correct input for title "inproceeding", correct booktitle "booktitle", correct year "2016" and correct Authors "Arton lasit" are given
    When manage button is pressed
    When edit button is pressed
    When incorrect input for title "", correct booktitle "booktitle", incorrect year "asdf" and correct Authors "Arton lasit" are given
    Then inproceeding is not edited and edit page is shown