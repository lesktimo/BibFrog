Feature: As a user I want to export articles from the site

  Scenario: User can export an article after it is succesfully added
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When download button is pressed
    Then a file with correct author "Arton lasit" is exported
