// hh.lemmikkikauppa.lemmikkikauppaprojekti.web.KoirantuoteUIController.java
package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.model.KoiranTuote;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.repository.KoirantuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class KoirantuoteUIController {

    @Autowired
    private KoirantuoteRepository repository;

    // Näytä tuotteet ja tyhjä lomake oletuksena
    @GetMapping("/tuotteet")
    public String naytaTuotteet(Model model) {
        model.addAttribute("tuotteet", repository.findAll());
        model.addAttribute("valittutuote", new KoiranTuote()); // uusi tuote lomaketta varten
        return "tuotteet";
    }

    // Lomake "muokkaa"-linkistä: täyttää lomakkeen valitulla tuotteella
    @GetMapping("/muokkaa/{id}")
    public String muokkaaTuote(@PathVariable Long id, Model model) {
        Optional<KoiranTuote> tuote = repository.findById(id);
        model.addAttribute("valittutuote", tuote.orElse(new KoiranTuote())); // jos ei löydy, annetaan tyhjä
        model.addAttribute("tuotteet", repository.findAll());
        return "tuotteet";
    }

    // Lisää tai päivitä tuote (POST-lomake)
    @PostMapping("/paivita")
    public String paivitaTuote(@ModelAttribute KoiranTuote tuote) {
        repository.save(tuote); // sama metodi toimii sekä uudelle että päivitettävälle tuotteelle
        return "redirect:/tuotteet";
    }
}