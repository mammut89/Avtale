package no.intervju.integrasjonslag.client.fagsystem.model;

import no.intervju.integrasjonslag.controller.model.AvtaleStatus;

public class OppdaterAvtaleStatusRequest {

    private final Long avtaleNummer;
    private final AvtaleStatus avtaleStatus;

    public OppdaterAvtaleStatusRequest(Long avtaleNummer, AvtaleStatus avtaleStatus) {
        this.avtaleNummer = avtaleNummer;
        this.avtaleStatus = avtaleStatus;
    }

    public Long getAvtaleNummer() {
        return avtaleNummer;
    }

    public AvtaleStatus getAvtaleStatus() {
        return avtaleStatus;
    }
}