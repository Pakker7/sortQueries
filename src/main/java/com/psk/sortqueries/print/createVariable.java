package com.psk.sortqueries.print;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class createVariable {

    public String exe(String originQuery) {
        Pattern queryPattern = Pattern.compile("[\\#$]\\{.*?\\}");
        Matcher matcher = queryPattern.matcher(originQuery);

        StringBuffer result = new StringBuffer();
        StringBuffer var = new StringBuffer();

        while (matcher.find()) {
            // 문자열 치환
            String findStr = matcher.group();
            findStr = "@" + findStr.substring(2, findStr.length() - 1);

            matcher.appendReplacement(result, findStr);

            // 변수 모음
            if (!var.toString().contains(findStr)) {
                var.append("set " + findStr + " = ; \r\n");
            }

        }
        matcher.appendTail(result);

        return printResult(var, result).toString();

    }

    private StringBuffer printResult(StringBuffer var, StringBuffer result) {
        String separator = "/***************variable***************/ \r\n";

        if (var.length() > 0) {
            result.insert(0, separator + var.toString() + separator);
        }

        return result;
    }
}
