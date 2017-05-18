package com.tikal.controller;

import com.tikal.dao.model.PhotoMetaData;
import com.tikal.service.PhotosHandler;
import com.tikal.web.entities.WebNotification;
import com.tikal.web.entities.WebPhotoMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    public String uploadPhotos(@RequestParam("user") String username,
                               @RequestParam("uploadingPhotos") MultipartFile[] uploadingPhotos) throws IOException {
        if (uploadingPhotos.length == 0)
            return "Failed to load photos, since no photos where selected. Please try again.";
        try {
            photosHandler.uploadFiles(username, uploadingPhotos);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Your photos were uploaded successfully.";
    }

    @GetMapping(value = "/see_photos")
    public List<String> getPhotosNames(@RequestParam("user") String username) throws IOException {
        try {
            return photosHandler.getFilesNames(username);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(value = "/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadPhoto(@RequestParam("user") String username, @RequestParam("photo_name") String photoName) throws IOException {
        try {
            return photosHandler.getPhoto(username, photoName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/photo_metadata")
    @Profile(value = "business")
    public String uploadPhotoMetaData(@RequestBody WebPhotoMetaData photoMetaData) {
        return photosHandler.save(photoMetaData);
    }


    @GetMapping(value = "/photo_metadata_by_landscape")
    @Profile(value = "business")
    public List<PhotoMetaData> getPhotosHandler(@RequestParam("landscape") String landscape) {
        return photosHandler.getPhotoMetaDataByLandscape(landscape);
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public WebNotification greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new WebNotification("Hello, " + new WebNotification(message) + "!");
    }

    /*@GetMapping(value = "/photo_metadata")
    public PhotoMetaData getPhotosHandler() {
        return photosHandler.getPhotoMetaDataByDate();
    }*/
}