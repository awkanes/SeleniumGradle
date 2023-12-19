package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ClaseBase {
    //Centralizar los By
    By localizadorSignIn = By.xpath("//a[contains(text(),'Sign In')]");
    By localizadorRegistrarse = By.xpath("//a[contains(text(),'Create an Account')]");


    //Acciones
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void IsARegistrarse(){
        click(esperarPorElementoAClickear(localizadorRegistrarse));
    }
    public void IrAIniciarSesion(){
        click(esperarPorElementoAClickear(localizadorSignIn));

    }
}
