package com.psk.sortqueries.type;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum DynamicQueryType {
    MAPPER("mapper", "<mapper>", false, "시작시 명명"),
    PARAMETERMAP("parameterMap", "<parameterMap>", false, "parameter랑 비슷한데 비권장"),
    PARAMETER("parameter", "<parameter>", false, "넘겨오는 파라미터의 행태를 지정"),
    RESULTMAP("resultMap", "<resultMap>", false, "결과 타입을 지정"),
    RESULTTYPE("resultType", "<resultType>", false, "결과 타입을 지정"),
    CONSTRUCTOR("constructor", "<constructor>", false, "모르겠"),
    RESULT("result", "<result>", false, "모르겠음"),
    IDARG("idArg", "<idArg>", false, "모름"),
    ARG("arg", "<arg>", false, "모름"),
    COLLCTION("collection", "<collection>", false, "모름"),
    ASSOCIATION("association", "<association>", false, "모름"),
    DISCRIMINATOR("discriminator", "<discriminator>", false, "모름"),
    CASE("case", "<case>", false, "모름"),
    PROPERTY("property", "<property>", false, "xml 설정시에 사용"),
    TYPEALIAS("typeAlias", "<typeAlias>", false, "xml 설정시에 사용"),
    SELECTKEY("selectKey", "<selectKey>", true, "사전에 어떤 키값을 가져와서 증가시켜서 입력하거나 혹은 입력후에 증가된 키값을 가져올"),
    ID("id", "<id>", true, "<sql>이나, 쿼리에 지정하는 id값"),
    SQL("sql", "<sql>", true, "쿼리 블록을 만든다."),
    INCLUDE("include", "<include>", true, "<sql>로 선언한 쿼리를 포함 시킨다."),
    BIND("bind", "<bind>", true, "변수를 생성한다"),
    TRIM("trim", "<trim>", true, "조건에 따라 where절을 추가 할때 사용한다."),
    WHERE("where", "<where>", true, "조건에 따라 where절을 추가 할때 사용한다."),
    SET("set", "<set>", true, "동적으로 update 구문을 만들때 사용한다."),
    FOREACH("foreach", "<foreach>", true, "배열 타입의 파라미터를 받을 때 사용한다"),
    CHOOSE("choose", "<choose>", true, "Oracle의 case 문과 비슷하다"),
    WHEN("when", "<when>", true, "Oracle의 case 문과 비슷하다"),
    OTHERWISEotherwise("otherwise", "<otherwise>", true, "Oracle의 case 문과 비슷하다"),
    IF("if", "<if>", true, "일반 개발 언어의 if문이랑 같음");

    private String name;
    private String tag;
    private boolean isNeedForConversion; //false인 것은 다 삭제한다.
    private String explanation;

    private static Map<String, DynamicQueryType> cashMap = new HashMap<>();
    private static Stream<DynamicQueryType> cashStream = Arrays.stream(DynamicQueryType.values());

    static {
        for (DynamicQueryType type : DynamicQueryType.values()) {
            cashMap.put(type.name, type);
        }
    }

    DynamicQueryType(String name, String tag, boolean isNeedForConversion, String explanation) {
        this.name = name;
        this.tag = tag;
        this.isNeedForConversion = isNeedForConversion;
        this.explanation = explanation;
    }

    public List<DynamicQueryType> getNeedConversion(boolean isNeedForConversion) {
        return cashStream.filter(i -> {
            if (isNeedForConversion) {
                return i.isNeedForConversion();
            }
            return !i.isNeedForConversion();
        }).collect(Collectors.toList());
    }

    public static boolean isContainDynamicQuery(String query) {
        return cashStream.anyMatch(i -> query.contains(i.getTag()));
    }
}
