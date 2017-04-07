Feature: As a user I want to add inproceedings to the site

Scenario: user can add an inproceeding with a correct input
Given add inproceeding is selected
When correct input for title "inproceeding", correct booktitle "booktitle", correct year "2016" and correct Authors "Arton lasit" are given
Then a new inproceeding is added to the site and a list of inproceedings is shown

Scenario: user can not add an inproceeding with an incorrect input
Given add inproceeding is selected
When correct input for title "Inproceeding", correct booktitle "booktitle", incorrect year "asdf" and correct Authors "Arton lasit" are given
Then the inproceeding is not added to the site and create an improceeding page is shown