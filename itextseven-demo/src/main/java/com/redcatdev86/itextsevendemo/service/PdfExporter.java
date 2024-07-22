package com.redcatdev86.itextsevendemo.service;

public interface PdfExporter<T> {

    void exportPDF(T data, String path) throws Exception;

}
