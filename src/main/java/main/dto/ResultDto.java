package main.dto;

/**
 * PackALunch
 * Created by Sadra on 2/4/16.
 */
public class ResultDto {

    String documentId;
    String documentText;
    String documentWeight;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    public String getDocumentWeight() {
        return documentWeight;
    }

    public void setDocumentWeight(String documentWeight) {
        this.documentWeight = documentWeight;
    }
}
