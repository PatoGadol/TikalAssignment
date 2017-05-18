package com.tikal.service;

import com.tikal.dao.model.PhotoMetaData;
import com.tikal.dao.repository.PhotoRepository;
import com.tikal.utils.OperationSystemDetermination;
import com.tikal.web.entities.WebPhotoMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Sopher on 22/03/2017.
 */

@Service
public class PhotosHandlerImpl implements PhotosHandler {

    private static String UPLOADED_FOLDER_WINDOWS = "D:\\UploadedPhotos\\";
    private static String UPLOADED_FOLDER_LINUX = "D:/UploadedPhotos/";

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public void uploadFiles(String username, MultipartFile[] uploadingPhotos) throws IOException {
        Arrays.stream(uploadingPhotos).forEach(x -> {
            try {
                uploadPhoto(username, x);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    @Override
    public void uploadPhoto(String username, MultipartFile file) throws IOException {
        String savePath = getFolderPath(username);
        boolean isNewDir = new File(savePath).mkdirs();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(savePath.concat(file.getOriginalFilename()));
        Files.write(path, bytes);
    }

    @Override
    public List<String> getFilesNames(String username) throws IOException {
        String loadPath = getFolderPath(username);
        Path path = Paths.get(loadPath);
        List<String> filesNames = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(path)) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    filesNames.add(filePath.toString());

                }
            });
        }

        return filesNames;
    }

    @Override
    public byte[] getPhoto(String username, String photoName) {
        String filePathStr = getFolderPath(username) + photoName;
        Path filePath = Paths.get(filePathStr);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String extension = photoName.split("\\.")[1];
        try {
            BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(filePath));
            ImageIO.write(bufferedImage, extension, baos);

        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
        System.out.println(filePath);
        return baos.toByteArray();
    }

    @Profile(value = "business")
    @Override
    public PhotoMetaData getPhotoMetaDataByDate(Date dateCreated) {
        return photoRepository.findByDateCreated(dateCreated);
    }

    @Profile(value = "business")
    @Override
    public List<PhotoMetaData> getPhotoMetaDataByLandscape(String landscape) {
        return  photoRepository.findByLandscape(landscape);
    }

    @Profile(value = "business")
    @Override
    public List<PhotoMetaData> getPhotoMetaDataByString(String location) {
        return photoRepository.findByLocation(location);
    }

    @Profile(value = "business")
    @Override
    public String save(WebPhotoMetaData webPhotoMetaData) {
        photoRepository.save(convertToPhotoMetaData(webPhotoMetaData));
        return "MetaData saved.";
    }

    private PhotoMetaData convertToPhotoMetaData(WebPhotoMetaData webPhotoMetaData) {
        return new PhotoMetaData(webPhotoMetaData.getLocation(),
                webPhotoMetaData.getLandscape(),
                webPhotoMetaData.getPhotoName(),
                webPhotoMetaData.getDateCreated());
    }

    private String getFolderPath(String username) {
        String os = OperationSystemDetermination.getOperationSystem();
        boolean isWindows = os.contains(OperationSystemDetermination.WINDOWS);
        return isWindows ? UPLOADED_FOLDER_WINDOWS.concat(username + "\\")
                : UPLOADED_FOLDER_LINUX.concat(username + "/");
    }

}
