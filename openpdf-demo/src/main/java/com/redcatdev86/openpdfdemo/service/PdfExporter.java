package com.redcatdev86.openpdfdemo.service;

import java.io.ByteArrayOutputStream;

public interface PdfExporter<T> {

    void exportPDF(T data, ByteArrayOutputStream out) throws Exception;

}
