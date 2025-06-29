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
        if(kundeRepository.findByFnr(request.fnr()).isEmpty()) {
            Kunde entity = new Kunde();
            entity.setFnr(request.fnr());
            kundeRepository.save(entity);
        }
    }

    public Optional<String> hentKundenummer(String fnr) {
        return kundeRepository.findByFnr(fnr)
                .stream()
                .findFirst()
                .map(kunde -> String.valueOf(kunde.getId()));
    }

    /**
     * Fix så det ikke er mulig å lage flere avtaler på samme registreringsnummer når det allerede er en avtale som ikke har passert sluttdato
     */
    public void opprettAvtale(OpprettAvtaleRequest request) {
        Avtale avtale = new Avtale();
        avtale.setKundeId(request.kundeNummer());
        avtale.setAvtaleStatus(AvtaleStatus.OPPRETTET);
        avtale.setRegistreringsnummer(request.registreringsnummer());
        avtale.setStartDato(request.startDato());
        avtale.setSluttDato(request.startDato());
        avtaleRepository.save(avtale);
    }

    public Long hentAvtalenummer(OpprettAvtaleRequest request) {
        return avtaleRepository.findByKundeId(request.kundeNummer())
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
        avtaleRepository.findById(request.avtaleNummer())
                .ifPresentOrElse(avtale -> {
                    avtale.setAvtaleStatus(request.avtaleStatus());
                    avtaleRepository.save(avtale);
                }, () -> {
                    throw new EntityNotFoundException("Fant ingen avtale med id " + request.avtaleNummer());
                });
    }

    public AvtaleStatus hentAvtaleStatus(Long avtaleId) {
        Optional<Avtale> avtale = hentAvtale(avtaleId);
        return avtale
                .map(Avtale::getAvtaleStatus)
                .orElseThrow(() -> new IllegalArgumentException("Avtale ikke funnet for id: " + avtaleId));
    }
}
