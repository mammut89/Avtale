package no.intervju.integrasjonslag.client.fagsystem.model;

import java.time.LocalDateTime;

public record OpprettFagsystemAvtaleRequest(Long kundeNummer, AvtaleType avtaleType, LocalDateTime startDato, LocalDateTime sluttDato, String registreringsnummer) {}
