package com.solodive.championselect.service.ftp;

import com.solodive.championselect.service.ftp.exception.FTPConnectionFailureException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public byte[] getChampionPicture(String championName) throws IOException {
        openFTPConnection();
        byte[] downloadedFile = downloadFile(championName);
        closeFTPConnection();
        return downloadedFile;
    }

    private void openFTPConnection() throws IOException {
        log.debug("Trying to establish ftp connection");
        ftpClient.connect(server, Integer.valueOf(port));
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            throw new FTPConnectionFailureException("Could not connect to ftp service");
        }
        ftpClient.login(user, password);
        log.debug("Succesfully conencted to ftp server");
    }

    private void closeFTPConnection() throws IOException {
        log.debug("Closing ftp connection");
        ftpClient.disconnect();
    }

    private byte[] downloadFile(String source) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ftpClient.retrieveFile(source, out);
            log.debug("Successfully retrieved file from FTP");
            return out.toByteArray();
        } catch (IOException e) {
            log.error("Unable to retrieve file from FTP");
            throw new FTPConnectionFailureException(e);
        }
    }
}
