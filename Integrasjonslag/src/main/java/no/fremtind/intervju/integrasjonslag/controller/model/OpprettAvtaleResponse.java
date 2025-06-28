package no.fremtind.intervju.integrasjonslag.controller.model;

public class OpprettAvtaleResponse {
    private Long avtalenummer;
    private final AvtaleStatus status;

    public OpprettAvtaleResponse(Long avtalenummer, AvtaleStatus status) {
        this.avtalenummer = avtalenummer;
        this.status = status;
    }

    public AvtaleStatus getStatus() {
        return status;
    }

    public void setStatus(AvtaleStatus status) {}

    public Long getAvtalenummer() {
        return avtalenummer;
    }

    public void setAvtalenummer(Long avtalenummer) {
        this.avtalenummer = avtalenummer;
    }
}

