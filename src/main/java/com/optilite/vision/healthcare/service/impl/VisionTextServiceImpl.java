package com.optilite.vision.healthcare.service.impl;

import com.optilite.vision.healthcare.entity.VisionText;
import com.optilite.vision.healthcare.exception.FileParserException;
import com.optilite.vision.healthcare.service.VisionTextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.optilite.vision.healthcare.utils.HealthcareAppConstants.CONTENT_TYPE_APPLICATION_TXT;
import static com.optilite.vision.healthcare.utils.HealthcareAppConstants.EXCEPTION_EMPTY_FILE;
import static com.optilite.vision.healthcare.utils.HealthcareAppConstants.EXCEPTION_UNSUPPORTED_FILE_TYPE;
import static com.optilite.vision.healthcare.utils.HealthcareAppConstants.EXTRACTING_WORDS_FROM_FILE;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisionTextServiceImpl implements VisionTextService {

    private final TextFileParser textFileParser;

    private final TextManager textManager;
    @Override
    public List<String> extractVisionText(MultipartFile multipartFile) {
        if (multipartFile == null) {
            throw new FileParserException(EXCEPTION_EMPTY_FILE);
        }

        return extractFile(multipartFile);
    }

    private List<String> extractFile(MultipartFile multipartFile) throws FileParserException {
        String contentType = multipartFile.getContentType();
        log.info(EXTRACTING_WORDS_FROM_FILE);
        if (multipartFile.isEmpty()) {
            log.debug("File uploaded is not a empty", contentType);
            throw new FileParserException(EXCEPTION_EMPTY_FILE + ":" + multipartFile.getOriginalFilename());
        }
        if (!CONTENT_TYPE_APPLICATION_TXT.equalsIgnoreCase(contentType)) {
            log.debug("File uploaded is not a text file,File is of type {}", contentType);
            throw new FileParserException(EXCEPTION_UNSUPPORTED_FILE_TYPE + ":" + multipartFile.getOriginalFilename());
        }
        List<String> texts = textFileParser.parse(multipartFile);

        List<VisionText> visionTextList = textManager.uploadExtractedTextFromFile(texts);
        return visionTextList.stream().map(x -> x.getText()).collect(Collectors.toList());
    }
}
