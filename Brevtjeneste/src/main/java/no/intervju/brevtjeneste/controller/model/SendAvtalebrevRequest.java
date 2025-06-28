package no.intervju.brevtjeneste.controller.model;

public class SendAvtalebrevRequest {

    private final Long avtaleId;

    public SendAvtalebrevRequest(Long avtaleId) {
        this.avtaleId = avtaleId;
    }

    public Long getAvtaleId() {
        return avtaleId;
    }
}
