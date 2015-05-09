Feature: Web, dodawanie incydentu

  Scenario: Powinien wyświetlić błąd niepełnych danych formularza
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/create.html
    Given Dane formularza
      |type|inc1  |
      |description|desc1  |
    When Kliknięty przycisk create
    Then Pojawi się alert Nie można utworzyć nowego rekordu
    Then Błędne pole addressLine
    Then Błędne pole cityLine

  Scenario: Powinien dodać incydent
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/create.html
    Given Dane formularza
    |type|inc1  |
    |description|desc1  |
    |addressLine|add|
    |cityLine|cit|
    When Kliknięty przycisk create
    Then Pojawi się komunikat Nowy incydent o id (\d+) został pomyślnie utworzony!