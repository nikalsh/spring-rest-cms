package se.nackademin.restcms;

import java.io.Serializable;
/*
Needed for response when saving data with ckeditor
 */
public class CKResponse implements Serializable {

    boolean uploaded=true;
    String url;

    public CKResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
}
