package com.myapp.testcases;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class Reports {
    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeSuite
    public void reportcreation()
    {
extent = new ExtentReports("Reports/index.html");
extent.addSystemInfo("frameworktype", "appium page object");
extent.addSystemInfo("auther", "Amine");
extent.addSystemInfo("enviremnt", "Windows");
extent.addSystemInfo("App", "Qa cart");

    }


    @AfterSuite
    public void reportclosing()
    {
        extent.flush();
    }
@BeforeMethod
    public void BeforeMethode(Method methode)
{
    logger = extent.startTest(methode.getName());
}
    @Test
    public void test1()
    {
        Assert.fail();
        System.out.println("test 1 failed");
    }
    @Test
    public void test2()
    {
        System.out.println("test 2 pass");
    }

    @AfterMethod
    public void AfterMethod(Method methode, ITestResult result)
    {
        if(result.getStatus()== ITestResult.SUCCESS){
        logger.log(LogStatus.PASS,"No error testpass");}
        else if(result.getStatus()== ITestResult.FAILURE){
            logger.log(LogStatus.FAIL," Test Failed");
            logger.log(LogStatus.FAIL,result.getThrowable());}
        else{
            logger.log(LogStatus.SKIP, "Test is skkiped");}
    }
}
