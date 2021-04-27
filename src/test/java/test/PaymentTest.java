package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BankingPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.WelcomePage;

public class PaymentTest extends BaseTest {

    @Test
    public void testPaymentPositive(){
        extentLogger = report.createTest("negativeTestLogin");
        LoginPage lp = welcomePage.clickLogin();
        //LoginPage lp = new LoginPage(driver);
        DashboardPage dp= lp.login("demo", "1234");
        BankingPage bp = dp.clickBanking();
        bp.payToUser("10", "friend gift");
        bp.confirmPayment();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String paymentText = bp.paymentConfirmationText();
        Assert.assertEquals(paymentText, "Payment performed");
    }

    @Test
    public void testPaymentNegative(){
/*
Amount field in the Banking page cannot accept those values:
- empty - errormessage: 'This field is required'
- 0 (zero) - errorMessage :'Amount must be a positive number.'
- More than transaction limit (currently 500,00 , so, 500,01 should fail)  - errorMessage: 'Amount must be less or equal to 500,00 IU's.'
 */
    }

    @DataProvider
    public Object [][] dataProvider(){
        Object [][]testData = {{}};
        // amount cannot be empty -
        // amount cannot be 0 (zero)
        // amount cannot be more than 500
        return testData;
    }

//    @Test(dataProvider = "paymentAmountNegativeData")
//    public void paymentAmountNegative(String amount, String errorMessage) {
//        /*
//        -empty, 'This field is required'
//        -0 (zero), 'Amount must be a positive number.'
//        -more than daily allowed (500), 'Amount must be less or equal to 500,00 IU's.'
//         */
//        extentLogger = report.createTest("Negative payment amount test");
//        LoginPage loginPage = welcomePage.clickLogin();
//        DashboardPage cdp = loginPage.login("demo", "1234");
//        BankingPage bp = cdp.clickBanking();
//        bp.payToUser(amount, "bills");
//        System.out.println("errorMessage = " + errorMessage);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String error = bp.getInvalidAmountError();
//        Assert.assertEquals(error, errorMessage);
//    }
//
//    @DataProvider
//    public Object[][] paymentAmountNegativeData() {
//        Object[][] testData = {
//                {" ", "This field is required1"},
//                {"0", "Amount must be a positive number.1"},
//                {"500.01", "Amount must be less or equal to 500,00 IU's.1"},
//        };
//        return testData;
//    }
//

}
