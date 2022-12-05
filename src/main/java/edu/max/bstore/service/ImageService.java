package edu.max.bstore.service;

import edu.max.bstore.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageEntity saveImage(String fineName);

    void saveImageToVolumeStorage(MultipartFile image);
}
