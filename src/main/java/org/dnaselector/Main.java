package org.dnaselector;

import org.apache.commons.io.FileUtils;
import org.dnaselector.cli.CliHelper;
import org.dnaselector.cli.SimpleGNUCommandLine;
import org.dnaselector.countries.CountryFileReader;
import org.dnaselector.fasta.FastaFileReader;
import org.dnaselector.utils.RandomNumberUtils;
import org.dnaselector.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        CliHelper.printHeader();

        SimpleGNUCommandLine cli = CliHelper.getMyCLI(args);

        String sampleFilePath = cli.getString("sampleFilePath");
        String fastaFilePath = cli.getString("fastaFilePath");
        String outputFilePath = cli.getString("outputFilePath");
        int wordNumber = cli.getInteger("wordNumber");

        CliHelper.printConfig(sampleFilePath, fastaFilePath, wordNumber, outputFilePath);

        File sampleFile = new File(sampleFilePath);
        File fastaFile = new File(fastaFilePath);

        checkArgs(sampleFilePath, fastaFilePath, outputFilePath, wordNumber, sampleFile, fastaFile);

        List<String> countries = CountryFileReader.getCountryList(sampleFile);
        for(String country : countries){

            /*System.out.write("\r|".getBytes());
            Thread.sleep(2);*/
            //String infoCountry = "\r" + "| working on sampleId " + country + "\n";
            /*String infoCountry = "\r| working on sampleId " + country;
            System.out.write(infoCountry.getBytes());*/
            //System.out.write(infoCountry.getBytes());
            System.out.println("| working on sampleId " + country);
            Thread.sleep(100);

            List<Integer> lineIdToGet = new ArrayList<Integer>();
            List<Integer> linesIdForCountry = FastaFileReader.getLinesIdForCountry(fastaFile, country);
            if(linesIdForCountry.size() == 0){
                System.out.println("| no lines for that sampleId in input fasta file");
                continue;
            }

            System.out.println("| start time : " + Utils.dateToString(new Date()));

            Integer nbLineForCountry = FastaFileReader.countLinesForCountry(fastaFile, country);
            int tmpWordNumber = wordNumber;
            if(nbLineForCountry < wordNumber) {
                tmpWordNumber = nbLineForCountry;
            }

            for(int i = 1; i <= tmpWordNumber; i++){

                Integer val = linesIdForCountry.get(
                        RandomNumberUtils.generateInteger(0, (linesIdForCountry.size() - 1))
                );

                while(Utils.isIntegerInList(val, lineIdToGet)){
                    val = linesIdForCountry.get(
                            RandomNumberUtils.generateInteger(0, (linesIdForCountry.size() - 1))
                    );
                }

                lineIdToGet.add(val);

                System.out.write("\r|".getBytes());
                Thread.sleep(2);
                String infoWordNumber = "\r" + "| working on sampleId " + country + ", words generated " + i +
                        " for " + nbLineForCountry + " lines to process";
                System.out.write(infoWordNumber.getBytes());
                Thread.sleep(2);
            }

            System.out.write("\n".getBytes());
            System.out.println("| end time : " + Utils.dateToString(new Date()));
            System.out.println("|");

            System.out.print("| extracting data ...");
            List<String> resLines = FastaFileReader.getLinesByIdList(fastaFile, lineIdToGet);
            StringBuilder sb = new StringBuilder();
            for(String s : resLines){
                sb.append(s);
            }
            System.out.println(" done");

            File fOutput = new File(outputFilePath);
            System.out.print("| writing output file ...");
            FileUtils.writeStringToFile(fOutput, sb.toString(), true);
            System.out.println(" done");
            System.out.println("|");
        }

        CliHelper.printOkFooter();
    }

    private static void checkArgs(
            String sampleFilePath, String fastaFilePath, String outputFilePath, int wordNumber,
            File sampleFile, File fastaFile
    ) {
        boolean settingsError = false;

        StringBuilder sb = new StringBuilder();

        if(wordNumber < 1){
            sb.append("ERROR : Given wordNumber ").append(wordNumber).append(" is invalid ! Need a positive value !\n");
            settingsError = true;
        }
        if(!sampleFile.exists()) {
            sb.append("ERROR : Given sampleFilePath ").append(sampleFilePath).append(" does not exist !\n");
            settingsError = true;
        }
        if(!fastaFile.exists()) {
            sb.append("ERROR : Given fastaFilePath ").append(fastaFilePath).append(" does not exist !\n");
            settingsError = true;
        }
        if(!Utils.isValidFileName(outputFilePath)){
            sb.append("ERROR : Given outputFilePath ").append(outputFilePath).append(" is not a valid file name !\n");
            settingsError = true;
        }
        if(settingsError){
            sb.append("Program arguments invalid, will now exit.");
            CliHelper.printErrorFooter(sb.toString());
            System.exit(-1);
        }
    }
}
