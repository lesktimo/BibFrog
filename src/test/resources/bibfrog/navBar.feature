Feature: as A user I want be able to use the navigation

  Scenario: using the NavBar to frontpage
    Given BibFrog link to frontpage is clicked
    When add inproceeding is selected
    When BibFrog in NavBar is clicked
    Then frontpage is shown

  Scenario: using the NavBar to references
    Given BibFrog link to frontpage is clicked
    When add inproceeding is selected
    When List all in NavBar is clicked
    Then a list of all references is shown

  Scenario: using the NavBar to list of inproceedings
    Given BibFrog link to frontpage is clicked
    When add inproceeding is selected
    When Inproceedings in NavBar is clicked
    Then a list of inproceedings is shown

  Scenario: using NavBar to list of books
    Given BibFrog link to frontpage is clicked
    When add book is selected
    When Books in NavBar is clicked
    Then a list of books is shown

  Scenario: using NavBar to list of articles
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When Articles in NavBar is clicked
    Then a list of articles is shown
