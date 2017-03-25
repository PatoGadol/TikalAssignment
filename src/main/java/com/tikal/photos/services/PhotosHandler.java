package com.tikal.photos.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by pavel_sopher on 26/03/2017.
 */
public interface PhotosHandler {

    public void uploadFiles(MultipartFile[] uploadingPhotos, String userName) throws IOException;
    public void uploadPhoto(String userName, MultipartFile file) throws IOException;

}
