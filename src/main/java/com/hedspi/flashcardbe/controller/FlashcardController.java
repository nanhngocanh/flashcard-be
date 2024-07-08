package com.hedspi.flashcardbe.controller;

import com.hedspi.flashcardbe.payload.Message;
import com.hedspi.flashcardbe.payload.SetFlcDetail;
import com.hedspi.flashcardbe.payload.SetFlcIntro;
import com.hedspi.flashcardbe.service.FlashcardService;
import com.hedspi.flashcardbe.utils.BasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/flashcard")
public class FlashcardController {

    private final FlashcardService service;

    //test ok
    @GetMapping()
    public ResponseEntity<List<SetFlcIntro>> getSetFlcList(Authentication authentication){
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        return ResponseEntity.ok(service.getSetList(userId));
    }

    //test ok
    @GetMapping("/{id}")
    public ResponseEntity<SetFlcDetail> getSetDetail(@PathVariable Integer id, Authentication authentication){
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        return ResponseEntity.ok(service.getSetDetail(id, userId));
    }

    //test ok
    @DeleteMapping("/{id}")
    public ResponseEntity<List<SetFlcIntro>> deleteSetFlc(@PathVariable Integer id, Authentication authentication) {
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        return ResponseEntity.ok(service.deleteSetFlc(id, userId));
    }

    //test ok
    @PostMapping()
    public ResponseEntity<SetFlcDetail> createSetFlc(@RequestBody SetFlcDetail request, Authentication authentication) {
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        return ResponseEntity.ok(service.createSetFlc(request, userId));
    }

    //test ok
    @PutMapping("/{id}")
    public ResponseEntity<SetFlcDetail> updateSetFlc(@RequestBody SetFlcDetail request, @PathVariable Integer id, Authentication authentication) {
        String userId = BasicInfo.getUserIdByAuthentication(authentication);
        return ResponseEntity.ok(service.updateSetFlc(request ,id, userId));
    }

}
