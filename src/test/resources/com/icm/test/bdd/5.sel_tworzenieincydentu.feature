Feature: Web, dodawanie incydentu

  Scenario: Powinien wyświetlić stronę dodawania audytu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/list.html
    When Kliknięta opcja //td[5]/div/button
    When Wybrana opcja Utwórz incydent
    Then Zostanie otwarta strona z przyciskiem create
    Then Zostanie otwarta strona z przyciskiem back



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

  Scenario: Powinien wyświetlić błąd za dużej ilości znaków w polach
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/create.html
    Given Dane formularza
      |type|vppppppppppppppp pppppppppp ppppppppppppppppppppppppp pppppppppppppppppppppppppp pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp pppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppp pppppppppppppppppppppppppppppppppggggggggg |
      |description|vppppppppppppppppppp pppppppppppppppppppppppp pppppppppppppppppppppppppppp ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppggggggggg  |
      |addressLine|vppppppppppppppppppp ppppppppppppppppppppppp pppppppppppppppppppppppppppppp pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppggggggggg  |
      |cityLine|vppppppppppppppppppp ppppppppppppppppppppppppp pppppppppppppppppppppppppppppppp pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppgggggggggpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppggggggggg  |
    When Kliknięty przycisk create
    Then Pojawi się alert Nie można utworzyć nowego rekordu
    Then Błędne pole type
    Then Błędne pole description
    Then Błędne pole addressLine
    Then Błędne pole cityLine
    Then Pojawi się error w Typie i Opisie Maksymalny rozmiar dla pola (50) został przekroczony
    Then Pojawi się error w Adresie Maksymalny rozmiar dla pola (255) został przekroczony


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

  Scenario: Nie powinien dodać incydentu
    Given Aplikacja pod adresem http://localhost:8080/
    Given Aktualny język to pl
    Given Użytkownik zalogowany icm-admin@icm.com z hasłem !1
    Given Sprawdzana strona pod ścieżką /incident/create.html
    Given Dane formularza
      |type|inc1  |
      |description|desc1  |
      |addressLine|add|
      |cityLine|cit|
    When Kliknięty przycisk back
    Then Nie pojawi się komunikat
