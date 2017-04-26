package com.tikal.photos.model;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pavel_sopher on 25/04/2017.
 */

@Entity
@Profile("business")
@Scope(value = "prototype")

@Table(name = "photo_metadata")
public class PhotoMetaData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="photo_metadata_id_seq")
    private Long id;
    @Column(nullable = false)
    private String photoName;
    @Column
    private String location;
    @Column
    private Date date;
    @Column
    private String landscape;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private PhotoMetaData() {}

    public PhotoMetaData(String location, String landscape, Date date) {
        this.location = location;
        this.landscape = landscape;
        this.date = date;
    }
}
