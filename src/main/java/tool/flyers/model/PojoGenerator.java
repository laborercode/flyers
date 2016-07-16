package tool.flyers.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * 입력받은 class name과 field name으로 POJO를 만든다.
 * 
 * @author sungbae
 *
 */
public class PojoGenerator {
    public static final String PACKAGE_NAME = "tool.flyers.model";

    private boolean generated;
    private final String className;
    private List<String> fieldNames;
    protected PojoBuilder writer;

    public PojoGenerator(String className) {
        this.className = className;
        fieldNames = new ArrayList<String>();
    }

    public void addField(String fieldName) {
        if(generated) {
            throw new IllegalStateException();
        }

        fieldNames.add(fieldName);
    }

    private void compile(String code) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileObject file = new JavaSourceFromString(PojoGenerator.PACKAGE_NAME + "." + className, code);

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        boolean success = task.call();
    }

    public synchronized Object newInstance()
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException,
            SecurityException {
        Object obj = null;
        if(!generated) {
            writer = new PojoBuilder(className, fieldNames);
            compile(writer.build());

            generated = true;
        }

        obj = Class.forName(PACKAGE_NAME + "." + className).newInstance();
        return obj;
    }

    private class JavaSourceFromString extends SimpleJavaFileObject {
        private final String code;

        protected JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }
    }
}
