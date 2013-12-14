package integration;

import org.dnaselector.utils.ExceptionUtils;
import org.dnaselector.countries.CountryFileReader;
import org.dnaselector.countries.CountryLineReaderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CountryFileReaderTest {

    @SuppressWarnings("FieldCanBeLocal")
    private String testFilesDirPath;
    private String exampleCountriesFilePath;

    @Before
    public void setUp() throws Exception {

        testFilesDirPath = System.getProperty("user.dir");
        testFilesDirPath += File.separator + "src" + File.separator + "test" + File.separator + "files";

        exampleCountriesFilePath = testFilesDirPath + File.separator + "map_All-countries.txt";
    }

    //@Ignore
    @Test
    public void getCountryListInCountryFileTest() {

        File fCountries = new File(exampleCountriesFilePath);

        List<String> res = null;
        try {
            res = CountryFileReader.getCountryList(fCountries);
        } catch (IOException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        } catch (CountryLineReaderException e) {
            Assert.fail(ExceptionUtils.throwableToString(e));
        }

        Assert.assertNotNull(res);

        Assert.assertEquals("Arg.BA.2002", res.get(0));
        Assert.assertEquals("Arg.Cor.1999", res.get(1));
        Assert.assertEquals("Arg.ER.1999", res.get(2));
        Assert.assertEquals("Arg.Miss.1999", res.get(3));
        Assert.assertEquals("USA.Cal.2011", res.get(4));
        Assert.assertEquals("USA.Ha.2002", res.get(5));


    }
}
