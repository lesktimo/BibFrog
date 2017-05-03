Feature: As a user I want to be able to use a search bar to filter the site

  Scenario: using the search bar to limit hits
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article1", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article2", correct journal "journal", correct year "2015" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article3", correct journal "journal", correct year "2014" and correct Authors "Arton lasit" are given
    When search is clicked
    When "article1" is entered to the search bar
    Then list page contains "article1" and "2016"

  Scenario: using the search bar to limit hits correctly
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article1", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article2", correct journal "journal", correct year "2015" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article3", correct journal "journal", correct year "2014" and correct Authors "Arton lasit" are given
    When search is clicked
    When "article2" is entered to the search bar
    Then list page does not contain "article1" and "article3"

  Scenario: search word contained
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article1", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article2", correct journal "journal", correct year "2015" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article3", correct journal "journal", correct year "2014" and correct Authors "Arton lasit" are given
    When search is clicked
    When "article" is entered to the search bar
    Then page contains "article1", "article2" and "article3"

  Scenario: no hits
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article1", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article2", correct journal "journal", correct year "2015" and correct Authors "Arton lasit" are given
    When BibFrog in NavBar is clicked
    When add article is selected
    When correct input for title "article3", correct journal "journal", correct year "2014" and correct Authors "Arton lasit" are given
    When search is clicked
    When "" is entered to the search bar
    Then no results list page shown
