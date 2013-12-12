package org.dnaselector.countries;

public class CountryFileReaderException extends Exception {
    public CountryFileReaderException() {
        super();
    }
    public CountryFileReaderException(String message){
        super(message);
    }
    public CountryFileReaderException(Throwable cause){
        super(cause);
    }
    public CountryFileReaderException(String message, Throwable cause){
        super(message, cause);
    }

    public CountryFileReaderException(String countryFilePath, Long lineNumber, String message){
        super("Invalid country line at line " + lineNumber + " for file " + countryFilePath + " : " + message);
    }

    public CountryFileReaderException(String countryFilePath, Long lineNumber, String message, Throwable cause){
        super("Invalid country line at line " + lineNumber + " for file " + countryFilePath + " : " + message, cause);
    }
}
