package com.hedspi.flashcardbe.repository;

import com.hedspi.flashcardbe.entity.Flashcard;
import com.hedspi.flashcardbe.entity.SetFlc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {
    List<Flashcard> findBySetFlc(SetFlc setFlc);
}