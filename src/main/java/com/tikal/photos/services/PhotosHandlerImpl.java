package com.tikal.photos.services;

import com.tikal.utils.OperationSystemDetermination;
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
import java.util.List;
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
    public List<String> getFilesNames(String userName) throws IOException {
        String loadPath = getFolderPath(userName);
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
    public byte[] getPhoto(String userName, String photoName) {
        String filePathStr = getFolderPath(userName) + photoName;
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

    private String getFolderPath(String userName) {
        String os = OperationSystemDetermination.getOperationSystem();
        boolean isWindows = os.contains(OperationSystemDetermination.WINDOWS);
        return isWindows ? UPLOADED_FOLDER_WINDOWS.concat(userName + "\\")
                : UPLOADED_FOLDER_LINUX.concat(userName + "/");
    }
}
