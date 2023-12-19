package Unidad2.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ClaseBase {
    //Wrapper de selenium
    //invocar a las funciones de selenium (click, senKeys, getText, ect

    //Atributos
    WebDriver driver;
    WebDriverWait wait;

    //Constructor
    public ClaseBase(WebDriver driver) {
        this.driver = driver;
    }
    //Métodos que envuelven las acciones del driver

    public void click(By localizador) {
        this.driver.findElement(localizador).click();
    }

    public void click(WebElement elemento) {
        elemento.click();
    }

    public List<WebElement> buscarElementosWeb(By localizador){
        return this.driver.findElements(localizador);
    }
    public WebElement buscarElementoWeb(By localizador){
        return this.driver.findElement(localizador);
    }
    public String obtenerTexto(By localizador) {
        return this.driver.findElement(localizador).getText();
    }

    public String obtenerTexto(WebElement elemento) {
        return elemento.getText();
    }
    public void agregarTexto(By localizador, String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }
    public void agregarTexto(WebElement elemento, String texto){
        elemento.sendKeys(texto);
    }
    public void agregarCombinacionTeclado(By localizador, Keys keys){
        this.driver.findElement(localizador).sendKeys(keys);
    }
    public void maximizarVentana(){
        this.driver.manage().window().maximize();
    }
    public void seleccionarComboBoxPorTextoVisible(WebElement elemento,String texto){
        Select select = new Select(elemento);
        select.selectByVisibleText(texto);
    }
    public void seleccionarComboBoxPorTextoVisible(By localizador,String texto){
        Select select = new Select(driver.findElement(localizador));
        select.selectByVisibleText(texto);
    }
    public void seleccionarComboBoxPorValue(By localizador,String value){
        Select select = new Select(driver.findElement(localizador));
        select.selectByVisibleText(value);
    }
    public void seleccionarComboBoxPorValue(WebElement elemento,String value){
        Select select = new Select(elemento);
        select.selectByVisibleText(value);
    }
    public void irAFramePorId(String Id){
        this.driver.switchTo().frame(Id);
    }
    public void cargarPagina(String url){
        this.driver.get(url);
    }
    public void cerrarPagina(){
        this.driver.quit();
    }
    public void refrescarPagina(){
        this.driver.navigate().refresh();
    }
    public void esperarXSegundos(int tiempoMilli) throws InterruptedException{
       Thread.sleep(tiempoMilli);
    }
    public WebElement esperarPorElementoAClickear(By localizador){
        wait = new WebDriverWait(driver,30);
        return wait.until(ExpectedConditions.elementToBeClickable(localizador));
    }
    public WebElement esperarPorPresenciaElementoWeb(By localizador){
        wait = new WebDriverWait(driver,30);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }
    public WebDriver conexionDriver(String rutaDriver,String browser,String propertyDriver){
        switch(browser){
            case "firefox":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver = new EdgeDriver();
                break;
            default:
                this.driver = null;
        }
        return driver;
    }
}
