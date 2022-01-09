package br.com.bearblog.publication.dto;

import br.com.bearblog.publication.model.Publication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
public class PublicationDto {

    @NotNull
    @Size(min = 10, max = 100, message = "Your title must contain at least 10 characters!")
    private String title;

    @NotNull
    @Size(min = 1, max = 10000, message = "Your title must be between 1 and 250 characters!")
    private String text;

    public Publication pulibcationFactory() {

        Publication publication = new Publication();
        publication.setText(this.text);
        publication.setTitle(this.title);
        publication.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        return publication;
    }

}


