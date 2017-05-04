Feature: As a user I want to export inproceedings from the site

#  Scenario: User can export an inproceeding after it is succesfully added
#    Given BibFrog link to frontpage is clicked
#    When add inproceeding is selected
#    When correct input for title "inproceeding", correct booktitle "booktitle", correct year "2016" and correct Authors "Arton lasit" are given
#    When manage button is pressed
#    When confirm button is pressed
#    Then a file with correct author "Arton lasit" is exported

  Scenario: U can export all books in a single file
    Given BibFrog link to frontpage is clicked
    When add inproceeding is selected
    When correct input for title "inproceeding1", correct booktitle "booktitle", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add inproceeding is selected
    When correct input for title "inproceeding2", correct booktitle "booktitle", correct year "2015" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add inproceeding is selected
    When correct input for title "inproceeding3", correct booktitle "booktitle", correct year "2014" and correct Authors "Arton lasit" are given
    When download all button is pressed
    When confirm all button is pressed
    Then a file that contains "inproceeding1", "inproceeding2" and "inproceeding3" is created
