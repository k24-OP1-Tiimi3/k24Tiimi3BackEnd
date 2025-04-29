// hh.lemmikkikauppa.lemmikkikauppaprojekti.web.KoirantuoteUIController.java
package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Manufacturer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ManufacturerRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductTypeRepository;
import jakarta.validation.Valid;


@Controller
public class KoirantuoteUIController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;


    // Näytä tuotteet ja tyhjä lomake oletuksena
    @GetMapping("/tuotteet")
    public String naytaTuotteet(
        @RequestParam(name = "manufacturerId", required = false) Long manufacturerId,
        Model model) {

    List<Product> tuotteet;
    if (manufacturerId != null) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElse(null);
        tuotteet = productRepository.findByManufacturer(manufacturer);
        model.addAttribute("selectedManufacturerId", manufacturerId);
    } else {
        tuotteet = (List<Product>) productRepository.findAll();
    }

    model.addAttribute("tuotteet", tuotteet);
    model.addAttribute("valittutuote", new Product());
    model.addAttribute("manufacturers", manufacturerRepository.findAll());
    model.addAttribute("types", productTypeRepository.findAll());

    return "tuotteet";
    }


    // Lomake "muokkaa"-linkistä: täyttää lomakkeen valitulla tuotteella
    @GetMapping("/muokkaa/{id}")
    public String muokkaaTuote(@PathVariable Long id, Model model) {
        Optional<Product> tuote = productRepository.findById(id);
        model.addAttribute("valittutuote", tuote.orElse(new Product())); // jos ei löydy, annetaan tyhjä
        model.addAttribute("tuotteet", productRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("types", productTypeRepository.findAll());
        return "tuotteet";
    }

    // Lisää tai päivitä tuote (POST-lomake)
    @PostMapping("/paivita")
    public String paivitaTuote(@Valid @ModelAttribute("valittutuote") Product tuote, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("tuotteet", productRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("types", productTypeRepository.findAll());
        return "tuotteet";
    }

    productRepository.save(tuote);
    return "redirect:/tuotteet";
    }

    // Poista tuote (GET-lomake)
    @GetMapping("/poista/{id}")
    public String poistaTuote(@PathVariable Long id) {
        productRepository.deleteById(id); // poistaa tuotteen id:n perusteella
        return "redirect:/tuotteet"; // ohjaa takaisin tuotteet-sivulle
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
}
