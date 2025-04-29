package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Manufacturer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ManufacturerRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductType;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductTypeRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Pet Store API", description = "REST API for pet store products and manufacturers")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Operation(summary = "Get all products", description = "Returns all products from database")
    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get product by ID", description = "Returns product with the specified ID")
    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductDTO::new).orElse(null);
    }

    @Operation(summary = "Get products by type ID", description = "Returns all products belonging to a specific type")
    @GetMapping("/products/type/{typeId}")
    public List<ProductDTO> getProductsByType(@PathVariable Long typeId) {
        Optional<ProductType> type = productTypeRepository.findById(typeId);
        if (type.isPresent()) {
            return type.get().getProducts().stream()
                    .map(ProductDTO::new)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Operation(summary = "Get all manufacturers", description = "Returns all manufacturers from database")
    @GetMapping("/manufacturers")
    public Iterable<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Operation(summary = "Get manufacturer by ID", description = "Returns manufacturer with the specified ID")
    @GetMapping("/manufacturers/{id}")
    public Optional<Manufacturer> getManufacturerById(@PathVariable Long id) {
        return manufacturerRepository.findById(id);
    }

    @Operation(summary = "Get all product types", description = "Returns all product types from database")
    @GetMapping("/types")
    public Iterable<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }
}
