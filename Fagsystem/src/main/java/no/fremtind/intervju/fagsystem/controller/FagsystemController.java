package no.fremtind.intervju.fagsystem.controller;

import no.fremtind.intervju.fagsystem.controller.model.AvtaleStatus;
import no.fremtind.intervju.fagsystem.controller.model.OppdaterAvtaleStatusRequest;
import no.fremtind.intervju.fagsystem.controller.model.OpprettAvtaleRequest;
import no.fremtind.intervju.fagsystem.controller.model.OpprettKundeRequest;
import no.fremtind.intervju.fagsystem.service.FagsystemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class FagsystemController {

    private final FagsystemService fagsystemService;

    public FagsystemController(FagsystemService fagsystemService) {
        this.fagsystemService = fagsystemService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/kunde")
    public ResponseEntity<String> opprettKunde(@RequestBody OpprettKundeRequest request) {
        fagsystemService.opprettKunde(request);
        String kundenummer = fagsystemService.hentKundenummer(request);
        return ResponseEntity.ok((kundenummer));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/avtale")
    public ResponseEntity<Long> opprettAvtale(@RequestBody OpprettAvtaleRequest request) {
        fagsystemService.opprettAvtale(request);
        Long avtalenummer = fagsystemService.hentAvtalenummer(request);
        return ResponseEntity.ok((avtalenummer));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/oppdaterAvtaleStatus")
    public ResponseEntity<AvtaleStatus> oppdaterAvtaleStatus(@RequestBody OppdaterAvtaleStatusRequest request) {
        fagsystemService.oppdaterUtsendelseStatus(request);
        AvtaleStatus avtaleStatus = fagsystemService.hentAvtaleStatus(request.getAvtaleNummer());
        return ResponseEntity.ok((avtaleStatus));
    }
}

