package edu.max.bstore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_price")
    private Double price;

    @Column(name = "book_category")
    private String category;

    @Column(name = "book_count")
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
            (name = "image_id"
                    , referencedColumnName = "id")
    private ImageEntity image;
}
