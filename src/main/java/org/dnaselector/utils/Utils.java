package org.dnaselector.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

    public static boolean isValidFileName(final String aFileName) {
        final File aFile = new File(aFileName);
        boolean isValid = true;
        try {
            if (aFile.createNewFile()) {
                aFile.delete();
            }
        } catch (IOException e) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isIntegerInList(Integer id, List<Integer> idList) {
        for(Integer val : idList){
            if(val == id.intValue()){
                return true;
            }
        }

        return false;
    }

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        return df.format(date);
    }
}
