package edu.max.bstore.tools;

import edu.max.bstore.dto.Author;
import edu.max.bstore.dto.Book;
import edu.max.bstore.dto.User;
import edu.max.bstore.entity.AuthorEntity;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.entity.UserEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

public class EntityToDto {

    public static Book bookEntityToDto(BookEntity bookEntity) {
        String base64Image = "";
        try {
            String fileName = bookEntity.getImage().getFileName();
            System.out.println(new File(".").getAbsolutePath());
            BufferedImage bf = ImageIO.read(new File("container/docker/images/" + fileName));

            ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
            ImageIO.write(bf, fileName.substring(fileName.lastIndexOf('.') + 1), outStreamObj);

            base64Image = Base64.getEncoder().encodeToString(outStreamObj.toByteArray());

            outStreamObj.close();
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("No image with name: '" + bookEntity.getImage().getFileName() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Book.builder()
                .id(String.valueOf(bookEntity.getId()))
                .name(bookEntity.getName())
                .base64Image(base64Image)
                .author(bookEntity.getAuthor().getName())
                .category(bookEntity.getCategory())
                .title(bookEntity.getTitle())
                .price(bookEntity.getPrice())
                .count(bookEntity.getCount())
                .build();
    }


    public static Author authorEntityToDto(AuthorEntity authorEntity) {
        return Author.builder()
                .id(String.valueOf(authorEntity.getId()))
                .name(authorEntity.getName())
                .bookList(authorEntity.getBookEntityList().stream()
                        .map(EntityToDto::bookEntityToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
