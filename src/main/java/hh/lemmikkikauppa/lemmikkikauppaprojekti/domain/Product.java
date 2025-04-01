package hh.lemmikkikauppa.lemmikkikauppaprojekti.domain;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String manufactorer;
    private int inventory;

    private String type;
    private String color;
    private String size;
    private double price;

    // Getterit ja setterit...

    // id, name, manufactorer, inventory
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getname() { return name; }
    public void setname(String name) { this.name = name; }

    public String getmanufactorer() { return manufactorer; }
    public void setmanufactorer(String manufactorer) { this.manufactorer = manufactorer; }

    public int getinventory() { return inventory; }
    public void setinventory(int inventory) { this.inventory = inventory; }

    // Uudet kentät
    public String gettype() { return type; }
    public void settypei(String typei) { this.type = typei; }

    public String getcolor() { return color; }
    public void setcolor(String color) { this.color = color; }

    public String getsize() { return size; }
    public void setsize(String size) { this.size = size; }

    public double getprice() { return price; }
    public void setprice(double price) { this.price = price; }
}
