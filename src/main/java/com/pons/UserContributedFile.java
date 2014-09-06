package com.pons;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.Serializable;

public class UserContributedFile
        implements Serializable
{
    private String title;
    private String[] tags;
    private File file;

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the tags
     */
    public String getTags()
    {
        return StringUtils.join(tags, ", ");
    }

    /**
     * @return the tags
     */
    public String getTagsUsingDelimiter(char delimiter)
    {
        return StringUtils.join(tags, delimiter);
    }

    /**
     * Set tags using a comma and whitespace separated list.
     *
     * @param tags
     */
    public void setTags(String tags)
    {
        this.tags = StringUtils.splitByWholeSeparator(tags, ", ");
    }

    /**
     * @return the file
     */
    public File getFile()
    {
        return file;
    }
    /**
     * @param file the file to set
     */
    public void setFile(File file)
    {
        this.file = file;
    }
}