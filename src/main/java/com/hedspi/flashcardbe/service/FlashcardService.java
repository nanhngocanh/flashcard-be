package com.hedspi.flashcardbe.service;

import com.hedspi.flashcardbe.entity.Flashcard;
import com.hedspi.flashcardbe.entity.SetFlc;
import com.hedspi.flashcardbe.payload.FlashcardDTO;
import com.hedspi.flashcardbe.payload.Message;
import com.hedspi.flashcardbe.payload.SetFlcDetail;
import com.hedspi.flashcardbe.payload.SetFlcIntro;
import com.hedspi.flashcardbe.repository.FlashcardRepository;
import com.hedspi.flashcardbe.repository.SetFlcRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlashcardService {
    private final SetFlcRepository setFlcRepository;
    private final FlashcardRepository flashcardRepository;
    private final ModelMapper modelMapper;

    public List<SetFlcIntro> getSetList(String userId){
        return setFlcRepository.getSetFlcByUserId(userId);
    }

    public SetFlcDetail getSetDetail(Integer setId, String userId) {
        SetFlc setFlc = setFlcRepository.findByIdAndUserId(setId, userId).orElseThrow();
        List<Flashcard> flashcards = flashcardRepository.findBySetFlc(setFlc);
        return new SetFlcDetail(setId, setFlc.getTitle(), setFlc.getDescription(), setFlc.getCreateAt(), flashcards.stream().map(element -> modelMapper.map(element, FlashcardDTO.class)).collect(Collectors.toList()));
    }

    public List<SetFlcIntro> deleteSetFlc(Integer setId, String userId) {
        SetFlc setFlc = setFlcRepository.findByIdAndUserId(setId, userId).orElseThrow();
        setFlcRepository.delete(setFlc);
        return setFlcRepository.getSetFlcByUserId(userId);
    }

    public SetFlcDetail createSetFlc(SetFlcDetail request, String userId) {
        SetFlc newSetFlc = setFlcRepository.save(new SetFlc(userId, request.getTitle(), request.getDescription()));
        List<Flashcard> reqFlashcards = request.getFlashcards().stream().map(element -> {
            Flashcard flc = modelMapper.map(element, Flashcard.class);
            flc.setSetFlc(newSetFlc);
            return flc;
        }).collect(Collectors.toList());
        List<Flashcard> newFlashcards = flashcardRepository.saveAll(reqFlashcards);
        List<FlashcardDTO> newFlashcardDTOS = newFlashcards.stream().map(element -> modelMapper.map(element, FlashcardDTO.class)).collect(Collectors.toList());
        return new SetFlcDetail(newSetFlc.getId(), newSetFlc.getTitle(), newSetFlc.getDescription(), newSetFlc.getCreateAt(),newFlashcardDTOS);
    }

    @Transactional
    public SetFlcDetail updateSetFlc(SetFlcDetail request, Integer setId, String userId) {
        SetFlc oldSetFlc = setFlcRepository.findByIdAndUserId(setId,userId).orElseThrow();
//        List<Flashcard> oldFlashcards = flashcardRepository.findBySetFlc(oldSetFlc);

        oldSetFlc.setTitle(request.getTitle());
        oldSetFlc.setDescription(request.getDescription());
        SetFlc newSetFlc = setFlcRepository.save(oldSetFlc);

//        flashcardRepository.deleteAll(oldFlashcards);
        List<Flashcard> newFlashcards = flashcardRepository.saveAll(request.getFlashcards().stream().map(element -> {
            Flashcard flc = modelMapper.map(element, Flashcard.class);
            flc.setSetFlc(newSetFlc);
            return flc;
        }).collect(Collectors.toList()));
        return new SetFlcDetail(newSetFlc.getId(), newSetFlc.getTitle(), newSetFlc.getDescription(), newSetFlc.getCreateAt(), newFlashcards.stream().map(element -> modelMapper.map(element, FlashcardDTO.class)).collect(Collectors.toList()));
    }

}
