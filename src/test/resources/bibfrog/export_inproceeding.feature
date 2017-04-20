Feature: As a user I want to export inproceedings from the site

  Scenario: User can export an inproceeding after it is succesfully added
    Given add inproceeding is selected
    When correct input for title "inproceeding", correct booktitle "booktitle", correct year "2016" and correct Authors "Arton lasit" are given
    When download button is pressed
    Then a file with correct authors "Arton lasit" is exported
