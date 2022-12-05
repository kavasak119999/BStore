package edu.max.bstore.service;

import edu.max.bstore.entity.ImageEntity;
import edu.max.bstore.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageEntity saveImage(String fileName) {
        ImageEntity imageEntity = ImageEntity.builder()
                .fileName(fileName)
                .build();

        return imageRepository.save(imageEntity);
    }

    @Override
    public void saveImageToVolumeStorage(MultipartFile imaged) {
        try {
            InputStream in = imaged.getInputStream();
            byte[] buf = new byte[in.available()];
            in.read(buf);

            File targetFile = new File("container/docker/images/" + imaged.getOriginalFilename());

            try (OutputStream outputStream = new FileOutputStream(targetFile)) {
                outputStream.write(buf);
            }
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
