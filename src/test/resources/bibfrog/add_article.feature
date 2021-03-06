Feature: As a user I want to add articles to the site

  Scenario: user can add an article with a correct input
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    Then a new article is added to the site and a list of articles is shown

  Scenario: user can not add an article with an incorrect input
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When incorrect input for title "", correct journal "journal", incorrect year "asdf" and correct Authors "Arton lasit" are given
    Then the article is not added to the site and create an article page is shown

  Scenario: user can add an article with optional fields
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" and inputs for optional fields "17", "72", "6-7", "8", "This is a note" are entered
    When manage button is pressed
    When edit button is pressed
    Then page contains "17", "72", "6-7", "8" and "This is a note"
