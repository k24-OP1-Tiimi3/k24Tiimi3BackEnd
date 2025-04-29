package hh.lemmikkikauppa.lemmikkikauppaprojekti.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Inventory must be a positive number")
    private int inventory;

    private String color;

    @Enumerated(EnumType.STRING)
    private Size size;
    
    @DecimalMin(value = "0.01", inclusive = false, message = "Price must be a positive number")
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "producttype_id", nullable = false)
    private ProductType type;
    
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;
    
    //Possible sizes
    public enum Size {
        S, M, L,
    }


    public void validateSizeForType() {
        if (type != null && type.getName() != ProductType.TypeName.Vaate && size != null) {
            throw new IllegalArgumentException("Size can only be set for products type of 'Vaate'");
        }
    }

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

    public ProductType getType() {
        return this.type;
    }

    public void setType(ProductType type) {
        this.type = type;
        validateSizeForType();
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
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
