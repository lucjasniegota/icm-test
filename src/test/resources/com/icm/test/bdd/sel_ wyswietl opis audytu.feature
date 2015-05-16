Feature: Web, wyświetlanie opisu audytu

Scenario: Powinien wyświetlić opis audytu
Given Aplikacja pod adresem http://localhost:8080/
Given Aktualny język to pl
Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
Given Sprawdzana strona pod ścieżką /incident/2.html
When Wybrana opcja Opis
Then Pojawi się okno z przyciskiem Close

  Scenario: Zamknięcie okna opisu audytu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/2.html
    When Wybrana opcja Opis
    When Kliknięta opcja (//button[@type='button'])[4]
    Then Zamknie się okno z przyciskiem Close
