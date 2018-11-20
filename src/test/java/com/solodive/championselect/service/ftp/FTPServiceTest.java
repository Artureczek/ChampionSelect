package com.solodive.championselect.service.ftp;

import com.solodive.championselect.ChampionSelectApp;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
@TestPropertySource(properties = {
    "ftp.server=someServer",
    "ftp.port=8080",
    "ftp.user=someUser",
    "ftp.password=somePassword"
})
public class FTPServiceTest {

    @Mock
    private FTPClient ftpClient;

    @InjectMocks
    private FTPService ftpService;

    private static final String SERVER = "someServer";
    private static final String USER = "someUser";
    private static final String PASSWORD = "somePassword";
    private static final String PORT = "8080";

    @Before
    public void init() {
        ReflectionTestUtils.setField(ftpService, "server", SERVER);
        ReflectionTestUtils.setField(ftpService, "user", USER);
        ReflectionTestUtils.setField(ftpService, "port", PORT);
        ReflectionTestUtils.setField(ftpService, "password", PASSWORD);
    }

    @Test
    public void testSendMultipartHtmlEmail() throws IOException {
        //given
        String path = "somePath";
        String fileName = "someFileName";

        when(ftpClient.getReplyCode()).thenReturn(200);
        when(ftpClient.login(anyString(), anyString())).thenReturn(true);
        when(ftpClient.changeWorkingDirectory(anyString())).thenReturn(true);
        when(ftpClient.retrieveFile(anyString(), any())).thenReturn(true);

        //when
        byte[] actualByteArray = ftpService.retrieveFileFromFTP(path, fileName);

        //then
        System.out.println(actualByteArray.length);
    }
}
