package com.tikal.photos.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by pavel_sopher on 26/03/2017.
 */
public interface PhotosHandler {

    public void uploadFiles(String userName, MultipartFile[] uploadingPhotos) throws IOException;
    public void uploadPhoto(String userName, MultipartFile file) throws IOException;

    public List<String> getFilesNames(String userName) throws IOException;

    public byte[] getPhoto(String userName, String photoName) throws IOException;
}
