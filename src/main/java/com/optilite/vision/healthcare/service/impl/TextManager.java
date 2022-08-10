package com.optilite.vision.healthcare.service.impl;

import com.optilite.vision.healthcare.entity.VisionText;
import com.optilite.vision.healthcare.service.repository.VisionTextRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TextManager {

    private final VisionTextRepository visionTextRepository;

    @Transactional
    public List<VisionText> uploadExtractedTextFromFile(List<String> visionTexts) {
      List<VisionText> visionTextList =  visionTexts.stream().map(text ->
                VisionText.builder().text(text).build()).collect(Collectors.toList());
      return visionTextRepository.saveAll(visionTextList);
    }
}
