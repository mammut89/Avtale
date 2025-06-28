package no.intervju.integrasjonslag.client.brevtjeneste;

import no.intervju.integrasjonslag.client.brevtjeneste.model.BrevStatus;
import no.intervju.integrasjonslag.client.brevtjeneste.model.SendAvtalebrevRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrevtjenesteRestClient {

    private final RestTemplate restTemplate;

    @Value("${brevtjeneste.api.base-url}")
    private String baseUrl;

    public BrevtjenesteRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public BrevStatus sendAvtaleTilKunde(SendAvtalebrevRequest request) {
        String url = baseUrl + "/sendAvtale";
        return restTemplate.postForObject(url, request, BrevStatus.class);
    }
}