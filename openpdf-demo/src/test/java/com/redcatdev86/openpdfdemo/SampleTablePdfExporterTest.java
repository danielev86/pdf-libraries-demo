package com.redcatdev86.openpdfdemo;

import com.redcatdev86.openpdfdemo.bean.PersonBean;
import com.redcatdev86.openpdfdemo.service.PdfExporter;
import com.redcatdev86.openpdfdemo.utility.DateUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SampleTablePdfExporterTest {

    @Autowired
    @Qualifier("sampleTablePdfExporter")
    private PdfExporter<List<PersonBean>> pdfExporter;

    @Test
    public void testExportSampleTable() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdfExporter.exportPDF(mockPersons(), out);
        OutputStream outStream = new FileOutputStream("sampleTable.pdf");
        out.writeTo(outStream);
        out.close();
    }

    private List<PersonBean> mockPersons(){
        List<PersonBean> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            PersonBean person = new PersonBean();
            person.setId(Long.valueOf(i));
            person.setFirstName("John" + i);
            person.setLastName("Doe" + i);
            person.setDateOfBirth(DateUtility.randomDate());
            persons.add(person);
        }
        return persons;
    }
}
