package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Manufacturer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ManufacturerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;

import java.util.List;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/manufacturerlist")
    public String getManufactures(Model model) {
        model.addAttribute("manufactures", manufacturerRepository.findAll());
        return "manufacturerlist";
    }

    @GetMapping("/addmanufacturer")
    public String showAddManufacturerForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "addmanufacturer";
    }

    @PostMapping("/addmanufacturer")
    public String addManufacturer(@Valid @ModelAttribute Manufacturer manufacturer, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("manufacturers", manufacturerRepository.findAll());
            return "addmanufacturer";
        }
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturerlist";
    }

    @GetMapping("/deletemanufacturer/{id}")
    public String deleteManufacturer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid manufacturer Id:" + id));
        List<Product> products = productRepository.findByManufacturer(manufacturer);
        if (products != null && !products.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Cannot delete manufacturer " + manufacturer.getName() + " because it has associated products");
            return "redirect:/manufacturerlist";
        }
        manufacturerRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Manufacturer deleted successfully");
        return "redirect:/manufacturerlist";
    }
}
