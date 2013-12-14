package org.dnaselector.utils;

import junit.framework.Assert;
import org.dnaselector.utils.RandomNumberUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberUtilsTest {

    private void internalGenerateInteger() {
        Integer res = RandomNumberUtils.generateInteger(1, 10);
        Assert.assertNotNull(res);
        Assert.assertTrue((res >= 1 && res <= 10));
    }
    @Test
    public void generateIntegerTest() {
        for(int i = 1; i < 10000000; i++){
            internalGenerateInteger();
        }
    }

    private void internalGenerateIntegerWithExcludedValues() {
        List<Integer> excludedValuesList = new ArrayList<Integer>();
        excludedValuesList.add(3);
        excludedValuesList.add(33);
        excludedValuesList.add(45);
        excludedValuesList.add(77);
        Integer res = RandomNumberUtils.generateInteger(1, 100, excludedValuesList);

        Assert.assertNotNull(res);
        Assert.assertTrue((res >= 1 && res <= 100));
        Assert.assertNotSame("res=" + res, 3, res);
        Assert.assertNotSame("res=" + res, 33, res);
        Assert.assertNotSame("res=" + res, 45, res);
        Assert.assertNotSame("res=" + res, 77, res);
    }

    @Test
    public void generateIntegerWithExcludedValuesTest() {
        for(int i = 1; i < 10000000; i++){
            internalGenerateIntegerWithExcludedValues();
        }
    }
}
