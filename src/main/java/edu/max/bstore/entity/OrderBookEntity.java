package edu.max.bstore.entity;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_books")
public class OrderBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "orders_id")
    private Long orderId;

    @Column(name = "books_id")
    private UUID bookId;
}
