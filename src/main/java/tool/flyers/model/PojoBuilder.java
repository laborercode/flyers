package tool.flyers.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PojoBuilder {
    private String className;
    private List<String> fieldNames;
    private StringBuilder sb;
    private Map<String, String> variableNameMap;
    private List<String> methodNameList;
    private boolean build;

    public PojoBuilder(String className, List<String> fieldNames) {
        this.className = className;
        this.fieldNames = fieldNames;
        this.variableNameMap = new HashMap<String, String>();
        this.methodNameList = new ArrayList<String>();
    }

    public String build() {
        sb = new StringBuilder(1024);

        // package
        sb.append("package " + PojoGenerator.PACKAGE_NAME + ";\n");
        sb.append("\n");

        // class
        sb.append("public class " + className + " {\n"); // class open

        // declare fields
        for(String field : fieldNames) {
            String camelCase = toCamelCase(field);
            variableNameMap.put(field, camelCase);
            sb.append("    private String " + camelCase + ";\n");
        }
        sb.append("\n");

        // ctor.
        sb.append("    public " + className + "() {\n");
        sb.append("    }\n");
        sb.append("\n");

        // getter/setter
        for(String field : fieldNames) {
            String titleCase = toTitleCase(field);
            methodNameList.add(titleCase);

            // getter
            sb.append("    public String get" + titleCase + "() {\n");
            sb.append("        return " + variableNameMap.get(field) + ";\n");
            sb.append("    }\n");
            sb.append("\n");

            // setter
            sb.append("    public void set" + titleCase + "(String value) {\n");
            sb.append("        " + variableNameMap.get(field) + " = value;\n");
            sb.append("    }\n");
            sb.append("\n");
        }

        // close
        sb.append("}\n");
        build = true;
        return sb.toString();
    }

    /**
     * 입력받은 필드 이름에서 delimiter가 제거된 title case를 리턴한다.
     * 이 값 앞에 'get'/'set'을 붙인 method가 이 pojo에 존재하게 된다.
     * @return
     * @throws IllegalStateException
     */
    public List<String> getMethodNameList() {
        if(!build) {
            throw new IllegalStateException();
        }
        return methodNameList;
    }

    // Hello world -> helloWorld
    private String toCamelCase(String words) {
        final String DELIMITERS = " -.";

        words = words.trim();
        StringBuffer sb = new StringBuffer(words.length());
        boolean upper = false;

        for(char c : words.toCharArray()) {
            if(DELIMITERS.indexOf(c) != -1) {
                upper = true;
                continue;
            }

            if(upper) {
                sb.append(Character.toUpperCase(c));
                upper = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    // hello world -> HelloWorld
    private String toTitleCase(String words) {
        words = toCamelCase(words);

        char c = words.charAt(0);
        return Character.toUpperCase(c) + words.substring(1);
    }
}
