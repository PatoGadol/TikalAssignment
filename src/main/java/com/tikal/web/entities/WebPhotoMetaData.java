package com.tikal.web.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pavel_sopher on 10/05/2017.
 */
public class WebPhotoMetaData implements Serializable {
    private String photoName;
    private String location;
    private String landscape;

    private Date dateCreated;

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

}
