package org.dnaselector.utils;

import junit.framework.Assert;
import org.dnaselector.utils.RandomNumberUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberUtilsTest {

    private void internalGenerateLong() {
        Long res = RandomNumberUtils.generateLong(1l, 10l);
        Assert.assertNotNull(res);
        Assert.assertTrue((res >= 1l && res <= 10l));
    }
    @Test
    public void generateLongTest() {
        for(int i = 1; i < 10000000; i++){
            internalGenerateLong();
        }
    }

    private void internalGenerateLongWithExcludedValues() {
        List<Long> excludedValuesList = new ArrayList<Long>();
        excludedValuesList.add(3l);
        excludedValuesList.add(33l);
        excludedValuesList.add(45l);
        excludedValuesList.add(77l);
        Long res = RandomNumberUtils.generateLong(1l, 100l, excludedValuesList);

        Assert.assertNotNull(res);
        Assert.assertTrue((res >= 1l && res <= 100l));
        Assert.assertNotSame("res=" + res, 3l, res);
        Assert.assertNotSame("res=" + res, 33l, res);
        Assert.assertNotSame("res=" + res, 45l, res);
        Assert.assertNotSame("res=" + res, 77l, res);
    }

    @Test
    public void generateLongWithExcludedValuesTest() {
        for(int i = 1; i < 10000000; i++){
            internalGenerateLongWithExcludedValues();
        }
    }

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
