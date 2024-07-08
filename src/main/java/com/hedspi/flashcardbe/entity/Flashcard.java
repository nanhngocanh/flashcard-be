package com.hedspi.flashcardbe.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "flashcard")
@EntityListeners(AuditingEntityListener.class)
public class Flashcard {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "set_flc_id")
    private SetFlc setFlc;

    @Column(name = "term")
    private String term;

    @Column(name = "definition")
    private String definition;

    @Column(name = "completed")
    private Boolean completed;

    public Flashcard() {
    }

    public Flashcard(Integer id, SetFlc setFlc, String term, String definition, Boolean completed) {
        this.id = id;
        this.setFlc = setFlc;
        this.term = term;
        this.definition = definition;
        this.completed = completed;
    }

    public Flashcard(SetFlc setFlc, String term, String definition, Boolean completed) {
        this.setFlc = setFlc;
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

    public SetFlc getSetFlc() {
        return setFlc;
    }

    public void setSetFlc(SetFlc setFlc) {
        this.setFlc = setFlc;
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
