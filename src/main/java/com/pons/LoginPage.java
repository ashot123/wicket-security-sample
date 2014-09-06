package com.pons;

import org.apache.wicket.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.WebPage;

public class LoginPage extends BasePage
{
    public LoginPage()
    {
        add(new SignInPanel("signInPanel"));
    }
}
