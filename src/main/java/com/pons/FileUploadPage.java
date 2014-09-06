package com.pons;

import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Bytes;

import java.io.File;
import java.io.IOException;

public class FileUploadPage extends BasePage {
    // user-File_Title-tag1+tag2+tag_3.ext
    private static final String FILENAME_FORMAT = "%s-%s-%s.%s";

    public FileUploadPage(IModel userContributedFileModel) {
        super(userContributedFileModel);
        add(new FileUploadForm("fileUploadForm"));
    }

    private class FileUploadForm extends Form {
        private FileUploadField fileUploadField;

        public FileUploadForm(String id) {
            super(id);

            setOutputMarkupId(true);

            setMultiPart(true);
            setMaxSize(Bytes.gigabytes(1));

            add(fileUploadField = new FileUploadField("fileInput"));
            add(new UploadProgressBar("progress", this));
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();

            final FileUpload upload = fileUploadField.getFileUpload();

            if (upload == null)
                return;

            File tempFile;
            try {
                tempFile = upload.writeToTempFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to write file upload.", e);
            }

            File uploadFolder
                    = ((ExampleWicketApplication) getApplication()).getUploadFolder();

            String userName
                    = ((ExampleWebSession) getSession()).getUserName();

            UserContributedFile userContributedFile
                    = (UserContributedFile) getPage().getModelObject();

            String remoteFileName = upload.getClientFileName();

            String destinationFileName = String.format(FILENAME_FORMAT, userName,
                    userContributedFile.getTitle(),
                    userContributedFile.getTagsUsingDelimiter('+'),
                    remoteFileName.substring(remoteFileName.lastIndexOf('.') + 1));

            tempFile.renameTo(new File(uploadFolder.getAbsolutePath()
                    .concat(File.separator)
                    .concat(destinationFileName)));
        }
    }
}