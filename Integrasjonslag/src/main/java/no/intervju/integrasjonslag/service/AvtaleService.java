package no.intervju.integrasjonslag.service;

import no.intervju.integrasjonslag.client.brevtjeneste.BrevtjenesteRestClient;
import no.intervju.integrasjonslag.client.brevtjeneste.model.BrevStatus;
import no.intervju.integrasjonslag.client.brevtjeneste.model.SendAvtalebrevRequest;
import no.intervju.integrasjonslag.client.fagsystem.FagsystemRestClient;
import no.intervju.integrasjonslag.client.fagsystem.model.OppdaterAvtaleStatusRequest;
import no.intervju.integrasjonslag.client.fagsystem.model.OpprettFagsystemAvtaleRequest;
import no.intervju.integrasjonslag.client.fagsystem.model.OpprettKundeRequest;
import no.intervju.integrasjonslag.controller.model.AvtaleStatus;
import no.intervju.integrasjonslag.controller.model.OpprettAvtaleRequest;
import no.intervju.integrasjonslag.controller.model.OpprettAvtaleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static no.intervju.integrasjonslag.client.fagsystem.model.AvtaleType.FORSIKRING;
import static no.intervju.integrasjonslag.controller.model.AvtaleStatus.SENDT;

@Service
public class AvtaleService {
    private static final Logger logger = LoggerFactory.getLogger(AvtaleService.class);

    final
    FagsystemRestClient fagsystemRestClient;
    BrevtjenesteRestClient brevtjenesteRestClient;

    public AvtaleService(FagsystemRestClient fagsystemRestClient, BrevtjenesteRestClient brevtjenesteRestClient) {
        this.fagsystemRestClient = fagsystemRestClient;
        this.brevtjenesteRestClient = brevtjenesteRestClient;
    }

    public OpprettAvtaleResponse opprettAvtale(OpprettAvtaleRequest request) {
        // opprett kunde
        Long kundenummer = opprettKunde(new OpprettKundeRequest(
                request.getFnr(),
                request.getFornavn(),
                request.getEtternavn(),
                request.getTelefonnummer())
        );
        logger.info("Opprettet kunde med kundenummer: {}", kundenummer);

        Long avtaleNummer = opprettFagsystemAvtale(new OpprettFagsystemAvtaleRequest(
                kundenummer,
                FORSIKRING,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(12))
        );
        logger.info("Opprettet avtale med avtalenummer: {}", avtaleNummer);

        BrevStatus brevstatus = sendAvtaleTilKunde(avtaleNummer);
        logger.info("Brev sendt til kunde med status: {}", brevstatus);

        AvtaleStatus avtaleStatus = oppdaterAvtalestatus(new OppdaterAvtaleStatusRequest(avtaleNummer, SENDT));
        logger.info("Avtale oppdatert med ny avtalestatus: {}", avtaleStatus);

        return new OpprettAvtaleResponse(avtaleNummer, avtaleStatus);
    }

    private Long opprettKunde(OpprettKundeRequest request) {
        return fagsystemRestClient.opprettKunde(request);
    }
    private Long opprettFagsystemAvtale(OpprettFagsystemAvtaleRequest request) {
        return fagsystemRestClient.opprettAvtale(request);
    }

    private BrevStatus sendAvtaleTilKunde(Long avtaleNummer) {
        return brevtjenesteRestClient.sendAvtaleTilKunde(new SendAvtalebrevRequest(avtaleNummer));
    }

    private AvtaleStatus oppdaterAvtalestatus(OppdaterAvtaleStatusRequest request) {
        return fagsystemRestClient.oppdaterAvtalestatus(request);
    }
}