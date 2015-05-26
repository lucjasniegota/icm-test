Feature: Dodawanie pracownika przez administratora
  Jako administrator 
  Chcę założyć konto dla nowego pracownika
  By miał on dostęp do serwisu ICM

  @NiepoprawneDodawaniePracownika
  Scenario: Nieuzupelnienie pol formularza dodawania pracownika
    Given Bedac zalogowanym jako adminisrator z loginem "icm-admin@icm.com" i haslem "!1"
    Given Bedac na liscie "Incydenty"
    When Klikam link "Użytkownicy"
    When Klikam link "Dodaj pracownika"
    When Klikam przycisk "create"
    Then Pojawi sie komunikat "Nie można utworzyć konta!"
    Then Pojawi sie komunikat przy kazdym polu

  @DodawaniePracownika
  Scenario: Dodawanie pracownika przez administratora
  Given Zresetowana baza danych
    Given Bedac zalogowanym jako adminisrator z loginem "icm-admin@icm.com" i haslem "!1"
    Given Bedac na liscie "Incydenty"
    When Klikam link "Użytkownicy"
    When Klikam link "Dodaj pracownika"
    When Na formularzu dodawania pracownika wypełniam dane
      | Fields            | Value                   |
      | name              | Pracownik1              |
      | email             | Pracownik1@pracownik.pl |
      | phone             | 123456789               |
      | password          | !1                      |
      | confirmedPassword | !1                      |
    When Klikam przycisk "create"
    Then Pojawi sie komunikat "Konto zostało utworzone"
    Then "Pracownik1" zostaje dodoany do listy praconikow

  @LogowanieNowegoPracownika
  Scenario: Logowanie pracownika
    Given Majac strone startowa
    When Klikam link "Zaloguj się"
    When Wprowadzam dane
      | Pracownik1@pracownik.pl | !1 |
    When Klikam przycisk "signin"
    Then Widoczny przycisk "Logout"
    Then Przeniesienie na liste "Incydenty"
