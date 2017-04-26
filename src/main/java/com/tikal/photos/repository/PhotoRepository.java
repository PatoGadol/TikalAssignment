package com.tikal.photos.repository;

import com.tikal.photos.model.PhotoMetaData;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by pavel_sopher on 25/04/2017.
 */
public interface PhotoRepository extends CrudRepository<PhotoMetaData, Long> {
    PhotoMetaData findByPhotoName(String photoName);
    List<PhotoMetaData> findByLocation(String location);
    List<PhotoMetaData> findByLandscape(String landscape);
    PhotoMetaData findByDate(Date date);
}
