package com.myapp.testcases;

import com.myapp.base.Base;
import com.myapp.screens.LoginScreen;
import org.testng.annotations.Test;

public class LoginScreenTest extends Base {

    LoginScreen loginScreenobject;

    String login="hatem.hatamleh@qacart.com";
    String password="test@123";
    @Test

    public void firsttestcase()
    {
loginScreenobject =new LoginScreen();
loginScreenobject.fill_email_password(login,password);

    }
}
