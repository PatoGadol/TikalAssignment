package com.tikal.service;

import com.tikal.dao.model.PhotoMetaData;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by pavel_sopher on 26/03/2017.
 */
public interface PhotosHandler {

    public void uploadFiles(String userName, MultipartFile[] uploadingPhotos) throws IOException;
    public void uploadPhoto(String userName, MultipartFile file) throws IOException;

    public List<String> getFilesNames(String userName) throws IOException;
    public byte[] getPhoto(String userName, String photoName) throws IOException;

    @Profile(value = "business")
    public PhotoMetaData getPhotoMetaDataByDate(Date date);

    @Profile(value = "business")
    public List<PhotoMetaData> getPhotoMetaDataByLandscape(String landscape);

    @Profile(value = "business")
    public List<PhotoMetaData> getPhotoMetaDataByString(String location);

    @Profile(value = "business")
    public String save(PhotoMetaData photoMetaData);
}
