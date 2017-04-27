Feature: As a user I want to be abe to edit articles after adding them to the site

  Scenario: User can add an article and edit it afterwards
    Given BibFrog link to frontpage is clicked
    When add article is selected
    When correct input for title "article", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
    When manage button is pressed
    When edit button is pressed
    When correct input for title "editedArticle", correct journal "editedjournal", correct year "2015" and correct Authors "Arton muokatut lasit" are given
#    Then listing page contains fields "editedArticle", "editedJournal", "2015" and "Arton muokatut lasit"
    Then a list of articles is shown

#  Scenario: User can add an article and cannot edit it with incorrect inputs afterwards
#    Given BibFrog link to frontpage is clicked
#    When add article is selected
#    When correct input for title "article", correct journal "journal", correct year "2016" and correct Authors "Arton lasit" are given
#    When manage button is pressed
#    When edit button is pressed
#    When correct input for title "article", correct journal "journal", incorrect year "ee" and correct Authors "Arton lasit" are given
#    Then article is not edited and edit page is shown
