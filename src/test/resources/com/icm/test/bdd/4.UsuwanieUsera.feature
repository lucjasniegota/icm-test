Feature: Usuwanie uzytkownika
Jako administrator
chce moc usuwac pracownika lub uzytkownika
by nieupowaznione osoby nie mialy dostepu do systemu ICM

@Usuwanie
Scenario: Usuwanie uzytkownika z listy
Given Zresetowana baza danych
Given Bedac zalogowanym jako adminisrator z loginem "icm-admin@icm.com" i haslem "!1"
Given Bedac na liscie "Incydenty"
Given Klikam link "Użytkownicy"
When Wybieram akcje Delete przy uzytkowniku "icm-employee"
Then powinien pojawic sie komunikat