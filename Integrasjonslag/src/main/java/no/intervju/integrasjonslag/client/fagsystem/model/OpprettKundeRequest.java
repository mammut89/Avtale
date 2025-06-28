package no.intervju.integrasjonslag.client.fagsystem.model;

public class OpprettKundeRequest {
    private final String fnr;
    private final String fornavn;
    private final String etternavn;
    private final String telefonnummer;

    public OpprettKundeRequest(String fnr, String fornavn, String etternavn, String telefonnummer) {
        this.fnr = fnr;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefonnummer = telefonnummer;
    }

    public String getFnr() {
        return fnr;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }
}
