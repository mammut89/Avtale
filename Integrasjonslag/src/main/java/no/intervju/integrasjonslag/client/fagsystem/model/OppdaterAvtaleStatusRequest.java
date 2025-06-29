package no.intervju.integrasjonslag.client.fagsystem.model;

import no.intervju.integrasjonslag.controller.model.AvtaleStatus;

public record OppdaterAvtaleStatusRequest(Long avtaleNummer, AvtaleStatus avtaleStatus) {}