package se.nackademin.restcms;

import java.io.Serializable;

public class ImageTest implements Serializable {

    boolean uploaded=true;
    String url;

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
