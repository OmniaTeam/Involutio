package com.omnia.Involutio.service.pdf;

import com.itextpdf.text.DocumentException;
import com.omnia.Involutio.service.file.FileMaster;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class PDFBuilder {
    final private FileMaster fileMaster;


    public PDFBuilder(FileMaster fileMaster) {
        this.fileMaster = fileMaster;
    }

    //TODO: MentalMagic
    public void createPDF(Long workerId, Model model) {
        try {


            Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
            configuration.setClassForTemplateLoading(PDFBuilder.class, "/templates");

            // Создание экземпляра Model и добавление данных

            // Получение шаблона FreeMarker
            Template template = configuration.getTemplate("template.ftl");

            // Преобразование шаблона в строку
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);


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
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }

    }

}
