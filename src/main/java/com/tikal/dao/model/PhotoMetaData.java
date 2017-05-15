package com.tikal.dao.model;

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
    @SequenceGenerator(name="photo_metadata_id_seq", sequenceName="photo_metadata_id_seq", allocationSize=1)
    private Long photoMetadataId;
    @Column(nullable = false)
    private String photoName;
    @Column
    private String location;
    @Column
    private Date dateCreated;
    @Column
    private String landscape;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public Long getPhotoMetadataId() {
        return photoMetadataId;
    }

    public void setPhotoMetadataId(Long photoMetadataId) {
        this.photoMetadataId = photoMetadataId;
    }

    private PhotoMetaData() {}

    public PhotoMetaData(String location, String landscape, String photoName, Date date) {
        this.location = location;
        this.landscape = landscape;
        this.photoName = photoName;
        this.dateCreated = date;
    }
}
