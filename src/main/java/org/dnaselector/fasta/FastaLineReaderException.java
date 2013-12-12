package org.dnaselector.fasta;

public class FastaLineReaderException extends Exception {
    public FastaLineReaderException() {
        super();
    }
    public FastaLineReaderException(String message){
        super(message);
    }
    public FastaLineReaderException(Throwable cause){
        super(cause);
    }
    public FastaLineReaderException(String message, Throwable cause){
        super(message, cause);
    }

    public FastaLineReaderException(String fastaFilePath, Integer lineNumber, String message){
        super("Invalid fasta line at line " + lineNumber + " for file " + fastaFilePath + " : " + message);
    }

    public FastaLineReaderException(String fastaFilePath, Integer lineNumber, String message, Throwable cause){
        super("Invalid fasta line at line " + lineNumber + " for file " + fastaFilePath + " : " + message, cause);
    }

    public FastaLineReaderException(String fastaFilePath, Long lineNumber, String message){
        super("Invalid fasta line at line " + lineNumber + " for file " + fastaFilePath + " : " + message);
    }

    public FastaLineReaderException(String fastaFilePath, Long lineNumber, String message, Throwable cause){
        super("Invalid fasta line at line " + lineNumber + " for file " + fastaFilePath + " : " + message, cause);
    }
}
