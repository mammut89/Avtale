package no.intervju.integrasjonslag.client.fagsystem.model;

import java.time.LocalDateTime;

public class OpprettFagsystemAvtaleRequest {

    private final Long kundeNummer;
    private final AvtaleType avtaleType;
    private final LocalDateTime startDato;
    private final LocalDateTime sluttDato;

    public OpprettFagsystemAvtaleRequest(Long kundeNummer, AvtaleType avtaleType,
                                         LocalDateTime startDato, LocalDateTime sluttDato) {
        this.kundeNummer = kundeNummer;
        this.avtaleType = avtaleType;
        this.startDato = startDato;
        this.sluttDato = sluttDato;
    }

    public Long getKundeNummer() {
        return kundeNummer;
    }

    public AvtaleType getAvtaleType() {
        return avtaleType;
    }

    public LocalDateTime getStartDato() {
        return startDato;
    }

    public LocalDateTime getSluttDato() {
        return sluttDato;
    }
}
