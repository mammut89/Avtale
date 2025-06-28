package no.intervju.fagsystem.repository;

import no.intervju.fagsystem.model.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KundeRepository extends JpaRepository<Kunde, Long> {
  //  List<Kunde> findByFnr(String fnr);
    Optional<Kunde> findByFnr(String fnr);

}
