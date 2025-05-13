package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Customer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.CustomerRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Order;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.OrderRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;



@Controller
public class OrderController {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Show all orders
    @GetMapping("orders")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }

    // Show orders in JSON format
    @GetMapping("api/orders")
    @ResponseBody
    public Iterable<Order> orderJson() {
        return orderRepository.findAll();
    }
    

    // Post new order 

    //accepted JSON format example:
    // {
    //     "customerId": 1,
    //     "productIds": [1, 2, 3]
    // }

    @PostMapping("api/orders")
    @ResponseBody
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.customerId).orElse(null);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Product> products = new ArrayList<>();
        productRepository.findAllById(orderRequest.productIds).forEach(products::add);
        
        // Decrease inventory for each product
        for (Product product : products) {
            if (product.getInventory() > 0) {
                product.setInventory(product.getInventory() - 1);
                productRepository.save(product);
            } else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
            }
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(products);
        order.setState("In Progress");

        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    
    public static class OrderRequest {
        public Long customerId;
        public List<Long> productIds;
    }

}
