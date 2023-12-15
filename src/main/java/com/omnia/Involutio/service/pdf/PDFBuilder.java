package com.omnia.Involutio.service.pdf;

import com.itextpdf.text.DocumentException;
import com.omnia.Involutio.service.file.FileMaster;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.mock.web.MockMultipartFile;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class PDFBuilder {
    final private FileMaster fileMaster;
    final private TemplateEngine templateEngine;

    public PDFBuilder(FileMaster fileMaster) {
        this.fileMaster = fileMaster;
        this.templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("classpath:/templates/");
        templateEngine.setTemplateResolver(templateResolver);
    }

    //TODO: MentalMagic
    public void createPDF(Long workerId, Context context){
        //TODO: need fix rout
        String html = templateEngine.process("template", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            MultipartFile multipartFile = new MockMultipartFile(workerId.toString() + ".pdf", inputStream);
            fileMaster.create(multipartFile);

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }

}
