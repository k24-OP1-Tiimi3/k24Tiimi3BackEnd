package hh.lemmikkikauppa.lemmikkikauppaprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.ProductRepository;

@SpringBootApplication
public class LemmikkikauppaprojektiApplication {

	private static final Logger log = LoggerFactory.getLogger(LemmikkikauppaprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LemmikkikauppaprojektiApplication.class, args);
	}

	

}
