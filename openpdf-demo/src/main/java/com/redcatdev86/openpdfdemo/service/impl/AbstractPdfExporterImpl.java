package com.redcatdev86.openpdfdemo.service.impl;

import com.redcatdev86.openpdfdemo.service.PdfExporter;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public abstract class AbstractPdfExporterImpl<T> implements PdfExporter<T> {

    public void exportPDF(T data, ByteArrayOutputStream out) throws Exception {
        log.info("Start create new pdf");
        populateData(data, out);
        log.info("New pdf created! close stream");
    }

    protected abstract void populateData(T data, ByteArrayOutputStream out) throws IOException;
}
