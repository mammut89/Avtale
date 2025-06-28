package no.intervju.fagsystem.service;

import jakarta.persistence.EntityNotFoundException;
import no.intervju.fagsystem.controller.model.AvtaleStatus;
import no.intervju.fagsystem.controller.model.OppdaterAvtaleStatusRequest;
import no.intervju.fagsystem.controller.model.OpprettAvtaleRequest;
import no.intervju.fagsystem.controller.model.OpprettKundeRequest;
import no.intervju.fagsystem.model.Avtale;
import no.intervju.fagsystem.model.Kunde;
import no.intervju.fagsystem.repository.AvtaleRepository;
import no.intervju.fagsystem.repository.KundeRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class FagsystemService {
    private static final Logger logger = LoggerFactory.getLogger(FagsystemService.class);

    private final AvtaleRepository avtaleRepository;
    private final KundeRepository  kundeRepository;

    public FagsystemService(AvtaleRepository avtaleRepository, KundeRepository kundeRepository) {
        this.avtaleRepository = avtaleRepository;
        this.kundeRepository = kundeRepository;
    }

    public void opprettKunde(OpprettKundeRequest request) {
        if(kundeRepository.findByFnr(request.getFnr()).isEmpty()) {
            Kunde entity = new Kunde();
            entity.setFnr(request.getFnr());
            kundeRepository.save(entity);
        }
    }

    public String hentKundenummer(OpprettKundeRequest request) {
        return kundeRepository.findByFnr(request.getFnr())
                .stream()
                .findFirst()
                .map(kunde -> String.valueOf(kunde.getId()))
                .orElse(null);
    }

    /**
     * Fix så det ikke er mulig å lage flere avtale når det allerede er en opprettet avtale som ikke har passert sluttdato
     */
    public void opprettAvtale(OpprettAvtaleRequest request) {
        Avtale entity = new Avtale();
        entity.setKundeId(request.getKundeNummer());
        entity.setAvtaleStatus(AvtaleStatus.OPPRETTET);
        avtaleRepository.save(entity);
    }

    public Long hentAvtalenummer(OpprettAvtaleRequest request) {
        return avtaleRepository.findByKundeId(request.getKundeNummer())
                .stream()
                .findFirst()
                .map(Avtale::getId)
                .orElse(null);
    }

    public Optional<Avtale> hentAvtale(Long avtaleId) {
        return avtaleRepository.findById(avtaleId)
                .stream()
                .findFirst();
    }

    public void oppdaterUtsendelseStatus(OppdaterAvtaleStatusRequest request) {
        avtaleRepository.findById(request.getAvtaleNummer())
                .ifPresentOrElse(avtale -> {
                    avtale.setAvtaleStatus(request.getAvtaleStatus());
                    avtaleRepository.save(avtale);
                }, () -> {
                    throw new EntityNotFoundException("Fant ingen avtale med id " + request.getAvtaleNummer());
                });
    }

    public AvtaleStatus hentAvtaleStatus(Long avtaleId) {
        Optional<Avtale> avtale = hentAvtale(avtaleId);
        return avtale
                .map(Avtale::getAvtaleStatus)
                .orElseThrow(() -> new IllegalArgumentException("Avtale ikke funnet for id: " + avtaleId));
    }
}
