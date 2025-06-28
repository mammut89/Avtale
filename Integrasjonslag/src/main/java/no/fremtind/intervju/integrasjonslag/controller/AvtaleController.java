package no.fremtind.intervju.integrasjonslag.controller;


import no.fremtind.intervju.integrasjonslag.controller.model.OpprettAvtaleRequest;
import no.fremtind.intervju.integrasjonslag.controller.model.OpprettAvtaleResponse;
import no.fremtind.intervju.integrasjonslag.service.AvtaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/opprettavtale")
public class AvtaleController {

    private final AvtaleService avtaleService;

    public AvtaleController(AvtaleService avtaleService) {
        this.avtaleService = avtaleService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OpprettAvtaleResponse> createUser(@RequestBody OpprettAvtaleRequest request) {
    OpprettAvtaleResponse response = avtaleService.opprettAvtale(request);
    return ResponseEntity.ok(response);
    }
}

