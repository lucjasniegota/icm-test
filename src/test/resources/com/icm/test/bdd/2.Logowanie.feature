Feature: Logowanie
  Jako niezalogowany użytkownik
  Podaję login i hasło przypisane do konta 
  Żeby zalogować się do systemu

  @LogowaniePracownika
  Scenario: Logowanie pracownika
    Given Majac strone startowa
    When Klikam link "Zaloguj się"
    When Wprowadzam dane
    |icm-user1@icm.com|!1|
   #| Pracownik1@pracownik.pl | !1 |
    When Klikam przycisk "signin"
    Then Widoczny przycisk "Logout"
    Then Przeniesienie na liste "Incydenty"

  @PusteLogowannie
  Scenario: Klikniecie przycisku Zaloguj sie w polskiej wersji jezykowej bez uzupełnienia danych
    Given Majac strone startowa
    When Klikam link "Polski"
    When Klikam link "Zaloguj się"
    When Klikam przycisk "signin"    
    Then Pojawi sie komunikat "Błąd logowania. Proszę spróbować ponownie."

@WprowadzenieNieprawidłowychDanychLogowania
Scenario: Złe dane użytkownika i hasło podczas logowania
    Given Majac strone startowa
	When Klikam link "Polski"
    When Klikam link "Zaloguj się"
    When Wprowadzam dane
    |aaa|ffff|
       When Klikam przycisk "signin"
   Then Pojawi sie komunikat "Błąd logowania. Proszę spróbować ponownie."
    