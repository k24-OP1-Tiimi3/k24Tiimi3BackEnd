package hh.lemmikkikauppa.lemmikkikauppaprojekti.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerid;
    private String name;

    private List<Product> products;

    public Manufacturer() {
        this.manufacturerid = null;
        this.name = null;
        this.products = null;
    }

    public Long getManufacturerid() {
        return manufacturerid;
    }

    public void setManufacturerid(Long manufacturerid) {
        this.manufacturerid = manufacturerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Manufacturer [manufacturerid=" + manufacturerid + ", name=" + name + "]";
    }

    
}
