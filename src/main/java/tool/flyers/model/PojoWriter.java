package tool.flyers.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PojoWriter {
    private String className;
    private List<String> fieldNames;
    private StringBuilder sb;
    private Map<String, String> variableNameMap;
    private List<String> methodNameList;

    public PojoWriter(String className, List<String> fieldNames) {
        this.className = className;
        this.fieldNames = fieldNames;
        this.variableNameMap = new HashMap<String, String>();
        this.methodNameList = new ArrayList<String>();
    }

    public String getPojo() {
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
            sb.append("\n");
        }

        // close
        sb.append("}\n");
        return sb.toString();
    }

    public List<String> getMethodNameList() {
        return methodNameList;
    }

    // Hello world -> helloWorld
    private String toCamelCase(String words) {
        final String DELIMITERS = " -";

        words = words.trim();
        StringBuffer sb = new StringBuffer(words.length());
        boolean upper = true;

        for(char c : words.toCharArray()) {
            if(DELIMITERS.indexOf(c) != -1) {
                upper = true;
                continue;
            } else {
                upper = false;
            }

            if(upper) {
                sb.append(Character.toUpperCase(c));
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
