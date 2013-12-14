package org.dnaselector.countries;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountryFileReader {

    public static List<String> getCountryList(File input) throws IOException, CountryLineReaderException {

        FileInputStream stream = new FileInputStream(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String strLine;
        Integer fileLineNumber = 0;
        List<String> countryList = new ArrayList<String>();

        try {
            while ((strLine = br.readLine()) != null)   {

                fileLineNumber++;

                CountryLine line = CountryLineReader.readLine(input.getAbsolutePath(), fileLineNumber, strLine);
                if(line != null) {
                    countryList.add(line.getSampleId());
                }

            }
        } catch (IOException e) {
            throw new IOException(e);
        } catch (CountryLineReaderException e) {
            throw new CountryLineReaderException(input.getAbsolutePath(), fileLineNumber, "line parsing error", e);
        } finally {
            IOUtils.closeQuietly(br);
        }

        return countryList;
    }
}
