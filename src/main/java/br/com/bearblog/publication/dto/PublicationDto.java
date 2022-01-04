package br.com.bearblog.publication.dto;

import br.com.bearblog.publication.model.Publication;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PublicationDto {

    public PublicationDto(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @NotNull
    @Size(min = 10, max = 100, message = "Your title must contain at least 10 characters!")
    private String title;

    @NotNull
    @Size(min = 1, max = 10000, message = "Your title must be between 1 and 250 characters!")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publication pulibcationFactory() {

        Publication publication = new Publication();
        publication.setText(text);
        publication.setTitle(title);
        publication.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        return publication;
    }

}


