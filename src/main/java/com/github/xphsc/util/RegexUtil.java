package com.github.xphsc.util;



import com.github.xphsc.exception.UtilException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${huipei.x} on 2017/6/20
 */
public class RegexUtil {


    static
    public boolean isMatched(String pattern, String reg) {
        Pattern compile = Pattern.compile(reg);
        return compile.matcher(pattern).matches();
    }

    static
    public List<String> match(String pattern, String reg, int group)
            throws UtilException {

        List<String> matchGroups = new ArrayList<>();
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(pattern);
        if (group > matcher.groupCount() || group < 0)
            throw new UtilException("Illegal match group :" + group);
        while (matcher.find()) {
            matchGroups.add(matcher.group(group));
        }
        return matchGroups;
    }


    static public String match(String pattern, String reg) {

        String match = null;
        try {
            List<String> matchs = match(pattern, reg, 0);
            if (null != matchs && matchs.size() > 0) {
                match = matchs.get(0);
            }
        } catch (UtilException e) {
            e.printStackTrace();
        }
        return match;
    }

    public static String converNumByReg(String number){
        Pattern compile = Pattern.compile("^(\\d+)(\\.0*)?$");
        Matcher matcher = compile.matcher(number);
        while (matcher.find()){
            number = matcher.group(1);
        }
        return number;
    }
}
