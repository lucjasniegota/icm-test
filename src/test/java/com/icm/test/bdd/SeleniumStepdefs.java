package com.icm.test.bdd;

import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDocument;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.org.apache.xml.internal.security.utils.IdResolver.getElementById;
import static org.junit.Assert.*;

/**
 * Created by lucja on 09.05.15.
 */
public class SeleniumStepdefs {

	private String appAddress;
	private String lang;
	private WebDriver webDriver;
	private String checkPath;
	private String user;
	private String password;
	private String tekst;
	private String plik;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();






	public SeleniumStepdefs() {
		this.webDriver = new SharedDriver();
	}

	@Given("^Dane formularza$")
	public void Dane_formularza(DataTable arg1) throws Throwable {
		List<String> textElements = Arrays.asList("input", "textarea");
		Map<String, String> values = arg1.asMap(String.class, String.class);
		for (Map.Entry<String, String> val : values.entrySet()) {
			WebElement element = webDriver.findElement(By.id(val.getKey()));
			if (textElements.contains(element.getTagName())) {
				element.clear();
				element.sendKeys(val.getValue());
			} else if ("select".equals(element.getTagName())) {
				System.out.println("select not supported yet");
			} else {
				System.out.println(element.getTagName() + " not supported");
			}
		}
	}

	@Given("^Aplikacja pod adresem (.+)/$")
	public void Aplikacja_pod_adresem_http_localhost_(String arg1)
			throws Throwable {
		this.appAddress = arg1;
		webDriver.get(this.appAddress);
	}

	@Given("^Aktualny język to (.+)$")
	public void Aktualny_język_to_pl(String arg1) throws Throwable {
		this.lang = arg1;
	}

	@Given("^Użytkownik zalogowany (.+) z hasłem (.+)$")
	public void Użytkownik_zalogowany_z_haslem(String arg1, String arg2)
			throws Throwable {
		this.user = arg1;
		this.password = arg2;
		webDriver.navigate().to(this.appAddress + "/signin");
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.findElement(By.id("inputEmail")).clear();
		webDriver.findElement(By.id("inputEmail")).sendKeys(this.user);
		webDriver.findElement(By.id("inputPassword")).clear();
		webDriver.findElement(By.id("inputPassword")).sendKeys(this.password);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.findElement(By.id("signin")).click();
	}

	@Given("^Sprawdzana strona pod ścieżką (.+)$")
	public void Sprawdzana_strona_pod_ścieżką_incident_html(String arg1)
			throws Throwable {
		this.checkPath = arg1;
		webDriver.navigate().to(this.appAddress + this.checkPath);
	}
	@Given("^Dodany plik (.+)$")
	public void Dodany_plik(String arg1) throws Throwable {
		this.plik=arg1;
		webDriver.findElement(By.id("file")).sendKeys(this.plik);
	}



	@Given("^Zresetowana baza danych$")
	public void majac_zresetowana_bazedanych() throws Throwable {
		webDriver.navigate().to("http://localhost:8080/setup");
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.findElement(By.linkText("Inicjalizuj")).click();
		assertEquals("Zarządzanie incydentami", webDriver.findElement(By.cssSelector("h1")).getText());

	}

	@Given("^Majac strone startowa$")
	public void Majac_stron_logowania() throws Throwable {
		webDriver.navigate().to("http://localhost:8080/");
	}

	@Given("^Bedac zalogowanym jako adminisrator z loginem \"(.*?)\" i haslem \"(.*?)\"$")
	public void bedac_zalogowanym_jako_adminisrator_z_loginem_i_haslem(String arg1, String arg2) throws Throwable {


		webDriver.navigate().to("http://localhost:8080/");
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.findElement(By.linkText("Zaloguj się")).click();
		this.user=arg1;
		this.password=arg2;
		webDriver.findElement(By.id("inputEmail")).clear();
		webDriver.findElement(By.id("inputEmail")).sendKeys(this.user);
		webDriver.findElement(By.id("inputPassword")).clear();
		webDriver.findElement(By.id("inputPassword")).sendKeys(this.password);
		webDriver.findElement(By.id("signin")).click();
	}

