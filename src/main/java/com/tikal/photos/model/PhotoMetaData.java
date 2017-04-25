package com.tikal.photos.model;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * Created by pavel_sopher on 25/04/2017.
 */

@Entity
@Profile("business")
@Scope(value = "prototype")
public class PhotoMetaData {
    private @Id
    @GeneratedValue
    Long id;
    private String location, landscape;
    private Date date;

    private PhotoMetaData() {}

    public PhotoMetaData(String location, String landscape, Date date) {
        this.location = location;
        this.landscape = landscape;
        this.date = date;
    }
}
