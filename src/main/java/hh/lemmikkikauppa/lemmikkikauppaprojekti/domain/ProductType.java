package hh.lemmikkikauppa.lemmikkikauppaprojekti.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

<<<<<<< HEAD
    @Enumerated(EnumType.STRING)
    private TypeName name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
=======
    @NotBlank(message = "Name is required")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
>>>>>>> 17e05d04e51de3cffb25def7c6b82d61e02c5e35
    private List<Product> products = new ArrayList<>();

    // Getters and setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeName getName() {
        return this.name;
    }

    public void setName(TypeName name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }
<<<<<<< HEAD

    // Possible type names

    public enum TypeName {
        Vaate, Lelu, Ruoka
    }
    
=======
>>>>>>> 17e05d04e51de3cffb25def7c6b82d61e02c5e35

}
