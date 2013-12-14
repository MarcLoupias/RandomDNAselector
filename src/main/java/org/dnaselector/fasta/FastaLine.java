package org.dnaselector.fasta;

@SuppressWarnings("UnusedDeclaration")
public class FastaLine {
    private String content;
    private String country;

    public FastaLine(String content, String country) {
        this.content = content;
        this.country = country;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
