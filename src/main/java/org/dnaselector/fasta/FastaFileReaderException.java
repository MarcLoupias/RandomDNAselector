package org.dnaselector.fasta;

@SuppressWarnings("UnusedDeclaration")
public class FastaFileReaderException extends Exception {
    public FastaFileReaderException() {
        super();
    }
    public FastaFileReaderException(String message){
        super(message);
    }
    public FastaFileReaderException(Throwable cause){
        super(cause);
    }
    public FastaFileReaderException(String message, Throwable cause){
        super(message, cause);
    }

    public FastaFileReaderException(String fastaFilePath, Integer lineNumber, String message){
        super("Invalid fasta line at line " + lineNumber + " for file " + fastaFilePath + " : " + message);
    }

    public FastaFileReaderException(String fastaFilePath, Integer lineNumber, String message, Throwable cause){
        super("Invalid fasta line at line " + lineNumber + " for file " + fastaFilePath + " : " + message, cause);
    }
}
