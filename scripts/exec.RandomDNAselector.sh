#! /bin/bash

# chemin absolu du fichier des countries
sampleFilePath="/home/marco/dev/RandomDNAselector.test.env/map_All-countries.txt"
# chemin absolu du fasta
fastaFilePath="/home/marco/dev/RandomDNAselector.test.env/seqs_All-countries2.fna"
# nombre de mots à sélectionner
wordNumber=500
# chemin absolu du fichier de sortie
outputFilePath="/home/marco/dev/RandomDNAselector.test.env/output2.fna"

java -jar RandomDNAselector-1.0.jar --sampleFilePath=$sampleFilePath --fastaFilePath=$fastaFilePath --wordNumber=$wordNumber --outputFilePath=$outputFilePath 
