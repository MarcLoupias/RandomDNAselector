package org.dnaselector.countries;

@SuppressWarnings("UnusedDeclaration")
public class CountryLineReaderException extends Exception {
    public CountryLineReaderException() {
        super();
    }
    public CountryLineReaderException(String message){
        super(message);
    }
    public CountryLineReaderException(Throwable cause){
        super(cause);
    }
    public CountryLineReaderException(String message, Throwable cause){
        super(message, cause);
    }

    public CountryLineReaderException(String countryFilePath, Integer lineNumber, String message){
        super("Invalid country line at line " + lineNumber + " for file " + countryFilePath + " : " + message);
    }

    public CountryLineReaderException(String countryFilePath, Integer lineNumber, String message, Throwable cause){
        super("Invalid country line at line " + lineNumber + " for file " + countryFilePath + " : " + message, cause);
    }
}
