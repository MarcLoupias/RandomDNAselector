package org.dnaselector.cli;

public class CliHelper {
    public static SimpleGNUCommandLine getMyCLI(String[] args) {
        SimpleGNUCommandLine cli = new SimpleGNUCommandLine(args);
        cli.addOption("sampleFilePath", "file path for the sample file", true, true);
        cli.addOption("fastaFilePath", "file path for the fasta file", true, true);
        cli.addOption("wordNumber", "word number from fasta file to put in output file", true, true);
        cli.addOption("outputFilePath", "file path for the output file", true, true);
        return cli;
    }

    public static void printHeader() {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|             RandomDNAselector 1.0-SNAPSHOT              |");
        System.out.println("+---------------------------------------------------------+");
    }

    public static void printConfig(String sampleFilePath, String fastaFilePath, int wordNumber, String outputFilePath){
        System.out.println("|                    configuration                        |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| sampleFilePath = " + sampleFilePath);
        System.out.println("| fastaFilePath = " + fastaFilePath);
        System.out.println("| wordNumber = " + wordNumber);
        System.out.println("| outputFilePath = " + outputFilePath);
        System.out.println("+---------------------------------------------------------+");
    }

    public static void printErrorFooter(String content) {
        System.out.println("");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| " + content);
        System.out.println("+---------------------------------------------------------+");
    }

    public static void printOkFooter() {
        System.out.println("");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| Done - bugs & comments at pro@marc-loupias.fr - © 2013  |");
        System.out.println("+---------------------------------------------------------+");
    }
}
