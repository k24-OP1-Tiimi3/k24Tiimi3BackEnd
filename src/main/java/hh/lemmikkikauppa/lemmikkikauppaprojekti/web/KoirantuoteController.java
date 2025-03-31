package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.model.KoiranTuote;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.repository.KoirantuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tuotteet")
public class KoirantuoteController {

    @Autowired
    private KoirantuoteRepository repository;

    @GetMapping
    public Iterable<KoiranTuote> haeKaikki() {
        return repository.findAll();
    }

    @PostMapping
    public KoiranTuote lisaaTuote(@RequestBody KoiranTuote tuote) {
        return repository.save(tuote);
    }

    @GetMapping("/{id}")
    public Optional<KoiranTuote> haeYksi(@PathVariable Long id) {
        return repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void poistaTuote(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public KoiranTuote paivitaTuote(@PathVariable Long id, @RequestBody KoiranTuote uusiTuote) {
        return repository.findById(id).map(tuote -> {
            tuote.setNimi(uusiTuote.getNimi());
            tuote.setValmistaja(uusiTuote.getValmistaja());
            tuote.setMaaraVarastossa(uusiTuote.getMaaraVarastossa());
            tuote.setTyyppi(uusiTuote.getTyyppi());
            tuote.setVari(uusiTuote.getVari());
            tuote.setKoko(uusiTuote.getKoko());
            tuote.setHinta(uusiTuote.getHinta());
            return repository.save(tuote);
        }).orElseGet(() -> {
            uusiTuote.setId(id);
            return repository.save(uusiTuote);
        });
    }
}
