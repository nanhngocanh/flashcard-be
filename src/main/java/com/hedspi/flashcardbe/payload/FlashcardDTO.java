package com.hedspi.flashcardbe.payload;

import com.hedspi.flashcardbe.entity.Flashcard;

public class FlashcardDTO {
    private Integer id;
    private String term;
    private String definition;
    private Boolean completed;

    public FlashcardDTO() {
    }

    public FlashcardDTO(Integer id, String term, String definition, Boolean completed) {
        this.id = id;
        this.term = term;
        this.definition = definition;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
