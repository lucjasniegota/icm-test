package com.icm.test.bdd;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		webDriver.findElement(By.id("signin")).click();
	}

	@Given("^Sprawdzana strona pod ścieżką (.+)$")
	public void Sprawdzana_strona_pod_ścieżką_incident_html(String arg1)
			throws Throwable {
		this.checkPath = arg1;
		webDriver.navigate().to(this.appAddress + this.checkPath);
	}

	@When("^Kliknięty przycisk (.+)$")
	public void Kliknięty_przycisk_save(String arg1) throws Throwable {
		webDriver.findElement(By.id(arg1)).click();
	}
	@When("^Kliknięta opcja (.+)$")
	public void Wybrana_opcja_save(String arg1) throws Throwable {
		webDriver.findElement(By.xpath("//table[@id='incidents']/tbody/tr/td[5]/div/button")).click();
	}
	@When("^Wybrana opcja (.+)$")
	public void Wybrana_opcja(String arg1) throws Throwable {
		webDriver.findElement(By.linkText(arg1)).click();
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

	@Then("^Błędne pole (.+)$")
	public void Bledne_pole(String arg1) throws Throwable {
		assertNotNull(webDriver.findElement(By.id(arg1)));
		assertEquals("form-group has-error", webDriver.findElement(By.id(arg1))
				.findElement(By.xpath("../..")).getAttribute("class"));
	}
	@Then("^Zostanie otwarta strona z przyciskiem (.+)$")
	public void Zostanie_otwarta_strona_z_tytułem(String arg1) throws Throwable {
		assertNotNull(webDriver.findElement(By.id("create")));
	}

}
