package org.dnaselector.countries;

public class CountryLineReader {
    public static CountryLine readLine(String countryFilePath, Integer lineNumber, String lineContent)
            throws CountryLineReaderException {

        if(lineContent == null) {
            throw new CountryLineReaderException(countryFilePath, lineNumber, "Line content is null");
        }
        if(lineContent.isEmpty()) {
            throw new CountryLineReaderException(countryFilePath, lineNumber, "Line content is empty");
        }

        if(lineContent.startsWith("#")) {
            return null;
        }

        String[] lineStrings = lineContent.split("\t");
        if(lineStrings == null) {
            throw new CountryLineReaderException(
                    countryFilePath, lineNumber, "Line split result with tab separator is null"
            );
        }
        /*if(lineStrings.length != 6) { // warning, there is 2 tabs for barcodesequence column
            throw new CountryLineReaderException(
                    countryFilePath, lineNumber, "Column number should be 6 instead of " + lineStrings.length
            );
        }*/

        if(lineStrings[0] == null || lineStrings[0].isEmpty()){
            throw new CountryLineReaderException(countryFilePath, lineNumber, "Country column is mandatory");
        }

        //return new CountryLine(lineStrings[0], lineStrings[1], lineStrings[3], lineStrings[4], lineStrings[5], lineContent);
        return new CountryLine(lineStrings[0], "", "", "", "", lineContent);
    }
}
