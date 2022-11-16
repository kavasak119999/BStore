package edu.max.bstore.service;

import edu.max.bstore.entity.ImageEntity;
import edu.max.bstore.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
//        File uploadDir = new File("container/docker/images");
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//        if (image != null) {
//            try {
//                File file = new File("container/docker/images" + "/" + image.getOriginalFilename());
//                image.transferTo(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
        try {
            InputStream in = imaged.getInputStream();
            byte[] buf = new byte[in.available()];
            in.read(buf);

            System.out.println(imaged.getOriginalFilename());
            File targetFile = new File("container/docker/images/" + imaged.getOriginalFilename());

            try(OutputStream outputStream = new FileOutputStream(targetFile)) {
                outputStream.write(buf);
            }

//            System.out.println(imaged.getOriginalFilename());
//            BufferedImage bf = ImageIO.read(imaged);
//
//            ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
//            ImageIO.write(bf, "jpg", outStreamObj);
//
//            base64Image = outStreamObj.toByteArray();

//            outStreamObj.close();
//            Path dir = Files.createDirectories(Paths.get("container/docker/images"));
//            OutputStream outStreamObjj = Files.newOutputStream(dir.resolve(imaged.getOriginalFilename()));
//            outStreamObjj.write(base64Image);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
