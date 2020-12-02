package com.psk.sortqueries.print;

import com.psk.sortqueries.exception.QueriesException;
import com.psk.sortqueries.type.DynamicQueryType;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Queries {

    private TagReplace tagReplace;
    private createVariable createVariable;

    /*
     * Mybatis(Mysql) 쿼리를 실제 사용하는 쿼리로 정렬해서 출력하는 프로그램
     *
     * <기능>
     * - dynamicQueryConverter
     *   - 동적 쿼리가 있는지 검사한다.
     *   - 동적쿼리 입력 값을 받는다.
     *   - 받은 값으로 동적쿼리를 풀어서 전달해 준다.
     * - variableConverter
     *   - 필요한 변수 입력칸을 만들어서 전달해 준다.
     * - 쿼리를 예쁘게 정렬해준다.
     */

    public String dynamicQueryConverter(String originQuery){
        dynamicQueryValidation(originQuery);
        //TODO 채우기
        return "";
    }

    public String variableConverter(String originQuery) throws ParseException {
        validation(originQuery);

        String result = "";

        result = tagReplace.exe(originQuery);
        result = createVariable.exe(result);

        return result;
    }

    private void dynamicQueryValidation(String originQuery) {
        if (!StringUtils.hasText(originQuery)) {
            throw new NullPointerException();
        }

        if(!DynamicQueryType.isContainDynamicQuery(originQuery)) {
            throw new QueriesException("xml 파일에서 사용되는 동적 쿼리가 포함되어 있지 않습니다.");
        }
    }

    private void validation(String originQuery) {
        if (!StringUtils.hasText(originQuery)) {
            throw new NullPointerException();
        }
    }



}
