package org.dnaselector.fasta;

public class FastaLineReader {
    public static FastaLine readLine(String fastaFilePath, Integer lineNumber, String lineContent) throws FastaLineReaderException {

        if(lineContent == null) {
            throw new FastaLineReaderException(fastaFilePath, lineNumber, "Line content is null");
        }
        if(lineContent.isEmpty()) {
            throw new FastaLineReaderException(fastaFilePath, lineNumber, "Line content is empty");
        }

        if(!lineContent.startsWith(">")) {
            throw new FastaLineReaderException(
                    fastaFilePath, lineNumber, "Line first char is not > but " + lineContent.substring(0, 1)
            );
        }

        String[] lineStrings = lineContent.split(" ");
        if(lineStrings == null) {
            throw new FastaLineReaderException(
                    fastaFilePath, lineNumber, "Line split result with space separator is null"
            );
        }
        if(lineStrings.length != 5) {
            throw new FastaLineReaderException(
                    fastaFilePath, lineNumber, "Column number should be 5 instead of " + lineStrings.length
            );
        }

        String[] subLineStrings = lineStrings[0].split("_");
        if(subLineStrings == null) {
            throw new FastaLineReaderException(
                    fastaFilePath, lineNumber, "Sub-line split result with underscore separator is null"
            );
        }
        if(subLineStrings.length != 2) {
            throw new FastaLineReaderException(
                    fastaFilePath, lineNumber, "Sub-line split length should be 2 instead of " + subLineStrings.length
            );
        }

        String country = subLineStrings[0].substring(1);
        if(country == null) {
            throw new FastaLineReaderException(fastaFilePath, lineNumber, "Country name is null");
        }
        if(country.isEmpty()) {
            throw new FastaLineReaderException(fastaFilePath, lineNumber, "Country name is empty");
        }

        return new FastaLine(lineContent, country);
    }
}
