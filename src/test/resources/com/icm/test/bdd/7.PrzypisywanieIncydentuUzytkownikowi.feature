Feature: Przypisywanie incydentu do pracownika 
Jako administrator
Chce przypisać incydent w statusie Zgloszonym do pracownika
By był za niego odpowiedzialny

@ResetBazy
Scenario: Reset bazy danych
Given Wchodzac na strone http://localhost:8080/setup
When Klikam link "Inicjalizuj"
Then Przejsce na strone glowna

@PrzypisywanieIncydentu
Scenario: Przypisywanie incydentu w statusie Zgłoszony do pracownika 
Given Zresetowana baza danych
Given Bedac zalogowanym jako adminisrator z loginem "icm-admin@icm.com" i haslem "!1"
Given Bedac na liscie "Incydenty"
Given Majac incydent w statusie zgloszonym
When klikam przycisk Akcje
When Klikam link "Dodaj audyt"
When W polu "assigneeId" wybieram pracownika "icm-employee"
When Klikam przycisk "create"
Then Zgłoszenie zostaje przypisane do "icm-employee"
  Then Pojawi się komunikat Nowy audyt o id (\d+) został pomyślnie utworzony!
