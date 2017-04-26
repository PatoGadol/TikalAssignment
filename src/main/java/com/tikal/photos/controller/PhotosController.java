package com.tikal.photos.controller;

import com.tikal.photos.model.PhotoMetaData;
import com.tikal.photos.services.PhotosHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sopher on 24/02/2017.
 */
@RestController
@EnableAutoConfiguration
public class PhotosController {

    @Autowired
    PhotosHandler photosHandler;

    @PostMapping(value = "/upload_photos")
    public String uploadPhotos(@RequestParam("user") String userName,
                               @RequestParam("uploadingPhotos") MultipartFile[] uploadingPhotos) throws IOException {
        if (uploadingPhotos.length == 0)
            return "Failed to load photos, since no photos where selected. Please try again.";
        try {
            photosHandler.uploadFiles(userName, uploadingPhotos);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Your photos were uploaded successfully.";
    }

    @GetMapping(value = "/see_photos")
    public List<String> getPhotosNames(@RequestParam("user") String userName) throws IOException {
        try {
            return photosHandler.getFilesNames(userName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadPhoto(@RequestParam("user") String userName, @RequestParam("photo_name") String photoName) throws IOException {
        try {
            return photosHandler.getPhoto(userName, photoName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/photo_metadata")
    @Profile(value = "business")
    public String uploadPhotoMetaData(@RequestBody PhotoMetaData photoMetaData) {
        return photosHandler.save(photoMetaData);
    }


    @GetMapping(value = "/photo_metadata_by_landscape")
    @Profile(value = "business")
    public List<PhotoMetaData> getPhotosHandler(@RequestParam("landscape") String landscape) {
        return photosHandler.getPhotoMetaDataByLandscape(landscape);
    }

    /*@GetMapping(value = "/photo_metadata")
    public PhotoMetaData getPhotosHandler() {
        return photosHandler.getPhotoMetaDataByDate();
    }*/
}