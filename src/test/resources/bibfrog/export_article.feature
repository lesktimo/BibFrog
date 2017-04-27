Feature: As a user I want to export articles from the site

  Scenario: User can export an article after it is succesfully added
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When download button is pressed
    When confirm button is pressed
    Then a file with correct author "Arton lasit" is exported

  Scenario: User can export all articles in a single file
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article1", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article2", correct journal "journal", correct year "2015" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article3", correct journal "journal", correct year "2014" and correct Authors "Arton lasit" are given
    When download all button is pressed
    When confirm button is pressed
    Then a file that contains page contains "article1", "article2" and "article3" is created
