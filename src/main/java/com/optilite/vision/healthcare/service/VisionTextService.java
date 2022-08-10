package com.optilite.vision.healthcare.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VisionTextService {

    /***
     * Method the extract  the texts form the MultipartFile and upload return the list of files
     * @param multipartFile of type MultiPartFile {@link MultipartFile}
     * @return List of Texts
     */
    List<String> extractVisionText(MultipartFile multipartFile);
}
