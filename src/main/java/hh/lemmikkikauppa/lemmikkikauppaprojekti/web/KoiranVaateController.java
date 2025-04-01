package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.KoiranTuote;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;

@Controller
public class KoiranVaateController {

    private final ProductRepository koirantuoteRepository;

    public KoiranVaateController(ProductRepository koirantuoteRepository) {
        this.koirantuoteRepository = koirantuoteRepository;
    }

    @GetMapping("/tuotteet")
    public String getKoiranTuotteet(Model model) {
        model.addAttribute("tuotteet", koirantuoteRepository.findAll());
        return "tuotteet";
    }
    
}
