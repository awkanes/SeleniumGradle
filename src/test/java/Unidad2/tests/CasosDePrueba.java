package Unidad2.tests;

import Unidad2.pages.HomePage;
import Unidad2.pages.LoginPage;
import Unidad2.pages.ProductPage;
import Unidad2.pages.RegisterPage;
import Unidad2.utils.ClaseBase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CasosDePrueba {
    //Atributos
    private WebDriver driver;

    //PAGE
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ProductPage productPage;

    String browser = "chrome";
    String propertyDriver = "webdriver.chrome.driver";
    String rutaDriver = System.getProperty("C:\\Users\\pablo.cabrera\\IdeaProjects\\SeleniumGradle\\src\\test\\resources\\driver\\chromedriver.exe");
    String url = "https://magento.softwaretestingboard.com/";

    @BeforeEach
    public void preCondiciones() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(homePage.getDriver());
        loginPage = new LoginPage(homePage.getDriver());
        productPage = new ProductPage(homePage.getDriver());
        homePage.conexionDriver(rutaDriver,browser,propertyDriver);
        homePage.cargarPagina(url);
        homePage.maximizarVentana();
    }
    @Test
    @Order(1)
    void CP001_login_incorrecto() throws InterruptedException {
        homePage.IrAIniciarSesion();
        loginPage.llenarCorreo("pablo@cabrera.cl");
        loginPage.llenarContrasena("Contrasena123");
        loginPage.clickSignInButton();
        Thread.sleep(3000);
        String loginErrorActual = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        String loginErrorExpected = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        assertEquals(loginErrorExpected,loginErrorActual);

    }
    @Test
    @Order(2)
    void CP002_creacion_de_cuenta() throws InterruptedException {
        homePage.IrARegistrarse();
        registerPage.ingresarFirstName("Camilo");
        registerPage.ingresarLastname("Soto");
        registerPage.ingresarCorreo("camilo@soto.cl");
        registerPage.ingresarPassword("Contraseña123");
        registerPage.confirmarPassword("Contraseña123");
        registerPage.clickRegistrar();
        Thread.sleep(3000);
        String registroExitosoActual=driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        String registroExitosoEsperado = "Thank you for registering with Main Website Store.";
        assertEquals(registroExitosoEsperado,registroExitosoActual);
    }
    @Test
    @Order(3)
    void CP003_login_correcto() throws InterruptedException {

        homePage.IrAIniciarSesion();
        loginPage.llenarCorreo("camilo@soto.cl");
        loginPage.llenarContrasena("Contrasena123");
        loginPage.clickSignInButton();
        assertEquals("Welcome, Camilo Soto!",driver.findElement(By.cssSelector("div[class='panel header'] span[class='logged-in']")).getText());
    }
    @Test
    @Order(5)
    void CP004_singOut() throws InterruptedException {
        homePage.IrAIniciarSesion();
        loginPage.llenarCorreo("camilo@soto.cl");
        loginPage.llenarContrasena("Contrasena123");
        loginPage.clickSignInButton();
        loginPage.clickBotonSignOut();
        assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).isDisplayed());
    }
    @Test
    @Order(4)
    void CP005_agregar_al_carro() throws InterruptedException {
        Actions act = new Actions(homePage.getDriver());
        homePage.IrAIniciarSesion();
        loginPage.llenarCorreo("camilo@soto.cl");
        loginPage.llenarContrasena("Contrasena123");
        loginPage.clickSignInButton();
        loginPage.irAHomePage();
        homePage.IrAProducto();
        assertEquals("Radiant Tee",driver.findElement(By.xpath("//span[@class='base']")).getText());
        productPage.AddToCart();
        assertTrue(driver.findElement(By.xpath("//body/div[2]/main[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).isDisplayed());

    }
    @AfterEach
    public void despuesDeCadaTest(){
        homePage.cerrarPagina();
    }



    @AfterAll
    static void despuesTodos(){
        System.out.println("Testing terminado");
    }

}
