package no.intervju.integrasjonslag.client.brevtjeneste.model;

public class SendAvtalebrevRequest {

    public SendAvtalebrevRequest(Long avtaleId) {
        this.avtaleId = avtaleId;
    }

    private Long avtaleId;

    public Long getAvtaleId() {
        return avtaleId;
    }

    public void setAvtaleId(Long avtaleId) {
        this.avtaleId = avtaleId;
    }
}
