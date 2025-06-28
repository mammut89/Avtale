package no.fremtind.intervju.integrasjonslag.client.fagsystem;

import no.fremtind.intervju.integrasjonslag.client.fagsystem.model.OppdaterAvtaleStatusRequest;
import no.fremtind.intervju.integrasjonslag.client.fagsystem.model.OpprettFagsystemAvtaleRequest;
import no.fremtind.intervju.integrasjonslag.client.fagsystem.model.OpprettKundeRequest;
import no.fremtind.intervju.integrasjonslag.controller.model.AvtaleStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FagsystemRestClient {

    private final RestTemplate restTemplate;

    @Value("${fagsystem.api.base-url}")
    private String baseUrl;

    public FagsystemRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Long opprettKunde(OpprettKundeRequest request) {
        String url = baseUrl + "/kunde";
        return restTemplate.postForObject(url, request, Long.class);
    }

    public Long opprettAvtale(OpprettFagsystemAvtaleRequest request) {
        String url = baseUrl + "/avtale";
        return restTemplate.postForObject(url, request, Long.class);
    }

    public AvtaleStatus oppdaterAvtalestatus(OppdaterAvtaleStatusRequest request) {
        String url = baseUrl + "/oppdaterAvtaleStatus";
        return restTemplate.postForObject(url, request, AvtaleStatus.class);
    }
}