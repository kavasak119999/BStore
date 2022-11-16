package edu.max.bstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Book {
    private String id;
    private String name;
    private Author author;
    private String title;
    private String category;
    private String base64Image;
    private Double price;
    private Integer count;
}
