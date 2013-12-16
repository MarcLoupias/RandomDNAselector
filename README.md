RandomDNAselector
=================

A command line java app to mine fasta file for a student project (not mine :p)
Compiled for JRE 1.6
Built with Maven3
IDE IntelliJ 12

Input
-----

* A sample file (csv format, line separator is \n, cell separator is \t, first column is country names)
* A fasta file containing the words (fasta format, a fasta record is composed of 2 mains parts separated by \n)
* An integer representing the number of word to take in fasta file

Algorithm
---------

For each country in the sample file, takes randomly wordNumber fasta records and put them into an output file.
In the input fasta file, each record contains a field structured by sampleId + "_" + "id". Check is done only on sampleId.

Output
------

The randomly taken fasta records from input fasta file.
The output file is created if not exist.
If output file exists, records are added to the end of the existing file.

Usage
-----

Rename RandomDNAselector-1.1-SNAPSHOT-jar-with-dependencies.jar in RandomDNAselector-1.0.jar for conveniance then :


    java -jar RandomDNAselector-1.0.jar --sampleFilePath=<sampleFilePath> --fastaFilePath=<fastaFilePath> --wordNumber=<wordNumber> --outputFilePath=<outputFilePath>






