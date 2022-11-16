package edu.max.bstore.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Author {
    private String id;
    private String name;
    private List<Book> bookList;
}
