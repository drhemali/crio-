package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @Test
    public void testCase01() throws InterruptedException{
        System.out.println("Start testCase01 : Automate filling up a form");
        driver.navigate().to("https://forms.gle/wjPkzeSEk1CM7KgGA");
        String url = driver.getCurrentUrl();
        if(url.contains("forms")){
            System.out.println("Navigated to the google form");
        }
        Thread.sleep(4000);
    }
    @Test
    public void testCase02() {
        
        WebElement nameBox = driver.findElement(By.xpath("(//input[@class= 'whsOnd zHQkBf'])[1]"));
        nameBox.click();
        nameBox.sendKeys("Crio Learner");
    }
    @Test
    public void testCase03() {
        
        WebElement practicingBox = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        Instant now = Instant.now();
        long epochTimeInSeconds = now.getEpochSecond();
        practicingBox.sendKeys("I want to be the best QA Engineer!"+ epochTimeInSeconds);
    }
    @Test
    public void testCase04() {
        
        WebElement experienceBox = driver.findElement(By.xpath("//div[@data-value = '0 - 2']/div/div"));
        experienceBox.click();
    }
    @Test
    public void testCase05() {
        
        WebElement javaBox = driver.findElement(By.xpath("//span[text()='Java']"));
        javaBox.click();
        WebElement seleniumBox = driver.findElement(By.xpath("//span[text()='Selenium']"));
        seleniumBox.click();
        WebElement testNgBox = driver.findElement(By.xpath("//span[text()='TestNG']"));
        testNgBox.click();
    }
    @Test
    public void testCase06() throws InterruptedException {
        
        WebElement dropDown = driver.findElement(By.xpath("//div[@class='e2CuFe eU809d']"));
        dropDown.click();
        Thread.sleep(2000);
        WebElement missButton = driver.findElement(By.xpath("(//span[text()='Ms'])[2]"));
        missButton.click();
        Thread.sleep(2000);
    }
    @Test
    public void testCase07() {
        
        LocalDate today = LocalDate.now();
        LocalDate dateMinus7Days = today.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateMinus7Days.format(formatter);
        WebElement dateInputField = driver.findElement(By.xpath("//input[@type='date']"));
        dateInputField.sendKeys(formattedDate);
    }
    @Test
    public void testCase08() {
        
        WebElement hourBox = driver.findElement(By.xpath("//input[@aria-label= 'Hour']"));
        hourBox.sendKeys("07");
        WebElement minuteBox = driver.findElement(By.xpath("//input[@aria-label= 'Minute']"));
        minuteBox.sendKeys("30");
    }
    @Test
    public void testCase09() {
        
        WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']"));
        submitButton.click();
        WebElement successResponse = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        String successMessage = successResponse.getText();
        System.out.println(successMessage);
    }

    @BeforeTest
    public void startBrowser(){
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void endTest(){
        driver.close();
        driver.quit();

    }
}