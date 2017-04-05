package com.tikal.photos.controller;

import com.tikal.photos.services.PhotosHandler;
import com.tikal.photos.services.PhotosHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Sopher on 24/02/2017.
 */
@RestController
@EnableAutoConfiguration
public class PhotosController {

    @Autowired
    PhotosHandler photosHandler;

    @PostMapping(value = "/upload_photos")
    public String uploadingPost(@RequestParam("user") String userName,
                                @RequestParam("uploadingPhotos") MultipartFile[] uploadingPhotos) throws IOException {
        if (uploadingPhotos.length == 0)
            return "Failed to load photos, since no photos where selected. Please try again.";
        try {
            photosHandler.uploadFiles(uploadingPhotos, userName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Your photos were uploaded successfully.";
    }
}

