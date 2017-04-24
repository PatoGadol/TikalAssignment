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
import java.util.stream.Stream;

/**
 * Created by Sopher on 22/03/2017.
 */

@Service
public class PhotosHandlerImpl implements PhotosHandler{

    private static String UPLOADED_FOLDER_WINDOWS = "D:\\UploadedPhotos\\";
    private static String UPLOADED_FOLDER_LINUX = "D:/UploadedPhotos/";

    @Override
    public void uploadFiles(String userName, MultipartFile[] uploadingPhotos) throws IOException {
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
        String savePath = getFolderPath(userName);
        boolean isNewDir = new File(savePath).mkdirs();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(savePath.concat(file.getOriginalFilename()));
        Files.write(path, bytes);
    }

    @Override
    public MultipartFile[] getFiles(String userName) throws IOException {
        String loadPath = getFolderPath(userName);
        Path path = Paths.get(loadPath);

        try(Stream<Path> paths = Files.walk(path)) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println(filePath);
                }
            });
        }

        return new MultipartFile[0];
    }

    private String getFolderPath(String userName) {
        String os = OperationSystemDetermination.getOperationSystem();
        boolean isWindows = os.contains(OperationSystemDetermination.WINDOWS);
        return isWindows ? UPLOADED_FOLDER_WINDOWS.concat(userName + "\\")
                : UPLOADED_FOLDER_LINUX.concat(userName + "/");
    }
}
