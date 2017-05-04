Feature: As a user I want to be able to import references from ACM library

  Scenario: incorrect url
    Given BibFrog link to frontpage is clicked
    When search is clicked
    When "" is entered to ACMquery
    Then no results list page shown

  Scenario: importing an unimplemented reference
    Given BibFrog link to frontpage is clicked
    When search is clicked
    When "" is entered to ACMquery
    Then no results list page shown

  Scenario: importing a book
    Given BibFrog link to frontpage is clicked
    When search is clicked
    When "2915031" is entered to ACMquery
    When Books in NavBar is clicked
    Then page contains "Zhai", "Text Data" and "2016"

  Scenario: importing an article
    Given BibFrog link to frontpage is clicked
    When search is clicked
    When "2950065" is entered to ACMquery
    When Articles in NavBar is clicked
    Then page contains "Sorva", "Break" and "ACM"

  Scenario: importing an inproceeding
    Given BibFrog link to frontpage is clicked
    When search is clicked
    When "2380613" is entered to ACMquery
    When Inproceedings in NavBar is clicked
    Then page contains "Three Years", "Luukkainen" and "2012"
