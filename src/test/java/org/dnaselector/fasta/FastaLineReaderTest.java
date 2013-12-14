package org.dnaselector.fasta;

import org.dnaselector.utils.ExceptionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FastaLineReaderTest {

    private String validLine;

    @Before
    public void setUp() throws Exception {

        validLine = ">CH.2012_81864 H70DYUJ01DGL4K orig_bc=GACTACTCGCGTCGTCCGATAGA new_bc=GACTACTCGCGTCGTCCGATAGA bc_diffs=0\n" +
                "GTTTGATCGTGGCTCAGAGCGAACGCTGGCGGCATGCTTAACACATGCAAGTTGCACGGACTTTTCGGAGTTAGTGGCGGACGGGTGAGTAACGCGTAGGAACCTGACCATGGGTGGGGGATAACACTGGGAAACTGGTGCTAATACCGCATGATACCTGAGGGTTAAAGGCTTTTGTTGCCTGTGGAGGGGCCTGCGTTTGATTAGCTAGTTGGTTGGGTAAAGGCTGACCAAGGCGATGATCGATAGCTGGTTTGAGAGGATGATCAGCCACACTGGGACTGAGACACGGCCCAGACTCCTACGGGAGGCAGCAGTGGGGAATATTGGACAATGGGGGCAACCCTGATCCAGCAATGCCGCGTGTGTGAAGAAGGTTTTCGGATTGTAAAAGCACTTTCGTTGGGGAAGATGATGACGGTACCCAAAGAAGAAGCCCCGGCTAACTTCGTGCCAGCAGCCGCAGTAAAACACGACGGGGATGGTCGGCGTCTCTCAAGGCACACAGGGGGATAGG\n";
    }

    @Test
    public void readFastaLine_OK_Test() {

        FastaLine res = null;
        try {
            res = FastaLineReader.readLine("testPath", 51, validLine);
        } catch (FastaLineReaderException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        }

        Assert.assertNotNull(res);
        Assert.assertEquals("CH.2012", res.getCountry());
    }

    @Test
    public void readFastaLine_contentIsNull_Test() {

        FastaLine res = null;
        try {
            res = FastaLineReader.readLine("testPath", 51, null);
        } catch (FastaLineReaderException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Invalid fasta line at line 51 for file testPath : Line content is null", e.getMessage());
        }

        Assert.assertNull(res);
    }

    @Test
    public void readFastaLine_contentIsEmpty_Test() {

        String invalidLine = "";
        FastaLine res = null;
        try {
            res = FastaLineReader.readLine("testPath", 51, invalidLine);
        } catch (FastaLineReaderException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Invalid fasta line at line 51 for file testPath : Line content is empty", e.getMessage());
        }

        Assert.assertNull(res);
    }

    @Test
    public void readFastaLine_wrongLineFirstChar_Test() {

        String invalidLine = "CH.2012_81864 H70DYUJ01DGL4K orig_bc=GACTACTCGCGTCGTCCGATAGA new_bc=GACTACTCGCGTCGTCCGATAGA bc_diffs=0\n" +
                "GTTTGATCGTGGCTCAGAGCGAACGCTGGCGGCATGCTTAACACATGCAAGTTGCACGGACTTTTCGGAGTTAGTGGCGGACGGGTGAGTAACGCGTAGGAACCTGACCATGGGTGGGGGATAACACTGGGAAACTGGTGCTAATACCGCATGATACCTGAGGGTTAAAGGCTTTTGTTGCCTGTGGAGGGGCCTGCGTTTGATTAGCTAGTTGGTTGGGTAAAGGCTGACCAAGGCGATGATCGATAGCTGGTTTGAGAGGATGATCAGCCACACTGGGACTGAGACACGGCCCAGACTCCTACGGGAGGCAGCAGTGGGGAATATTGGACAATGGGGGCAACCCTGATCCAGCAATGCCGCGTGTGTGAAGAAGGTTTTCGGATTGTAAAAGCACTTTCGTTGGGGAAGATGATGACGGTACCCAAAGAAGAAGCCCCGGCTAACTTCGTGCCAGCAGCCGCAGTAAAACACGACGGGGATGGTCGGCGTCTCTCAAGGCACACAGGGGGATAGG\n";
        FastaLine res = null;
        try {
            res = FastaLineReader.readLine("testPath", 51, invalidLine);
        } catch (FastaLineReaderException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Invalid fasta line at line 51 for file testPath : Line first char is not > but C", e.getMessage());
        }

        Assert.assertNull(res);
    }

    @Test
    public void readFastaLine_wrongLineColumnNumber_Test() {

        String invalidLine = ">CH.2012_81864    H70DYUJ01DGL4K orig_bc=GACTACTCGCGTCGTCCGATAGA new_bc=GACTACTCGCGTCGTCCGATAGA bc_diffs=0\n" +
                "GTTTGATCGTGGCTCAGAGCGAACGCTGGCGGCATGCTTAACACATGCAAGTTGCACGGACTTTTCGGAGTTAGTGGCGGACGGGTGAGTAACGCGTAGGAACCTGACCATGGGTGGGGGATAACACTGGGAAACTGGTGCTAATACCGCATGATACCTGAGGGTTAAAGGCTTTTGTTGCCTGTGGAGGGGCCTGCGTTTGATTAGCTAGTTGGTTGGGTAAAGGCTGACCAAGGCGATGATCGATAGCTGGTTTGAGAGGATGATCAGCCACACTGGGACTGAGACACGGCCCAGACTCCTACGGGAGGCAGCAGTGGGGAATATTGGACAATGGGGGCAACCCTGATCCAGCAATGCCGCGTGTGTGAAGAAGGTTTTCGGATTGTAAAAGCACTTTCGTTGGGGAAGATGATGACGGTACCCAAAGAAGAAGCCCCGGCTAACTTCGTGCCAGCAGCCGCAGTAAAACACGACGGGGATGGTCGGCGTCTCTCAAGGCACACAGGGGGATAGG\n";
        FastaLine res = null;
        try {
            res = FastaLineReader.readLine("testPath", 51, invalidLine);
        } catch (FastaLineReaderException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Invalid fasta line at line 51 for file testPath : Column number should be 5 instead of 8", e.getMessage());
        }

        Assert.assertNull(res);
    }

    @Test
    public void readFastaLine_wrongSubLineColumnNumber_Test() {

        String invalidLine = ">CH.2012____81864 H70DYUJ01DGL4K orig_bc=GACTACTCGCGTCGTCCGATAGA new_bc=GACTACTCGCGTCGTCCGATAGA bc_diffs=0\n" +
                "GTTTGATCGTGGCTCAGAGCGAACGCTGGCGGCATGCTTAACACATGCAAGTTGCACGGACTTTTCGGAGTTAGTGGCGGACGGGTGAGTAACGCGTAGGAACCTGACCATGGGTGGGGGATAACACTGGGAAACTGGTGCTAATACCGCATGATACCTGAGGGTTAAAGGCTTTTGTTGCCTGTGGAGGGGCCTGCGTTTGATTAGCTAGTTGGTTGGGTAAAGGCTGACCAAGGCGATGATCGATAGCTGGTTTGAGAGGATGATCAGCCACACTGGGACTGAGACACGGCCCAGACTCCTACGGGAGGCAGCAGTGGGGAATATTGGACAATGGGGGCAACCCTGATCCAGCAATGCCGCGTGTGTGAAGAAGGTTTTCGGATTGTAAAAGCACTTTCGTTGGGGAAGATGATGACGGTACCCAAAGAAGAAGCCCCGGCTAACTTCGTGCCAGCAGCCGCAGTAAAACACGACGGGGATGGTCGGCGTCTCTCAAGGCACACAGGGGGATAGG\n";
        FastaLine res = null;
        try {
            res = FastaLineReader.readLine("testPath", 51, invalidLine);
        } catch (FastaLineReaderException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Invalid fasta line at line 51 for file testPath : Sub-line split length should be 2 instead of 5", e.getMessage());
        }

        Assert.assertNull(res);
    }

    @Test
    public void readFastaLine_emptyCountryName_Test() {

        String invalidLine = ">_81864 H70DYUJ01DGL4K orig_bc=GACTACTCGCGTCGTCCGATAGA new_bc=GACTACTCGCGTCGTCCGATAGA bc_diffs=0\n" +
                "GTTTGATCGTGGCTCAGAGCGAACGCTGGCGGCATGCTTAACACATGCAAGTTGCACGGACTTTTCGGAGTTAGTGGCGGACGGGTGAGTAACGCGTAGGAACCTGACCATGGGTGGGGGATAACACTGGGAAACTGGTGCTAATACCGCATGATACCTGAGGGTTAAAGGCTTTTGTTGCCTGTGGAGGGGCCTGCGTTTGATTAGCTAGTTGGTTGGGTAAAGGCTGACCAAGGCGATGATCGATAGCTGGTTTGAGAGGATGATCAGCCACACTGGGACTGAGACACGGCCCAGACTCCTACGGGAGGCAGCAGTGGGGAATATTGGACAATGGGGGCAACCCTGATCCAGCAATGCCGCGTGTGTGAAGAAGGTTTTCGGATTGTAAAAGCACTTTCGTTGGGGAAGATGATGACGGTACCCAAAGAAGAAGCCCCGGCTAACTTCGTGCCAGCAGCCGCAGTAAAACACGACGGGGATGGTCGGCGTCTCTCAAGGCACACAGGGGGATAGG\n";
        FastaLine res = null;
        try {
            res = FastaLineReader.readLine("testPath", 51, invalidLine);
        } catch (FastaLineReaderException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Invalid fasta line at line 51 for file testPath : Country name is empty", e.getMessage());
        }

        Assert.assertNull(res);
    }
}
