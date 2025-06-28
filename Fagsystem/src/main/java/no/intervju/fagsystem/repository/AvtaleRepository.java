package no.intervju.fagsystem.repository;

import no.intervju.fagsystem.model.Avtale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvtaleRepository extends JpaRepository<Avtale, Long> {
    List<Avtale> findByKundeId(Long kundeId);
}

