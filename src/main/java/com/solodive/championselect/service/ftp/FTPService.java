package com.solodive.championselect.service.ftp;

import com.solodive.championselect.service.ftp.exception.FTPConnectionFailureException;
import com.solodive.championselect.service.ftp.exception.FTPFileSaveUnsuccessfulException;
import com.solodive.championselect.service.ftp.exception.FTPRequestNotFoundException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class FTPService {

    private final Logger log = LoggerFactory.getLogger(FTPService.class);

    @Autowired
    private FTPClient ftpClient;

    @Value("${ftp.server}")
    private String server;

    @Value("${ftp.port}")
    private String port;

    @Value("${ftp.user}")
    private String user;

    @Value("${ftp.password}")
    private String password;

    public byte[] retrieveFileFromFTP(String path, String fileName) throws IOException {
        openFTPConnection();
        byte[] downloadedFile = downloadFile(path, fileName);
        closeFTPConnection();
        return downloadedFile;
    }

    public void saveFileToFTP(byte[] fileToSave, String path, String fileName) throws IOException {
        openFTPConnection();
        saveFile(fileToSave, path, fileName);
        closeFTPConnection();
    }


    private void saveFile(byte[] fileToSave, String path, String fileName) throws IOException {
        try (ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileToSave)) {

            log.debug("Trying to save file for path and name: {}:{}", path, fileName);
            boolean directoryChangeSuccessful = ftpClient.changeWorkingDirectory(path);
            if (!directoryChangeSuccessful) {
                throw new FTPFileSaveUnsuccessfulException("Could not change ftp directory for given path: " + path);
            }
            boolean fileSaveSuccessful = ftpClient.storeFile(fileName, fileInputStream);

            if (!fileSaveSuccessful) {
                throw new FTPFileSaveUnsuccessfulException("Could not save file to ftp server for given name: " + fileName);
            }

        } catch (IOException | FTPFileSaveUnsuccessfulException e) {
            closeFTPConnection();
            throw e;
        }
    }


    private byte[] downloadFile(String path, String fileName) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            log.debug("Trying to retrieve file for path and name: {}:{}", path, fileName);
            boolean directoryChangeSuccessful = ftpClient.changeWorkingDirectory(path);
            if (!directoryChangeSuccessful) {
                throw new FTPRequestNotFoundException("Could not change ftp directory for given path: " + path);
            }

            boolean fileRetrievalSuccessful = ftpClient.retrieveFile(fileName, out);
            if (!fileRetrievalSuccessful) {
                throw new FTPRequestNotFoundException("Could not find file: " + fileName);
            }
            return out.toByteArray();

        } catch (IOException | FTPRequestNotFoundException e) {
            closeFTPConnection();
            throw e;
        }
    }

    private void openFTPConnection() throws IOException {
        log.debug("Trying to establish ftp connection: {}:{}", server, port);
        ftpClient.connect(server, Integer.valueOf(port));
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            closeFTPConnection();
            throw new FTPConnectionFailureException("Could not connect to ftp service");
        }

        log.debug("Trying to login to ftp service");
        boolean loginSuccessful = ftpClient.login(user, password);
        if (!loginSuccessful) {
            closeFTPConnection();
            throw new FTPConnectionFailureException("Could not connect to ftp service. Check credentials in configuration!");
        }

        log.debug("Successfully connected and logged in to ftp server");
    }

    private void closeFTPConnection() throws IOException {
        log.debug("Closing ftp connection");
        ftpClient.disconnect();
    }
}
