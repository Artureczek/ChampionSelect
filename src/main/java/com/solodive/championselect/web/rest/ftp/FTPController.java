package com.solodive.championselect.web.rest.ftp;

import com.solodive.championselect.service.ftp.FTPService;
import com.solodive.championselect.service.ftp.exception.FTPConnectionFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FTPController {

    private final Logger log = LoggerFactory.getLogger(FTPController.class);

    @Autowired
    private FTPService ftpService;


    @GetMapping("/images/champion/{name}")
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String name) {
        log.info("Received request for champion image for name: " + name);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        byte[] media;
        try {
            media = ftpService.getChampionPicture(name);
            log.info("Successfully retrieved champion image");
            return new ResponseEntity<>(media, headers, HttpStatus.OK);
        } catch (IOException | FTPConnectionFailureException e) {
            log.error("Unable to retrieve champion image. Original error {}", e);
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unknown error: {}", e);
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
