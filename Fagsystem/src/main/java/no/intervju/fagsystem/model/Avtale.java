package no.intervju.fagsystem.model;

import jakarta.persistence.*;
import no.intervju.fagsystem.controller.model.AvtaleStatus;

@Entity
public class Avtale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long kundeId;

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
}