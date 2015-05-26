Feature: Web, wyświetlanie opisu incydentu

Scenario: Powinien wyświetlić opis incydentu
Given Aplikacja pod adresem http://localhost:8080/
Given Aktualny język to pl
Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
Given Sprawdzana strona pod ścieżką /incident/2.html
When Kliknięta opcja (//button[@type='button'])[2]
Then Na stronie pojawi się tekst Lorem ipsum dolor sit amet, consectetur adipiscing elit.


  Scenario: Dodanie tekstu do opisu incydentu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/2.html
    When Kliknięta opcja (//button[@type='button'])[2]
    When Kliknięta opcja //td/div
    When Wpisać tekst nowy wpis do incydentu
    When Kliknięta opcja //h2
    Then Na stronie pojawi się tekst nowy wpis do incydentu



  Scenario: Zamknięcie okna opisu incydentu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/2.html
    When Kliknięta opcja (//button[@type='button'])[2]
    When Kliknięta opcja (//button[@type='button'])[2]
    Then Na stronie nie będzie tekstu nowy wpis do incydentu
