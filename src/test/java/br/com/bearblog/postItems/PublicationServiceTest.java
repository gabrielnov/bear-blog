package br.com.bearblog.postItems;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.bearblog.postItems.dto.PublicationDto;
import br.com.bearblog.postItems.model.Publication;
import br.com.bearblog.postItems.service.PublicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PublicationServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PublicationService publicationService;

    String title = "This is just a test for a title";
    String text = "And this is just a test for a text";

    @Test
    void shouldCreateNewPublication() throws Exception {
        PublicationDto publicationDto = new PublicationDto(title, text);

        mockMvc.perform(post("/api/news")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(publicationDto)))
                .andExpect(status().isOk());


        Optional<Publication> publication = publicationService.findByTitleContaining("This is just");
        Assertions.assertEquals(title, publication.get().getTitle());
    }

    @Test
    void shouldReturnIdAfterCreation() throws Exception {
        PublicationDto publicationDto = new PublicationDto(title, text);

        ResultActions resultActions = mockMvc.perform(post("/api/news")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(publicationDto)))
                .andExpect(status().isOk());

        Optional<Publication> publication = publicationService.findByTitleContaining("This is just");
        Assertions.assertEquals(title, publication.get().getTitle());
    }
}
