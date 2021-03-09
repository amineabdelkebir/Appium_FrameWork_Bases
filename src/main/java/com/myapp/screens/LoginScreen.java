package com.myapp.screens;

import com.myapp.base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginScreen extends Base {

    @iOSXCUITFindBy(xpath="//*[@text='Enter your Email']")
    @AndroidFindBy(xpath="//*[@text='Enter your Email']")
    private MobileElement email;
    @iOSXCUITFindBy(xpath="//*[@text='Enter your Password']")
    @AndroidFindBy(xpath="//*[@text='Enter your Password']")
    private MobileElement password;
    @iOSXCUITFindBy(accessibility = "Login")
    @AndroidFindBy(xpath="//*[@text='Login']")
    private MobileElement loginbtn;
    @iOSXCUITFindBy(xpath="//*[@text='Signup']")
    @AndroidFindBy(xpath="//*[@text='Signup']")
    private MobileElement signupbtn;
    ///or  we can find this element using xpath and index
    //   driver.findElementByXPath("//android.widget.Text[@index='1']").sendKeys("aaa@aa.com");

    public void fill_email_password(String myemail, String mypassword)
    {
        email.sendKeys(myemail);
        password.sendKeys((mypassword));

    }
}
