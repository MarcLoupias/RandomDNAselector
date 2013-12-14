package integration;

import org.dnaselector.utils.ExceptionUtils;
import org.dnaselector.fasta.FastaFileReader;
import org.dnaselector.fasta.FastaFileReaderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FastaFileReaderTest {

    @SuppressWarnings("FieldCanBeLocal")
    private String testFilesDirPath;
    private String exampleFastaFilePath;

    @Before
    public void setUp() throws Exception {

        testFilesDirPath = System.getProperty("user.dir");
        testFilesDirPath += File.separator + "src" + File.separator + "test" + File.separator + "files";

        exampleFastaFilePath = testFilesDirPath + File.separator + "fasta.example.fna";
    }

    @Test
    public void getCurrentPathTest() {
        String s = System.getProperty("user.dir");
        Assert.assertNotNull(s);
    }

    @Ignore
    @Test
    public void readTest() {

        try {
            FastaFileReader.read(new File(exampleFastaFilePath));
        } catch (IOException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        }
    }

    //@Ignore
    @Test
    public void countLinesInFastaFileTest() {

        File fFasta = new File(exampleFastaFilePath);

        Integer lineCount = 0;
        try {
            lineCount = FastaFileReader.countFastaLines(fFasta);
        } catch (IOException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        }

        Assert.assertTrue(lineCount > 0);
        Assert.assertEquals(50, lineCount.longValue());
    }

    //@Ignore
    @Test
    public void countLinesForCountry_WN2011_InFastaFileTest() {

        File fFasta = new File(exampleFastaFilePath);

        Integer lineCount = 0;
        try {
            lineCount = FastaFileReader.countLinesForCountry(fFasta, "WN.2011");
        } catch (IOException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        } catch (FastaFileReaderException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        }

        Assert.assertTrue(lineCount > 0);
        Assert.assertNotSame(100, lineCount);
    }

    //@Ignore
    @Test
    public void getLinesIdForCountryTest() {
        File fFasta = new File(exampleFastaFilePath);

        List<Integer> lineIdList = null;
        try {
            lineIdList = FastaFileReader.getLinesIdForCountry(fFasta, "WN.2011");
        } catch (IOException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        } catch (FastaFileReaderException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        }

        Assert.assertNotNull(lineIdList);
        Assert.assertTrue(lineIdList.size() > 0);
        Assert.assertEquals(2, lineIdList.get(0).longValue());
        Assert.assertEquals(16, lineIdList.get(1).longValue());
        Assert.assertEquals(31, lineIdList.get(2).longValue());
    }

    //@Ignore
    @Test
    public void getLinesByIdTest() {
        File fFasta = new File(exampleFastaFilePath);

        List<Integer> idList = new ArrayList<Integer>();
        idList.add(3);
        idList.add(5);

        List<String> res = null;
        try {
            res = FastaFileReader.getLinesByIdList(fFasta, idList);
        } catch (IOException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        } catch (FastaFileReaderException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        }

        Assert.assertNotNull(res);
        Assert.assertEquals(2, res.size());

        String line1 = ">WN.2012_81866 H70DYUJ01COKW4 orig_bc=GACTACTCGCGTCGTTGCTTAGA new_bc=GACTACTCGCGTCGTTGCTTAGA bc_diffs=0\n" +
                "GTTTGATCATGGCTCAGAGCGAACGCTGGCGGCATGCTTAACACATGCAAGTTGCACGGACTTTTCGGAGTTAGTGGCGGACGGGTGAGTAACGCGTAGGAACCTGACCATGGGTGGGGGATAACACTGGGAAACTGGTGCTAATACCGCATGATACCTGAGGGTTAAAGGCTTTTGTTGCCTGTGGAGGGGCCTGCGTTTGATTAGCTAGTTGGTTGGGTAAAGGCTGACCAAGGCGATGATCGATAGCTGGTTTGAGAGGATGATCAGCCACACTGGGACTGAGACACGGCCCAGACTCCTACGGGAGGCAGCAGTGGGGAATATTGGACAATGGGGGCAACCCTGATCCAGCAATGCCGCGTGTGTGAAGAAGGTTTTCGGATTGTAAAGCACTTTCGTTGGGGTAAGATGATGACGGTACCCCAAAGAAGAAGCCCCGGCTAACTTCGTGCCAGCAGCCGCACGTAATACACGACGGGGAGTGTCGGCGTCTCTCAAGGCACACGGAGGGG\n";
        Assert.assertEquals(line1, res.get(0));

        String line2 = ">WN.2012_81868 H70DYUJ01B25RR orig_bc=GACTACTCGCGTCGTTGCTTAGA new_bc=GACTACTCGCGTCGTTGCTTAGA bc_diffs=0\n" +
                "GTTTGATCGTGGCTCAGGACGAACGTTGGCGGCGTGCCTAATACATGCAAGTCGAACGAGGCTGCCCAGTTGCTAGTCGGTGCTTGCACTGACGAACAATTGGATCCAGCCGAGTGGCGAACTGGTGAGTAACACGTGGGTAACCTGCCCAGCAGAAGGGGGATAACACCTGGAAACAGATGCTAATACCGTATAAACCTGAAAACCGCCTGGTCTTCAGCTAAAAGATGGTGTACGCTATCGCTGCTGGATGGACCCGCGGCGTATTAGCTAGTTGGCGAGATAATAGCTCACCAAGGCGATGATACGTAGCAGACCTGAGAGGGTAATCTGCCACAATGGGACTGAGACACGGCCCATACTCCTACGGGGAGGCAGCAGTAGGGAATCTTCCACAATGGACGAAAGTCTGATGGAGCAACGCCGCGTGAGTGAAGAAGGGTTTCGGCTCGTAAAACTCTGTTGTTAAGAGAAGAACGATCGTAAGAGTAACTGCTTACGG\n";
        Assert.assertEquals(line2, res.get(1));

    }
}
