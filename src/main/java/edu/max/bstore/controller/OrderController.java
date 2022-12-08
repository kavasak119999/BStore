package edu.max.bstore.controller;

import edu.max.bstore.dto.Order;
import edu.max.bstore.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    public ModelAndView showOrders(ModelMap map) {
        List<Order> orders = orderService.getAllOrders();
        orders.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        map.addAttribute("data", orders);
        return new ModelAndView("pages/orders", map);
    }

    @PostMapping(value = "/confirm-order")
    public String confirmOrder(@RequestParam(value = "userId") String userName,
                               @RequestParam(value = "bookName") String bookName,
                               @RequestParam(value = "status") String status,
                               @RequestParam(value = "bookId") UUID bookId) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        currentDateTime.format(format);

        Order order = Order.builder()
                .bookName(bookName)
                .userName(userName)
                .bookId(bookId)
                .created(currentDateTime.format(format))
                .status(Integer.valueOf(status))
                .build();

        orderService.createOrder(order);

        return "redirect:";
    }

    @PostMapping(value = "/orders/complete")
    public String switchStatusToOrder(@RequestParam String id){
        orderService.switchStatusToOrder(id);
        return "redirect:";
    }
}
