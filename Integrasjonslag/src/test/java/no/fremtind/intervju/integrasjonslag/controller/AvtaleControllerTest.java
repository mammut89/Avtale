package no.fremtind.intervju.integrasjonslag.controller;

import no.fremtind.intervju.integrasjonslag.controller.model.AvtaleStatus;
import no.fremtind.intervju.integrasjonslag.controller.model.OpprettAvtaleRequest;
import no.fremtind.intervju.integrasjonslag.controller.model.OpprettAvtaleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvtaleControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createUser_shouldReturnAvtaleResponse() {
        OpprettAvtaleRequest request = new OpprettAvtaleRequest(
                "12345678901",
                "Ola",
                "Nordmann",
                "12345678"
        );

        ResponseEntity<OpprettAvtaleResponse> response = restTemplate.postForEntity(
                "/api/opprettavtale",
                request,
                OpprettAvtaleResponse.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAvtalenummer()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(AvtaleStatus.SENDT);
    }
}
