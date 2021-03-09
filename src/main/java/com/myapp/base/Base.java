package com.myapp.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import sun.misc.IOUtils;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public Base()
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    protected FileInputStream inputStream;
    protected Properties prop;
    protected static AndroidDriver<AndroidElement> driver;
    public static ExtentReports extent;
    public static ExtentTest logger;

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
    public void closingreport()
    {
        extent.flush();
    }

    @BeforeMethod
    public void BeforeMethode(Method methode)
    {
        logger = extent.startTest(methode.getName());
    }
    @AfterMethod
    public void AfterMethod(Method methode, ITestResult result) throws IOException {
        File image = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.getFileUtils().copyFile(image , new File ("Snapshoots\\"+methode.getName()+".jpeg"));

        String fullpath= System.getProperty("user.dir")+"\\Snapshoots\\"+methode.getName()+".jpeg";

        if(result.getStatus()== ITestResult.SUCCESS){
            logger.log(LogStatus.PASS,"No error testpass");
            logger.log(LogStatus.PASS,logger.addScreenCapture(fullpath));

        }
        else if(result.getStatus()== ITestResult.FAILURE){
            logger.log(LogStatus.FAIL," Test Failed");
            logger.log(LogStatus.FAIL,result.getThrowable());
            logger.log(LogStatus.FAIL,logger.addScreenCapture(fullpath));}
        else{
            logger.log(LogStatus.SKIP, "Test is skkiped");}

        System.out.println(fullpath);
    }

    @Parameters({"devicename","platformname","platformversion"})
    @BeforeClass
      public void beforeclass(String devicename, String platformname, String platformversion) throws IOException {

        File propfile = new File("C:\\Users\\abdel\\IdeaProjects\\todoapp_framework\\src\\main\\resources\\config\\config.properties");
        inputStream = new FileInputStream(propfile);
        prop= new Properties();
        prop.load(inputStream);

        if(platformname.equalsIgnoreCase("android")) {
            File androidapp = new File(prop.getProperty("androidAppPath"));
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformname);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformversion);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("AndroidAutomationName"));
            caps.setCapability(MobileCapabilityType.APP, androidapp.getAbsolutePath());
            driver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("appiumserverURL")), caps);
        }
        else if (platformname.equalsIgnoreCase("ios"))
        {
            File iosapp = new File(prop.getProperty("iosApppath"));
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformname);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformversion);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("IOSAutomationName"));
            caps.setCapability(MobileCapabilityType.APP, iosapp.getAbsolutePath());
            driver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("appiumserverURL")), caps);
        }

        else
        { System.out.println("Device or  emulator not found");}

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
    }




    @AfterClass

    public void closedriver()
    {
        driver.quit();
    }

}
