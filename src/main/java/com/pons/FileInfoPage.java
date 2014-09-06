package com.pons;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

@AuthorizeInstantiation("USER")
public class FileInfoPage extends BasePage {
    public FileInfoPage() {
        super();
        add(new FileInfoForm("metaDataForm"));
    }

    private class FileInfoForm extends Form {
        public FileInfoForm(String id) {
            super(id);

            setModel(new CompoundPropertyModel(new UserContributedFile()));

            add(new RequiredTextField("title")
                    .add(StringValidator.maximumLength(32)));
            add(new TextField("tags")
                    .add(StringValidator.maximumLength(32)));
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();

            FileUploadPage fileUploadPage = new FileUploadPage(getModel());
            setResponsePage(fileUploadPage);
        }
    }
}
