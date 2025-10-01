package com.s2p;

import com.s2p.controller.PdfController;
import com.s2p.util.PdfReaderUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PdfControllerTest {

    private final PdfController pdfController = new PdfController();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pdfController).build();

    @Test
    void testUploadPdfAndExtractText() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "sample.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "fake pdf content".getBytes(StandardCharsets.UTF_8)
        );

        try (MockedStatic<PdfReaderUtil> utilities = mockStatic(PdfReaderUtil.class)) {
            utilities.when(() -> PdfReaderUtil.extractText("uploads/sample.pdf"))
                    .thenReturn("Name: John Doe\nRoll: 123");

            mockMvc.perform(multipart("/pdf/upload").file(mockFile))
                    .andExpect(status().isOk())
                    .andExpect(content().string("File uploaded and data extracted successfully!"));
        }
    }
}
