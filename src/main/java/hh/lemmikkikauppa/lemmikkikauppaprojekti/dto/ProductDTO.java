package hh.lemmikkikauppa.lemmikkikauppaprojekti.dto;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;

public class ProductDTO {
    private Long id;
    private String name;
    private int inventory;
    private String color;
    private String size;
    private double price;
    private Long typeId;
    private String typeName;
    private Long manufacturerId;
    private String manufacturerName;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.inventory = product.getInventory();
        this.color = product.getColor();
        this.size = product.getSize();
        this.price = product.getPrice();

        if (product.getType() != null) {
            this.typeId = product.getType().getId();
            this.typeName = product.getType().getName();
        }

        if (product.getManufacturer() != null) {
            this.manufacturerId = product.getManufacturer().getManufacturerid();
            this.manufacturerName = product.getManufacturer().getName();
        }
    }

    // Getterit
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return inventory;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public Long getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }
}