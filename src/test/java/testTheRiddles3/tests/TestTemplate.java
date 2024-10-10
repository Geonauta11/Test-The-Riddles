package testTheRiddles3.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract public class TestTemplate {
    private static final Duration artificialLatency = Duration.ofMillis(0);
    private static final Duration implicitWaitDuration = Duration.ofSeconds(3);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    protected long timestamp;

    @BeforeAll
    public void setUpAll() {
        timestamp = System.currentTimeMillis();

        ChromeOptions options = new ChromeOptions();
        options.setCapability("acceptInsecureCerts", true);
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWaitDuration);
        ChromiumNetworkConditions conditions = new ChromiumNetworkConditions();
        conditions.setLatency(artificialLatency);
        ((ChromeDriver) driver).setNetworkConditions(conditions);
        wait = new WebDriverWait(driver, implicitWaitDuration);
        actions = new Actions(driver);
    }

    @BeforeEach
    public void setUpEach() {
        timestamp = System.currentTimeMillis();
    }

    @AfterAll
    public void tearDownAll() {
        driver.quit();
    }
}