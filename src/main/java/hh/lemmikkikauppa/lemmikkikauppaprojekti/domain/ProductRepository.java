package hh.lemmikkikauppa.lemmikkikauppaprojekti.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByType(String type);
    List<Product> findByManufacturer(Manufacturer manufacturer);

}
