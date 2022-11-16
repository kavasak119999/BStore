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
            System.out.println(bookEntity.getImage().getFileName());
            BufferedImage bf = ImageIO.read(new File("container/docker/images/" + bookEntity.getImage().getFileName()));

            ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
            ImageIO.write(bf, "jpg", outStreamObj);

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
                .author(EntityToDto.authorEntityToDtoCustom(bookEntity.getAuthor()))
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

    private static Author authorEntityToDtoCustom(AuthorEntity authorEntity) {
        return Author.builder()
                .id(String.valueOf(authorEntity.getId()))
                .name(authorEntity.getName())
                .build();
    }
}
