package org.dnaselector.fasta;

import org.apache.commons.io.IOUtils;
import org.dnaselector.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FastaFileReader {

    public static void read(File input) throws IOException {

        FileInputStream stream = new FileInputStream(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String strLine;

        try {
            while ((strLine = br.readLine()) != null)   {

                System.out.println (strLine);
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            IOUtils.closeQuietly(br);
        }
    }

    public static Long countFastaLines(File input) throws IOException {
        FileInputStream stream = new FileInputStream(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String strLine;
        Long count = 0l;
        Long lineNumber = 0l;

        try {
            while ((strLine = br.readLine()) != null)   {

                lineNumber++;
                if( (lineNumber % 2) != 0){
                    count++;
                }
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            IOUtils.closeQuietly(br);
        }

        return count;
    }

    public static Integer countLinesForCountry(File input, String country) throws IOException, FastaFileReaderException {

        if(country == null || country.isEmpty()) {
            throw new FastaFileReaderException("country is null or empty");
        }

        FileInputStream stream = new FileInputStream(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String strLine;
        Integer countRes = 0;
        Long fileLineNumber = 0l;
        Integer fastaLineNumber= 0;

        try {
            while ((strLine = br.readLine()) != null)   {

                fileLineNumber++;

                if( (fileLineNumber % 2) != 0 ) {
                    fastaLineNumber++;
                    fileLineNumber++;

                    StringBuilder sb = new StringBuilder();
                    sb.append(strLine);
                    sb.append(IOUtils.LINE_SEPARATOR);

                    strLine = br.readLine();
                    if(strLine == null) {
                        throw new FastaFileReaderException(
                                input.getAbsolutePath(), fastaLineNumber, "incomplete line, second part is missing"
                        );
                    } else {
                        sb.append(strLine);
                        sb.append(IOUtils.LINE_SEPARATOR);

                        FastaLine fl = FastaLineReader.readLine(input.getAbsolutePath(), fastaLineNumber, sb.toString());

                        if(country.equals(fl.getCountry())){
                            countRes++;
                        }
                    }
                }

            }
        } catch (IOException e) {
            throw new IOException(e);
        } catch (FastaLineReaderException e) {
            throw new FastaFileReaderException(input.getAbsolutePath(), fastaLineNumber, "line parsing error", e);
        } finally {
            IOUtils.closeQuietly(br);
        }

        return countRes;
    }

    public static List<Integer> getLinesIdForCountry(File input, String country) throws IOException, FastaFileReaderException {

        if(country == null || country.isEmpty()) {
            throw new FastaFileReaderException("country is null or empty");
        }

        FileInputStream stream = new FileInputStream(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String strLine;
        List<Integer> lineIdList = new ArrayList<Integer>();
        Long fileLineNumber = 0l;
        Integer fastaLineNumber= 0;

        try {
            while ((strLine = br.readLine()) != null)   {

                fileLineNumber++;

                if( (fileLineNumber % 2) != 0 ) {
                    fastaLineNumber++;
                    fileLineNumber++;

                    StringBuilder sb = new StringBuilder();
                    sb.append(strLine);
                    sb.append(IOUtils.LINE_SEPARATOR);

                    strLine = br.readLine();
                    if(strLine == null) {
                        throw new FastaFileReaderException(
                                input.getAbsolutePath(), fastaLineNumber, "incomplete line, second part is missing"
                        );
                    } else {
                        sb.append(strLine);
                        sb.append(IOUtils.LINE_SEPARATOR);

                        FastaLine fl = FastaLineReader.readLine(input.getAbsolutePath(), fastaLineNumber, sb.toString());

                        if(country.equals(fl.getCountry())){
                            lineIdList.add(fastaLineNumber);
                        }
                    }
                }

            }
        } catch (IOException e) {
            throw new IOException(e);
        } catch (FastaLineReaderException e) {
            throw new FastaFileReaderException(input.getAbsolutePath(), fastaLineNumber, "line parsing error", e);
        } finally {
            IOUtils.closeQuietly(br);
        }

        return lineIdList;
    }

    public static List<String> getLinesByIdList(File input, List<Integer> idList) throws IOException, FastaFileReaderException {

        FileInputStream stream = new FileInputStream(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String strLine;

        List<String> res = new ArrayList<String>();
        Long fileLineNumber = 0l;
        Integer fastaLineNumber = 0;

        try {
            while ((strLine = br.readLine()) != null)   {

                fileLineNumber++;

                if( (fileLineNumber % 2) != 0 ) {
                    fastaLineNumber++;
                    fileLineNumber++;

                    StringBuilder sb = new StringBuilder();
                    sb.append(strLine);
                    sb.append(IOUtils.LINE_SEPARATOR);

                    strLine = br.readLine();
                    if(strLine == null) {
                        throw new FastaFileReaderException(
                                input.getAbsolutePath(), fastaLineNumber, "incomplete line, second part is missing"
                        );
                    } else {
                        if(Utils.isIntegerInList(fastaLineNumber, idList)){
                            sb.append(strLine);
                            sb.append(IOUtils.LINE_SEPARATOR);
                            res.add(sb.toString());
                        }
                    }
                }

            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            IOUtils.closeQuietly(br);
        }

        return res;
    }
}
