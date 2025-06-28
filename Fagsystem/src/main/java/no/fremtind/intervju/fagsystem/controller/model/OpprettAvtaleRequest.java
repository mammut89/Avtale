package no.fremtind.intervju.fagsystem.controller.model;

import java.time.LocalDateTime;

public class OpprettAvtaleRequest {

    private Long kundeNummer;
    private AvtaleType avtaleType;
    private LocalDateTime startDato;
    private LocalDateTime sluttDato;

    public OpprettAvtaleRequest(Long kundeNummer, AvtaleType avtaleType, LocalDateTime startDato, LocalDateTime sluttDato) {
        this.kundeNummer = kundeNummer;
        this.avtaleType = avtaleType;
        this.startDato = startDato;
        this.sluttDato = sluttDato;
    }

    public Long getKundeNummer() {
        return kundeNummer;
    }

    public void setKundeNummer(Long kundeNummer) {
        this.kundeNummer = kundeNummer;
    }

    public AvtaleType getAvtaleType() {
        return avtaleType;
    }

    public void setAvtaleType(AvtaleType avtaleType) {
        this.avtaleType = avtaleType;
    }

    public LocalDateTime getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDateTime startDato) {
        this.startDato = startDato;
    }

    public LocalDateTime getSluttDato() {
        return sluttDato;
    }

    public void setSluttDato(LocalDateTime sluttDato) {
        this.sluttDato = sluttDato;
    }
}
