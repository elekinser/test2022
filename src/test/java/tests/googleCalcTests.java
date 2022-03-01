package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import page.dateGooglePage;
import page.dateGooglePage;
--Домашняя работа

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class googleCalcTests {

    private static WebDriver driver;
    private static dateGooglePage dateGooglePage;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        dateGooglePage = new dateGooglePage(driver);

    }

    @BeforeEach
    public void setup() {

        driver.get("https://www.google.ru/");
    }

    @Test
    @DisplayName("Кейс 1. Проверка расчета формулы")
    public void test1() {
        dateGooglePage.search("Калькулятор");
        //(
        dateGooglePage.openBracket.click();
        //1
        dateGooglePage.one.click();
        //+
        dateGooglePage.plus.click();
        //2
        dateGooglePage.two.click();
        //)
        dateGooglePage.closingBracket.click();
        //*
        dateGooglePage.multiply.click();
        //3
        dateGooglePage.three.click();
        //-
        dateGooglePage.minus.click();
        //4
        dateGooglePage.four.click();
        //0
        dateGooglePage.zero.click();
        //:
        dateGooglePage.division.click();
        //5
        dateGooglePage.five.click();
        //=
        dateGooglePage.equally.click();

        assertAll(
                () -> assertEquals("(1 + 2) × 3 - 40 ÷ 5 =", driver.findElement(By.cssSelector("div [jsname=\"VkJw6\"] span")).getText()),
                () -> assertEquals("1", driver.findElement(By.cssSelector("div [jsname=\"zLiRgc\"] span")).getText())
        );


    }
    @Test
    @DisplayName("Кейс 2. Проверка деления на ноль")
    public void test2() {
        dateGooglePage.search("Калькулятор");
        //6
        dateGooglePage.six.click();
        //:
        dateGooglePage.division.click();
        //0
        dateGooglePage.zero.click();
        //=
        dateGooglePage.equally.click();
        assertAll(
                () -> assertEquals("6 ÷ 0 =", driver.findElement(By.cssSelector("div [jsname=\"VkJw6\"] span")).getText()),
                () -> assertEquals("Infinity", driver.findElement(By.cssSelector("div [jsname=\"zLiRgc\"] span")).getText())
        );


    }
    @Test
    @DisplayName("Кейс 3. Проверка ошибки при отсутствии значения")
    public void test3() {
        dateGooglePage.search("Калькулятор");
        //sin()
        dateGooglePage.sin.click();
        //=
        dateGooglePage.equally.click();
        assertAll(
                () -> assertEquals("sin() =", driver.findElement(By.cssSelector("div [jsname=\"VkJw6\"] span")).getText()),
                () -> assertEquals("Error", driver.findElement(By.cssSelector("div [jsname=\"zLiRgc\"] span")).getText())
        );


    }
    @AfterAll
    public static void teardown() {
        driver.quit();
    }

}
