package com.pons;

import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadWebRequest;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 */
public class ExampleWicketApplication extends AuthenticatedWebApplication {
    private File uploadFolder;

    /**
     * Constructor
     */
    public ExampleWicketApplication() {
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return ExampleWebSession.class;
    }

    public Class getHomePage() {
        return FileInfoPage.class;
    }

    /**
     * @return the uploadFolder
     */
    public File getUploadFolder() {
        return uploadFolder == null ? new File(".") : uploadFolder;
    }

    @Override
    protected WebRequest newWebRequest(HttpServletRequest servletRequest) {
        return new UploadWebRequest(servletRequest);
    }

    /**
     * @param uploadFolder the uploadFolder to set
     */
    public void setUploadFolder(File uploadFolder) {
        this.uploadFolder = uploadFolder;
    }
}