	@Given("^Bedac na liscie \"(.*?)\"$")
	public void bedac_na_liscie_incydentow(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.cssSelector("h2")).getText());

	}

	@Given("^Majac incydent w statusie zgloszonym$")
	public void majac_incydent_w_statusie_zgloszonym() throws Throwable {
		assertEquals(
				"Zgłoszony",
				webDriver.findElement(
						By.xpath("//table[@id='incidents']/tbody/tr/td[4]"))
						.getText());

	}
	@Given("^Wchodzac na strone http://localhost:(\\d+)/setup$")
	public void wchodzac_na_strone_http_localhost_setup(int arg1) throws Throwable {
		webDriver.navigate().to("http://localhost:8080/setup");
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@When("^Kliknięty przycisk (.+)$")
	public void Kliknięty_przycisk_save(String arg1) throws Throwable {
		webDriver.findElement(By.id(arg1)).click();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	@When("^Kliknięta opcja (.+)$")
	public void Kliknieta_opcja(String arg1) throws Throwable {
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.findElement(By.xpath(arg1)).click();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@When("^Wybrana opcja (.+)$")
	public void Wybrana_opcja(String arg1) throws Throwable	{
		webDriver.findElement(By.linkText(arg1)).click();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@When("^Wpisać tekst (.+)$")
	public void Wpisac_tekst(String arg1) throws Throwable {
		this.tekst = arg1;
		webDriver.findElement(By.xpath("//textarea")).clear();
		webDriver.findElement(By.xpath("//textarea")).sendKeys(this.tekst);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@When("^Wprowadzam dane")
	public void Loguje_sie_do_systemu_poprzez_login_i_haslo_przypisane_do_konta_administratora(DataTable table)
			throws Throwable {
		List<List<String>> data = table.raw();

		webDriver.findElement(By.id("inputEmail")).clear();
		webDriver.findElement(By.id("inputEmail")).sendKeys(data.get(0).get(0));
		webDriver.findElement(By.id("inputPassword")).clear();
		webDriver.findElement(By.id("inputPassword")).sendKeys(data.get(0).get(1));

	}

	@When("^Klikam link \"(.*?)\"$")
	public void klikajac_przycisk_Inicjalizuj(String arg1) throws Throwable {
		webDriver.findElement(By.linkText(arg1)).click();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@When("^klikam przycisk Akcje$")
	public void klikam_przycisk_Akcje() throws Throwable {
		webDriver.findElement(
				By.xpath("//table[@id='incidents']/tbody/tr/td[5]/div/button"))
				.click();

	}

	@When("^W polu \"(.*?)\" wybieram pracownika \"(.*?)\"$")
	public void w_polu_przypisz_do_wybieram_pracownika(String arg1, String arg2) throws Throwable {
		new Select(webDriver.findElement(By.id(arg1))).selectByVisibleText(arg2);

	}


	@When("^Wybieram akcje Delete przy uzytkowniku \"(.*?)\"$")
	public void Wybieram_akcje_Delete_przy_uzytkowniku(String arg1) throws Throwable {

		assertEquals(arg1, webDriver.findElement(By.xpath("//table[@id='accounts']/tbody/tr[2]/td[2]")).getText());
		webDriver.findElement(By.xpath("//table[@id='accounts']/tbody/tr[2]/td[6]/div/button")).click();
		webDriver.findElement(By.cssSelector("#action-menu-2 > li > a")).click();

	}

	@When("^Na formularzu dodawania pracownika wypełniam dane$")
	public void na_formularzu_dodawania_pracownika_wypełniam_dane(DataTable table) throws Throwable {

		List<List<String>> data = table.raw();

		webDriver.findElement(By.id("name")).clear();
		webDriver.findElement(By.id("name")).sendKeys(data.get(1).get(1));
		webDriver.findElement(By.id("email")).clear();
		webDriver.findElement(By.id("email")).sendKeys(data.get(2).get(1));
		webDriver.findElement(By.id("phone")).clear();
		webDriver.findElement(By.id("phone")).sendKeys(data.get(3).get(1));
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("password")).sendKeys(data.get(4).get(1));
		webDriver.findElement(By.id("confirmedPassword")).clear();
		webDriver.findElement(By.id("confirmedPassword")).sendKeys(data.get(5).get(1));

	}

	@When("^Klikam przycisk \"(.*?)\"$")
	public void klikam_przycisk_dodaj_pracownika(String arg1) throws Throwable{

		webDriver.findElement(By.id(arg1)).click();

	}

	@Then("^Przejsce na strone glowna$")
	public void przejsce_na_strone_glowna() throws Throwable {
		assertEquals("Zarządzanie incydentami", webDriver.findElement(By.cssSelector("h1")).getText());

	}
	@Then("^Zgłoszenie zostaje przypisane do \"(.*?)\"$")
	public void zgłoszenie_zostaje_przypisane_do_pracownika(String arg1) throws Throwable {
		assertEquals(arg1,
				webDriver.findElement(By.xpath("//tr[8]/td/address/strong"))
						.getText());

	}

	@Then("^Pojawia sie komunikat informujący o dodaniu audytu$")
	public void pojawia_sie_komunikat_informujący_o_dodaniu_audytu()
			throws Throwable {
		assertEquals("Nowy audyt o id 7 został pomyślnie utworzony!", webDriver
				.findElement(By.id("alert")).getText());

	}
	@Then("^powinien pojawic sie komunikat$")
	public void powinien_pojawia_sie_komunikat_potwierdzajacy_usuniecie_uzytkownika() throws Throwable {
		assertEquals("Account deleted!", webDriver.findElement(By.id("alert")).getText());
	}

	@Then("^Zostanie wyswietlony komunikat (.+)$")
	public void zostanie_wyswietlony_komunikat(String arg1) throws Throwable {
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		assertEquals("Konto zostało utworzone", webDriver.findElement(By.id("alert")).getText());
		//assertEquals(arg1, webDriver.findElement(By.id("alert")).getText());
	}

	@Then("^\"(.*?)\" zostaje dodoany do listy praconikow$")
	public void pracownik_zostaje_dodoany_do_listy_praconikow(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.xpath("//table[@id='accounts']/tbody/tr[3]/td[2]")).getText());
	}

	@Then("^Pojawi sie komunikat przy kazdym polu$")
	public void pojawi_sie_komunikat_przy_kazdym_polu() throws Throwable {
		assertEquals("Wartość nie może być pusta", webDriver.findElement(By.cssSelector("span.help-block")).getText());
		assertEquals("Wartość nie może być pusta", webDriver.findElement(By.xpath("//div[2]/div/span")).getText());
		assertEquals("Wartość nie może być pusta", webDriver.findElement(By.xpath("//div[3]/div")).getText());
		assertEquals("Wartość nie może być pusta", webDriver.findElement(By.xpath("//div[4]/div/span")).getText());
		assertEquals("Wartość nie może być pusta", webDriver.findElement(By.xpath("//div[5]/div/span")).getText());

	}

	@Then("^Pojawi sie komunikat \"(.*?)\"$")
	public void pojawi_sie_komunikat(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.id("alert")).getText());
	}


	@Then("^Pojawi się alert \"(.*?)\"$")
	public void pojawi_się_alert(String arg1) throws Throwable {
		assertEquals("Nie można utworzyć konta!", webDriver.findElement(By.id("alert")).getText());

	}
	@Then("^Widoczny przycisk \"(.*?)\"$")
	public void widoczny_przycisk_wylogowania(String arg1) throws Throwable {
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		assertEquals(arg1, webDriver.findElement(By.linkText(arg1)).getText());
	}

	@Then("^Przeniesienie na liste \"(.*?)\"$")
	public void przeniesienie_na_lieste(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.cssSelector("h2")).getText());

	}

	@Then("^Pojawi się komunikat (.+)$")
	public void Pojawi_się_komunikat(String arg1) throws Throwable {
		assertNotNull(webDriver.findElement(By.id("alert")));
		Matcher m = Pattern.compile(arg1).matcher(
				webDriver.findElement(By.id("alert")).getText());
		assertTrue(m.find());
	}

	@Then("^Nie pojawi się komunikat$")
	public void Nie_pojawi_się_komunikat() throws Throwable {
		assertNull(webDriver.findElement(By.id("alert")));
	}


	@Then("^Pojawi się alert (.+)$")
	public void Pojawi_sie_alert(String arg1) throws Throwable {
		assertNotNull(webDriver.findElement(By.id("alert")));
		assertEquals(arg1, webDriver.findElement(By.id("alert")).getText());
	}
	@Then("^Pojawi się error w Typie i Opisie (.+)$")
	public void Pojawi_sie_error_w_Typie_i_Opisie(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.id("description-error")).getText());
		assertEquals(arg1, webDriver.findElement(By.id("type-error")).getText());
	}
	@Then("^Pojawi się error w Adresie (.+)$")
	public void Pojawi_sie_error_w_Adresie(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.id("address-line-error")).getText());
		assertEquals(arg1, webDriver.findElement(By.id("city-line-error")).getText());
	}
	@Then("^Błędne pole (.+)$")
	public void Bledne_pole(String arg1) throws Throwable {
		assertNotNull(webDriver.findElement(By.id(arg1)));
		assertEquals("form-group has-error", webDriver.findElement(By.id(arg1))
				.findElement(By.xpath("../..")).getAttribute("class"));
	}
	@Then("^Zostanie otwarta strona z przyciskiem (.+)$")
	public void Zostanie_otwarta_strona_z_przyciskiem(String arg1) throws Throwable {
		assertNotNull(webDriver.findElement(By.id(arg1)));
	}
	@Then("^Zostanie otwarta strona z tekstem (.+)$")
	public void Zostanie_otwarta_strona_z_tekstem(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.xpath("//h2")).getText());
	}

	@Then("^Pojawi się plik (.+)$")
	public void Pojawi_się_plik(String arg1) throws Throwable {
		assertNotNull(webDriver.findElement(By.linkText(arg1)));
	}
	@Then("^Pojawi się okno z przyciskiem (.+)$")
	public void Pojawi_się_okno_z_przyciskiem(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.xpath("(//button[@type='button'])[4]")).getText());
	}
	@Then("^Zamknie się okno z przyciskiem (.+)$")
	public void Zamknie_się_okno_z_przyciskiem(String arg1) throws Throwable {
		assertNotEquals(arg1, webDriver.findElement(By.xpath("(//button[@type='button'])[4]")).getText());
	}

	@Then("^Na stronie pojawi się tekst (.+)$")
	public void Na_stronie_pojawi_się_tekst(String arg1) throws Throwable {
		assertEquals(arg1, webDriver.findElement(By.xpath("//td/div")).getText());
	}




	@Then("^Na stronie nie będzie tekstu(.+)$")
	public void Na_stronie_nie_będzie_tekstu(String arg1) throws Throwable {
		assertNotEquals(arg1, webDriver.findElement(By.xpath("//td/div")).getText());
	}
}
