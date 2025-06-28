package no.fremtind.intervju.fagsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.fremtind.intervju.fagsystem.controller.model.OppdaterAvtaleStatusRequest;
import no.fremtind.intervju.fagsystem.controller.model.OpprettAvtaleRequest;
import no.fremtind.intervju.fagsystem.controller.model.OpprettKundeRequest;
import no.fremtind.intervju.fagsystem.service.FagsystemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import  org. springframework. test. context. bean. override. mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static no.fremtind.intervju.fagsystem.controller.model.AvtaleStatus.SENDT;
import static no.fremtind.intervju.fagsystem.controller.model.AvtaleType.FORSIKRING;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FagsystemController.class)
class FagsystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FagsystemService fagsystemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void opprettKunde_ok() throws Exception {
        OpprettKundeRequest request = new OpprettKundeRequest("12345678901", "Ola", "Nordmann", "99999999");
        Mockito.when(fagsystemService.hentKundenummer(any())).thenReturn("1");

        mockMvc.perform(post("/api/kunde")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void opprettAvtale_ok() throws Exception {
        OpprettAvtaleRequest request = new OpprettAvtaleRequest(1L, FORSIKRING, LocalDateTime.now(), LocalDateTime.now().plusMonths(12));
        Mockito.when(fagsystemService.hentAvtalenummer(any())).thenReturn(1L);

        mockMvc.perform(post("/api/avtale")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    void oppdaterAvtaleStatus_ok() throws Exception {
        OppdaterAvtaleStatusRequest request = new OppdaterAvtaleStatusRequest(1L, SENDT);
        Mockito.when(fagsystemService.hentAvtaleStatus(1L)).thenReturn(SENDT);

        mockMvc.perform(post("/api/oppdaterAvtaleStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(SENDT)));
    }
}
