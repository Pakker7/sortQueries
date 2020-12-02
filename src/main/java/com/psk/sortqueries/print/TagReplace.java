package com.psk.sortqueries.print;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagReplace {

    // TODO if, foeach, when, choose 등의 동적 태그도 추가 필요.......
    public String exe(String query) {
        query = basicTagRemove(query);
        query = cdataTagRemove(query);
        //TODO 동적태그 추가

        return query;
    }

    private String basicTagRemove(String query) {
        return query.replaceAll("(<!--.*?-->)", "") // 주석 삭제
                .replaceAll("&lt;", "<").replaceAll("&gt;", ">") // html escape
                .replaceAll("(((<|</)(select|update|delete|insert|sql))).*?(>)", ""); // 기본 태그 삭제
    }

    private String cdataTagRemove(String query){
        Pattern queryPattern = Pattern.compile("<!\\[CDATA\\[(.*?)\\]\\]>");
        Matcher matcher = queryPattern.matcher(query);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            // 문자열 치환
            String findStr = matcher.group();
            findStr = findStr.substring(findStr.lastIndexOf("[") + 1, findStr.indexOf("]"));

            matcher.appendReplacement(result, findStr);

        }
        matcher.appendTail(result);

        return result.toString();
    }
}
