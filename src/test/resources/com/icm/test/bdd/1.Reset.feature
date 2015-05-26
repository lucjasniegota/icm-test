Feature: Reset bazy danych
Jako niezalogowany uzytkownik
Po wejsciu na stronę "http://localhost:8080/setup"
Baza danych programu ICM zostanie zresetowana
Aby testy były powtarzalne

@ResetBazy
Scenario: Reset bazy danych
Given Wchodzac na strone http://localhost:8080/setup
When Klikam link "Inicjalizuj"
Then Przejsce na strone glowna