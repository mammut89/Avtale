package no.intervju.fagsystem.controller.model;

import java.time.LocalDateTime;

public record OpprettAvtaleRequest(Long kundeNummer, LocalDateTime startDato, LocalDateTime sluttDato,
                                   String registreringsnummer) {}
