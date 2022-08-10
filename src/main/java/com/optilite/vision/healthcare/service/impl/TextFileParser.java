package com.optilite.vision.healthcare.service.impl;

import com.optilite.vision.healthcare.exception.FileParserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.optilite.vision.healthcare.utils.HealthcareAppConstants.EXCEPTION_IN_PROCESSING_TEXT_FILE;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextFileParser {

    /**
     * Method to parse the uploaded files and Get the words from the uploaded files
     * @param file of type MultipartFile {@link MultipartFile}
     * @return list of words seperated by whitespace characters
     * @throws FileParserException
     */
    public List<String> parse(MultipartFile file) throws FileParserException {

        try (Stream<String> stream = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)).lines()) {
            return stream.collect(Collectors.toList());
        } catch (Exception ex) {
            log.debug("Parsing of the file failed due to  {}", ex.getMessage());
            throw new FileParserException(EXCEPTION_IN_PROCESSING_TEXT_FILE ,ex);
        }

    }
}
