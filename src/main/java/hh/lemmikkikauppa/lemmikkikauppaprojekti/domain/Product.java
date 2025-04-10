package hh.lemmikkikauppa.lemmikkikauppaprojekti.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Inventory must be a positive number")
    private int inventory;

    private String type;
    private String color;
    private String size;

    @DecimalMin(value = "0.01", inclusive = false, message = "Price must be a positive number")
    private double price;

    @ManyToOne
    private Manufacturer manufacturer;

    // Getterit ja setterit...

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return this.inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", inventory=" + inventory + ", type=" + type + ", color="
                + color + ", size=" + size + ", price=" + price + ", manufacturer=" + manufacturer + "]";
    }

}
