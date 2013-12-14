package org.dnaselector.countries;

@SuppressWarnings("UnusedDeclaration")
public class CountryLine {
    private String sampleId;
    private String barcodeSequence;
    private String linkerPrimerSequence;
    private String countryArea;
    private String description;
    private String content;

    public CountryLine(
            String sampleId, String barcodeSequence, String linkerPrimerSequence, String countryArea, String description,
            String content
    ) {
        this.sampleId = sampleId;
        this.barcodeSequence = barcodeSequence;
        this.linkerPrimerSequence = linkerPrimerSequence;
        this.countryArea = countryArea;
        this.description = description;
        this.content = content;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getBarcodeSequence() {
        return barcodeSequence;
    }

    public void setBarcodeSequence(String barcodeSequence) {
        this.barcodeSequence = barcodeSequence;
    }

    public String getLinkerPrimerSequence() {
        return linkerPrimerSequence;
    }

    public void setLinkerPrimerSequence(String linkerPrimerSequence) {
        this.linkerPrimerSequence = linkerPrimerSequence;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
