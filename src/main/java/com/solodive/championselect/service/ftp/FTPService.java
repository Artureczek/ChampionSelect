package com.solodive.championselect.service.ftp;

import com.solodive.championselect.service.ftp.exception.FTPConnectionFailureException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FTPService {

    @Autowired
    private FTPClient ftpClient;

    @Value("ftp.server")
    private String server;

    @Value("ftp.port")
    private int port;

    @Value("ftp.user")
    private String user;

    @Value("ftp.password")
    private String password;


    private void openFTPConnection(FTPClient ftpClient) throws IOException {

            ftpClient.connect(server, port);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new FTPConnectionFailureException("Could not connect to ftp service", e)
            }
    }
}
