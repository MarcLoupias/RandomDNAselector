package org.dnaselector.utils;

import junit.framework.Assert;
import org.dnaselector.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    //@Ignore
    @Test
    public void isLongInListTest() {

        List<Long> idList = new ArrayList<Long>();
        idList.add(1l);
        idList.add(2l);
        idList.add(3l);
        idList.add(4l);
        idList.add(5l);

        boolean res;
        res = Utils.isLongInList(1l, idList);

        Assert.assertTrue(res);

        res = Utils.isLongInList(11l, idList);
        Assert.assertFalse(res);
    }

    @Test
    public void isIntegerInListTest() {

        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        idList.add(4);
        idList.add(5);

        boolean res;
        res = Utils.isIntegerInList(1, idList);

        Assert.assertTrue(res);

        res = Utils.isIntegerInList(11, idList);
        Assert.assertFalse(res);
    }
}
