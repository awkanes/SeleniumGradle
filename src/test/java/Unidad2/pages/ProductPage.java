package Unidad2.pages;

import Unidad2.utils.ClaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends ClaseBase {
    //Centralizar los By
    By localizadorSize = By.xpath("//div[@id='option-label-size-143-item-169']");
    By localizadorColor = By.xpath("//div[@id='option-label-color-93-item-57']");
    By localizadorAddToCart = By.xpath("//button[@id='product-addtocart-button']");


    //Acciones
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void AddToCart(){
        click(esperarPorElementoAClickear(localizadorSize));
        click(esperarPorElementoAClickear(localizadorColor));
        click(esperarPorElementoAClickear(localizadorAddToCart));

    }

}
