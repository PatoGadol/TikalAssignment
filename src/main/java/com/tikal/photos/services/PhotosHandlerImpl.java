package com.tikal.photos.services;

import com.tikal.utils.OperationSystemDetermination;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Sopher on 22/03/2017.
 */

@Service
public class PhotosHandlerImpl implements PhotosHandler{

    private static String UPLOADED_FOLDER_WINDOWS = "D:\\UploadedPhotos\\";
    private static String UPLOADED_FOLDER_LINUX = "D:/UploadedPhotos/";

    @Override
    public void uploadFiles(MultipartFile[] uploadingPhotos, String userName) throws IOException {
        Arrays.stream(uploadingPhotos).forEach(x -> {
            try {
                uploadPhoto(userName, x);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    @Override
    public void uploadPhoto(String userName, MultipartFile file) throws IOException {
        String os = OperationSystemDetermination.getOperationSystem();
        byte[] bytes = file.getBytes();
        boolean isWindows = os.contains(OperationSystemDetermination.WINDOWS);
        String savePath = isWindows ? UPLOADED_FOLDER_WINDOWS.concat(userName + "\\")
                : UPLOADED_FOLDER_LINUX.concat(userName + "/");
        boolean isNewDir = new File(savePath).mkdirs();
        Path path = Paths.get(savePath.concat(file.getOriginalFilename()));
        Files.write(path, bytes);
    }
}
