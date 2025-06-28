package no.fremtind.intervju.brevtjeneste.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.fremtind.intervju.brevtjeneste.controller.model.SendAvtalebrevRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrevController.class)
class BrevControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void sendAvtalebrev_skalReturnereSendtStatus() throws Exception {
        SendAvtalebrevRequest request = new SendAvtalebrevRequest(123L);

        mockMvc.perform(post("/api/sendAvtale")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("\"SENDT\""));
    }
}
