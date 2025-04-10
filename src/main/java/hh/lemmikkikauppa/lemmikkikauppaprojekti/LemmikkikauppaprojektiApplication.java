package hh.lemmikkikauppa.lemmikkikauppaprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.AppUser;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.AppUserRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Manufacturer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ManufacturerRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Product;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;

@SpringBootApplication
public class LemmikkikauppaprojektiApplication {

	private static final Logger log = LoggerFactory.getLogger(LemmikkikauppaprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LemmikkikauppaprojektiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, AppUserRepository appUserRepository) {
		return (args) -> {
			// Luodaan valmistajia
			Manufacturer dogWear = new Manufacturer();
			dogWear.setName("DogWear Inc.");
			manufacturerRepository.save(dogWear);

			Manufacturer petStyle = new Manufacturer();
			petStyle.setName("PetStyle Co.");
			manufacturerRepository.save(petStyle);

			log.info("Tallennetut valmistajat:");
			manufacturerRepository.findAll().forEach(manufacturer -> log.info(manufacturer.toString()));

			
			Product winterCoat = new Product();
			winterCoat.setName("Talvitakki");
			winterCoat.setInventory(10);
			winterCoat.setType("Takki");
			winterCoat.setColor("Punainen");
			winterCoat.setSize("M");
			winterCoat.setPrice(49.99);
			winterCoat.setManufacturer(dogWear);
			productRepository.save(winterCoat);

			Product rainCoat = new Product();
			rainCoat.setName("Sadetakki");
			rainCoat.setInventory(15);
			rainCoat.setType("Takki");
			rainCoat.setColor("Sininen");
			rainCoat.setSize("L");
			rainCoat.setPrice(39.99);
			rainCoat.setManufacturer(petStyle);
			productRepository.save(rainCoat);

			log.info("Tallennetut tuotteet:");
			productRepository.findAll().forEach(product -> log.info(product.toString()));
			

			AppUser test = new AppUser();
			test.setPasswordHash("$2a$10$jLI6uLO7dLA.oY5ZoW.pX.TtX9pIvXBH6KQ.53jXc3T8LRYq/Raoy");
			test.setRole("ROLE_ADMIN");
			test.setUsername("admin");
			appUserRepository.save(test);
		};
	}
}
