package edu.max.bstore.service;

import edu.max.bstore.dto.Order;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.entity.OrderBookEntity;
import edu.max.bstore.entity.OrderEntity;
import edu.max.bstore.entity.UserEntity;
import edu.max.bstore.repository.BookRepository;
import edu.max.bstore.repository.OrderBookRepository;
import edu.max.bstore.repository.OrderRepository;
import edu.max.bstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderBookRepository orderBookRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderBookRepository orderBookRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderBookRepository = orderBookRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void createOrder(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .userId(order.getUserName())
                .created(order.getCreated())
                .status(order.getStatus())
                .build();

        OrderEntity orderEn = orderRepository.save(orderEntity);

        OrderBookEntity orderBookEntity = OrderBookEntity.builder()
                .bookId(order.getBookId())
                .orderId(orderEn.getId())
                .build();

        orderBookRepository.save(orderBookEntity);
    }

    @Override
    public List<Order> getAllOrders() {
        List<OrderBookEntity> orderBookEntities = orderBookRepository.findAll();
        List<OrderEntity> orderEntities = orderRepository.findAll();

        List<Order> ordersList = new ArrayList<>();
        for (int i = 0; i < orderEntities.size(); i++) {
            UserEntity user = userRepository.findById(orderEntities.get(i).getUserId()).get();
            UUID bookId = orderBookEntities.get(i).getBookId();
            Optional<BookEntity> bookEntity = bookRepository.findById(bookId);

            ordersList.add(Order.builder()
                    .id(orderBookEntities.get(i).getOrderId())
                    .userName(orderEntities.get(i).getUserId())
                    .bookName(bookEntity.get().getName())
                            .firstNameCustomer(user.getFirstName())
                            .lastNameCustomer(user.getLastName())
                    .phoneNumber(user.getPhoneNumber())
                    .created(orderEntities.get(i).getCreated())
                    .status(orderEntities.get(i).getStatus())
                    .bookId(bookId)
                    .build());
        }
        return ordersList;
    }
}
