package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends ClaseBase {
//Centralizar los By
    By locatorFirstName = By.xpath("//input[@id='firstname']");
    By locatorLastName = By.xpath("//input[@id='lastname']");
    By locatorEmail = By.xpath("//input[@id='email_address']");
    By locatorPassword = By.xpath("//input[@id='password']");
    By locatorPasswordConfirmation = By.xpath("//input[@id='password-confirmation']");
    By locatorSignInButton = By.xpath("//button[@title='Create an Account']");
    By locatorTextoConfirmacionRegistroExitoso = By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
//Acciones

    public void ingresarFirstName(String firstName){
        agregarTexto(esperarPorElementoAClickear(locatorFirstName),firstName);
    }


}
