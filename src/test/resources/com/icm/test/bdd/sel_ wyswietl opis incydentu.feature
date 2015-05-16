Feature: Web, wyświetlanie opisu incydentu

Scenario: Powinien wyświetlić opis incydentu
Given Aplikacja pod adresem http://localhost:8080/
Given Aktualny język to pl
Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
Given Sprawdzana strona pod ścieżką /incident/2.html
When Kliknięta opcja (//button[@type='button'])[2]
Then

  Scenario: Dodanie tekstu do opisu incydentu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/2.html
    When Kliknięta opcja (//button[@type='button'])[2]
    Then



  Scenario: Zamknięcie okna opisu incydentu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/2.html
    When Kliknięta opcja (//button[@type='button'])[2]
    Then
