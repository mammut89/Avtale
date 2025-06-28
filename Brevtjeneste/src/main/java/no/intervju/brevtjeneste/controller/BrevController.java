package no.intervju.brevtjeneste.controller;

import no.intervju.brevtjeneste.controller.model.BrevStatus;
import no.intervju.brevtjeneste.controller.model.SendAvtalebrevRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class BrevController {

    //TODO logg noe...
    @RequestMapping(method = RequestMethod.POST, value = "/sendAvtale")
    public ResponseEntity<BrevStatus> sendAvtalebrev(@RequestBody SendAvtalebrevRequest request) {
        return ResponseEntity.ok((BrevStatus.SENDT));
    }
}

