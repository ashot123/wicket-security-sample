package com.pons;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;

public class ExampleWebSession extends AuthenticatedWebSession {
    private String userName;

    public ExampleWebSession(AuthenticatedWebApplication application, Request request) {
        super(application, request);
    }

    @Override
    public boolean authenticate(String userName, String password) {
        boolean success = userName.equals("guest") && password.equals("guest");

        if (success)
            this.userName = userName;

        return success;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();

        if (isSignedIn())
            roles.add("USER");
        return roles;
    }

    public String getUserName() {
        return userName;
    }
}