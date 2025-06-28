package no.fremtind.intervju.fagsystem.controller.model;

public class OpprettKundeRequest {
    private String fnr;
    private String fornavn;
    private String etternavn;
    private String telefonnummer;

    public OpprettKundeRequest(String fnr, String fornavn, String etternavn, String telefonnummer) {
        this.fnr = fnr;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefonnummer = telefonnummer;
    }

    public String getFnr() {
        return fnr;
    }

    public void setFnr(String fnr) {
        this.fnr = fnr;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
