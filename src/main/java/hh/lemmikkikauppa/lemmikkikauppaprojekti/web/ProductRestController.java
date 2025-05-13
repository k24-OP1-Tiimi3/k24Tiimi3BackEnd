package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Customer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.CustomerRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Manufacturer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ManufacturerRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductType;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductTypeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;



    @GetMapping("/products")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @GetMapping("/products/type/{typeId}")
    public Optional<ProductType> getProductsByType(@PathVariable Long typeId) {
        return productTypeRepository.findById(typeId);
    }

    @GetMapping("/manufacturers")
    public Iterable<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @GetMapping("/manufacturers/{id}")
    public Optional<Manufacturer> getManufacturerById(@PathVariable Long id) {
        return manufacturerRepository.findById(id);
    }

    @GetMapping("/types")
    public Iterable<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    @GetMapping("/customers")
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

}
