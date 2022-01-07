package br.com.bearblog.publication.service;

import br.com.bearblog.publication.repository.PublicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.bearblog.publication.dto.PublicationDto;
import br.com.bearblog.publication.model.Publication;
import br.com.bearblog.publication.service.PublicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private PublicationRepository publicationRepository;

    String title,  text = "this is just a test";

    @Test
    void shouldReturnListOfPublications() throws Exception {
        title = "Finding multiple publications test";

        PublicationDto pub1 = new PublicationDto(title, text);
        PublicationDto pub2 = new PublicationDto(title, text);

        List publications = new ArrayList<PublicationDto>();
        publications.add(pub1);
        publications.add(pub2);

        publications.forEach(pub -> {
            try {
                mockMvc.perform(post("/api/publication")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(pub)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<Publication> list = publicationService.findAll();
        Assertions.assertEquals(publicationRepository.findAll().size(), list.size());
    }

    @Test
    void shouldCreateNewPublication() throws Exception {
        title = "Creating publication test";
        PublicationDto publicationDto = new PublicationDto(title, text);

        mockMvc.perform(post("/api/publication")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(publicationDto)))
                .andExpect(status().isOk());


        Optional<Publication> publication = publicationService.findByTitleContaining("Creating");
        Assertions.assertEquals(title, publication.get().getTitle());
    }

    @Test
    void shouldReturnIdAfterCreation() throws Exception {
        title = "Finding one publication test";
        PublicationDto publicationDto = new PublicationDto(title, text);

        mockMvc.perform(post("/api/publication")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(publicationDto)));

        Optional<Publication> publication = publicationService.findByTitleContaining("Finding one");
        Assertions.assertEquals(title, publication.get().getTitle());
    }

    @Test
    void shouldSavePublication(){
        title = "Saving publication test";
        PublicationDto publicationDto = new PublicationDto(title, text);

        Publication publication = publicationService.savePublication(publicationDto);
        Assertions.assertEquals(title, publication.getTitle());
        Assertions.assertEquals(text, publication.getText());
        Assertions.assertEquals(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), publication.getCreatedAt());
    }
}
