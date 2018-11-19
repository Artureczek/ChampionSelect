package com.solodive.championselect.web.rest.ftp;

import com.solodive.championselect.service.ftp.FTPService;
import com.solodive.championselect.service.ftp.exception.FTPConnectionFailureException;
import com.solodive.championselect.web.rest.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/files/ftp/{path}/{fileName}")
    public ResponseEntity<byte[]> getSingleFileFromFTP(@PathVariable String path, @PathVariable String fileName) {
        log.info("Received request for file by name: " + fileName);
        String pathWithSlashes = UrlUtil.changeUnderscoresToSlashes(path);
        byte[] fileFromFTP;
        try {
            fileFromFTP = ftpService.retrieveFileFromFTP(pathWithSlashes, fileName);
            log.info("Successfully retrieved file from FTP");
            return new ResponseEntity<>(fileFromFTP, new HttpHeaders(), HttpStatus.OK);
        } catch (FTPConnectionFailureException e) {
            log.error("Unable to retrieve champion image. Original error {}", e);
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        } catch(IOException e) {
            log.error("Unable to connect to ftp service. Original error {}", e);
            return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            log.error("Unknown error: {}", e);
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
