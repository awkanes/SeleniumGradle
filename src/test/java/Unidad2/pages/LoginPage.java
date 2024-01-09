package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends ClaseBase {
    //Centralizar los By
    By locatorCorreo = By.xpath("//input[@id='email']");
    By locatorContrasena = By.xpath("//input[@name='login[password]']");
    By locatorBoton = By.xpath("//button[@class='action login primary']");
    By locatorMenu = By.xpath("//div[@class='panel header']//button[@type='button']");
    By locatorSignOut = By.xpath("//div[@aria-hidden='false']//a[normalize-space()='Sign Out']");
    By locatorMenuPrincipal = By.xpath("//a[@aria-label='store logo']//img");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Acciones
    public void clickSignInButton(){
        click(esperarPorElementoAClickear(locatorBoton));
    }
    public void llenarCorreo(String correo){
        agregarTexto(esperarPorPresenciaElementoWeb(locatorCorreo),correo);
    }
    public void llenarContrasena(String contrasena){
        agregarTexto(esperarPorPresenciaElementoWeb(locatorContrasena),contrasena);
    }
    public void clickBotonSignOut(){
        click(esperarPorElementoAClickear(locatorMenu));
        click(esperarPorElementoAClickear(locatorSignOut));
    }
    public void irAHomePage(){
        click(esperarPorElementoAClickear(locatorMenuPrincipal));
    }
}
