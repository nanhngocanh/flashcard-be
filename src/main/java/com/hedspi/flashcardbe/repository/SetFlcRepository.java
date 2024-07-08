package com.hedspi.flashcardbe.repository;

import com.hedspi.flashcardbe.entity.SetFlc;
import com.hedspi.flashcardbe.payload.SetFlcIntro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SetFlcRepository extends JpaRepository<SetFlc, Integer> {
    Optional<SetFlc> findByIdAndUserId(Integer id, String userId);
    @Query("select new com.hedspi.flashcardbe.payload.SetFlcIntro(s.id, s.title, s.description) from SetFlc s where s.userId=:userId")
    List<SetFlcIntro> getSetFlcByUserId(String userId);

}