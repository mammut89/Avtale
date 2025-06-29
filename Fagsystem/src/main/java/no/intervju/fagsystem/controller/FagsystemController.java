package no.intervju.fagsystem.controller;

import no.intervju.fagsystem.controller.model.AvtaleStatus;
import no.intervju.fagsystem.controller.model.OppdaterAvtaleStatusRequest;
import no.intervju.fagsystem.controller.model.OpprettAvtaleRequest;
import no.intervju.fagsystem.controller.model.OpprettKundeRequest;
import no.intervju.fagsystem.service.FagsystemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class FagsystemController {

    private final FagsystemService fagsystemService;

    public FagsystemController(FagsystemService fagsystemService) {
        this.fagsystemService = fagsystemService;
    }

    //TODO Forbedringspotensiale: validere fnr mot folkeregisteret, sjekke om DNR eventuelt bør støttes
    @RequestMapping(method = RequestMethod.POST, value = "/kunde")
    public ResponseEntity<String> opprettKunde(@RequestBody OpprettKundeRequest request) {
        fagsystemService.opprettKunde(request);
        Optional<String> kundenummer = fagsystemService.hentKundenummer(request.fnr());
        return kundenummer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO Forbedringspotensiale: validere registreringsnummer mot autosys eller annet bil register
    @RequestMapping(method = RequestMethod.POST, value = "/avtale")
    public ResponseEntity<Long> opprettAvtale(@RequestBody OpprettAvtaleRequest request) {
        fagsystemService.opprettAvtale(request);
        Long avtalenummer = fagsystemService.hentAvtalenummer(request);
        return ResponseEntity.ok((avtalenummer));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/oppdaterAvtaleStatus")
    public ResponseEntity<AvtaleStatus> oppdaterAvtaleStatus(@RequestBody OppdaterAvtaleStatusRequest request) {
        fagsystemService.oppdaterUtsendelseStatus(request);
        AvtaleStatus avtaleStatus = fagsystemService.hentAvtaleStatus(request.avtaleNummer());
        return ResponseEntity.ok((avtaleStatus));
    }
}

