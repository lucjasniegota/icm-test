Feature: Web, dodawania pliku do incydentu

Scenario: Powinien wyświetlić szczegóły incydentu
Given Aplikacja pod adresem http://localhost:8080/
Given Aktualny język to pl
Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
Given Sprawdzana strona pod ścieżką /incident/list.html
When Kliknięta opcja //td[5]/div/button
When Wybrana opcja Szczegóły
Then Zostanie otwarta strona z tekstem Szczegóły

Scenario: Powinien dodać plik
Given Aplikacja pod adresem http://localhost:8080/
Given Aktualny język to pl
Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
Given Sprawdzana strona pod ścieżką /incident/2.html
Given Dodany plik C:\\Users\\domowy\\Desktop\\projekty moje\\bdd-test\\plik\\given.txt
When Kliknięta opcja //td/form/button
Then Pojawi się komunikat File (.+) uploaded successfully
Then Pojawi się plik given.txt

  Scenario: Powinien nie dodać pliku
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/2.html
    When Kliknięta opcja //td/form/button
    Then Pojawi się komunikat (.+)file.empty_pl(.+)


