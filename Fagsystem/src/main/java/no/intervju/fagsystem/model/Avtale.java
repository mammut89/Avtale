package no.intervju.fagsystem.model;

import jakarta.persistence.*;
import no.intervju.fagsystem.controller.model.AvtaleStatus;

import java.time.LocalDateTime;

@Entity
public class Avtale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long kundeId;
    private LocalDateTime startDato;
    private LocalDateTime sluttDato;
    private String registreringsnummer;

    @Enumerated(EnumType.STRING)
    private AvtaleStatus avtaleStatus;

    public Long getId() {
        return id;
    }

    public Long getKundeId() {
        return kundeId;
    }

    public void setKundeId(Long kunde_id) {
        this.kundeId = kunde_id;
    }

    public AvtaleStatus getAvtaleStatus() {
        return avtaleStatus;
    }
    public void setAvtaleStatus(AvtaleStatus status) {
        this.avtaleStatus = status;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRegistreringsnummer() {
        return registreringsnummer;
    }

    public void setRegistreringsnummer(String registreringsnummer) {
        this.registreringsnummer = registreringsnummer;
    }
}