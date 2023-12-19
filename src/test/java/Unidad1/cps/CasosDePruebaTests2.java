package Unidad1.cps;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CasosDePruebaTests2 {
    private WebDriver driver;

    @BeforeEach
    void initDriver() {
        System.out.println("Antes");
        String path = "C:\\Users\\pablo.cabrera\\IdeaProjects\\SeleniumGradle\\src\\test\\resources\\driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
    }
    @Test
    @Order(1)
    void loginIncorrecto() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("pablo@cabrera.cl");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("Contrasena123");
        driver.findElement(By.xpath("//button[@class='action login primary']")).click();
        Thread.sleep(3000);
        String loginErrorActual = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        String loginErrorExpected = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        assertEquals(loginErrorExpected,loginErrorActual);

    }
    @Test
    @Order(2)
    void createAnAccount() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Create an Account')]")).click();
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Leo");
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Perez");
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("Leo@perez.cl");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("contraseña123");
        driver.findElement(By.xpath("//input[@id='password-confirmation']")).sendKeys("contraseña123");
        driver.findElement(By.xpath("//button[@title='Create an Account']")).click();
        Thread.sleep(3000);
        String registroExitosoActual=driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        String registroExitosoEsperado = "Thank you for registering with Main Website Store.";
        assertEquals(registroExitosoEsperado,registroExitosoActual);
    }
    @Test
    @Order(3)
    void loginCorrecto() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Leo@perez.cl");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("contraseña123");
        driver.findElement(By.xpath("//button[@class='action login primary']")).click();
        Thread.sleep(3000);
        assertEquals("Welcome, Leo Perez!",driver.findElement(By.cssSelector("div[class='panel header'] span[class='logged-in']")).getText());
    }
    @Test
    @Order(5)
    void singOut() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Leo@perez.cl");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("contraseña123");
        driver.findElement(By.xpath("//button[@class='action login primary']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']")).click();
        driver.findElement(By.xpath("//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")).click();
        Thread.sleep(6000);
        assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).isDisplayed());
    }
    @Test
    @Order(4)
    void addToCart() throws InterruptedException {
        WebElement e = driver.findElement(By.xpath("//img[@alt='Radiant Tee']"));
        Actions act = new Actions(driver);
        act.moveToElement(e).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
        assertEquals("Radiant Tee",driver.findElement(By.xpath("//span[@class='base']")).getText());
        driver.findElement(By.xpath("//div[@id='option-label-size-143-item-169']")).click();
        driver.findElement(By.xpath("//div[@id='option-label-color-93-item-57']")).click();
        driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
        assertTrue(driver.findElement(By.xpath("//body/div[2]/main[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).isDisplayed());

    }
    @AfterEach
    void despuesCada(){
        driver.quit();
    }
    @AfterAll
    static void despuesTodos(){
        System.out.println("Testing terminado");
    }
}
