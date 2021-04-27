package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.WelcomePage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;
    public WelcomePage welcomePage;
    public static ExtentReports report;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentTest extentLogger;



    @BeforeTest
    public void beforeTest() {
        // Reports setup
        report = new ExtentReports();
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/testReports/report.html");
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("Test Report");
        report.setSystemInfo("Tester", "Ismail");
        report.setSystemInfo("Environment", "test");
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        report.flush();
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.cyclos.org/ui/home");
        welcomePage = new WelcomePage(driver);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentLogger.fail(result.getName());
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            String target = System.getProperty("user.dir") + "/testReports/" + result.getName() + date + ".png";
            File finalDestination = new File(target);

            FileUtils.copyFile(file, finalDestination);
            extentLogger.addScreenCaptureFromPath(result.getName()+date+".png");
            extentLogger.fail(result.getThrowable());
        }
        driver.quit();

    }

}
