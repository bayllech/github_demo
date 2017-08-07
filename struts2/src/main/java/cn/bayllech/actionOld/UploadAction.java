package cn.bayllech.actionOld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadAction extends BaseAction{

    private File[] file;
    private String[] fileFileName;
    private String[] fileContentType;

    private String fileName;
    private String contentType;
    private Long size;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String upload() throws Exception {
        for (int i = 0 ; i < file.length; i++) {
            System.out.println("fileName:" + fileFileName[i]);
            System.out.println("contentType:" + fileContentType[i]);
        }
        return SUCCESS;
    }

    public String download() throws Exception {
        fileName = new String("我的文件".getBytes("UTF-8"), "ISO8859-1");
        size = new File("F:/tempFile/my.txt").length();
        contentType = "text/plain";

        return SUCCESS;
    }

    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(new File("F:/tempFile/my.txt"));
    }

    public File[] getFile() {
        return file;
    }

    public void setFile(File[] file) {
        this.file = file;
    }

    public String[] getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String[] fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String[] getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String[] fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
