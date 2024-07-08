package com.hedspi.flashcardbe.payload;

import java.time.Instant;
import java.util.List;

public class SetFlcDetail extends SetFlcIntro {
    private Instant createAt;
    private List<FlashcardDTO> flashcards;

    public SetFlcDetail(Integer id, String title, String description, Instant createAt, List<FlashcardDTO> flashcards) {
        super(id, title, description);
        this.createAt = createAt;
        this.flashcards = flashcards;
    }

    public SetFlcDetail(Instant createAt, List<FlashcardDTO> flashcards) {
        this.createAt = createAt;
        this.flashcards = flashcards;
    }

    public SetFlcDetail(){}

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public List<FlashcardDTO> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<FlashcardDTO> flashcards) {
        this.flashcards = flashcards;
    }
}
