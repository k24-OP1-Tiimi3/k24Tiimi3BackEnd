// hh.lemmikkikauppa.lemmikkikauppaprojekti.web.KoirantuoteUIController.java
package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;

@Controller
public class KoirantuoteUIController {

    @Autowired
    private ProductRepository repository;

    // Näytä tuotteet ja tyhjä lomake oletuksena
    @GetMapping("/tuotteet")
    public String naytaTuotteet(Model model) {
        model.addAttribute("tuotteet", repository.findAll());
        model.addAttribute("valittutuote", new Product()); // uusi tuote lomaketta varten
        return "tuotteet";
    }

    // Lomake "muokkaa"-linkistä: täyttää lomakkeen valitulla tuotteella
    @GetMapping("/muokkaa/{id}")
    public String muokkaaTuote(@PathVariable Long id, Model model) {
        Optional<Product> tuote = repository.findById(id);
        model.addAttribute("valittutuote", tuote.orElse(new Product())); // jos ei löydy, annetaan tyhjä
        model.addAttribute("tuotteet", repository.findAll());
        return "tuotteet";
    }

    // Lisää tai päivitä tuote (POST-lomake)
    @PostMapping("/paivita")
    public String paivitaTuote(@ModelAttribute Product tuote) {
        repository.save(tuote); // sama metodi toimii sekä uudelle että päivitettävälle tuotteelle
        return "redirect:/tuotteet";
    }
}
