package hh.lemmikkikauppa.lemmikkikauppaprojekti.repository;

import org.springframework.data.repository.CrudRepository;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.model.KoiranTuote;

public interface KoirantuoteRepository extends CrudRepository<KoiranTuote, Long> {
}
