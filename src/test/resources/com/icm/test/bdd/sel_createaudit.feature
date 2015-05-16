Feature: Web, dodawanie audytu

  Scenario: Powinien wyświetlić stronę dodawania audytu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/list.html
    When Kliknięta opcja //td[5]/div/button
    When Wybrana opcja Dodaj audyt
    Then Zostanie otwarta strona z przyciskiem create

  Scenario: Powinien dodać audyt
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/1/audit/create
    Given Dane formularza
      |assigneeId| |
      |description|desc1 |
    When Kliknięty przycisk create
    Then Pojawi się komunikat Nowy audyt o id (\d+) został pomyślnie utworzony!
    
      Scenario: Powinien nie dodać audytu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/1/audit/create
    Given Dane formularza
      |assigneeId| |
      |description|desc1 |
    When Kliknięty przycisk back
    Then Nie pojawi się komunikat
    
    