package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.KoiranTuote;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.KoirantuoteRepository;

@Controller
public class KoiranVaateController {

    private final KoirantuoteRepository koirantuoteRepository;

    public KoiranVaateController(KoirantuoteRepository koirantuoteRepository) {
        this.koirantuoteRepository = koirantuoteRepository;
    }

    @GetMapping("/tuotteet")
    public String showKoiranTuotteet(Model model) {
        model.addAttribute("tuotteet", koirantuoteRepository.findAll());
        return "tuotteet";
    }
}
