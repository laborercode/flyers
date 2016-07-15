package tool.flyers.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ItemGenerator extends PojoGenerator {

    public ItemGenerator(String className) {
        super(className);
    }

    public synchronized Object newInstance(String[] values) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        Object obj = super.newInstance();
        setValues(obj, values);
        return obj;
    }

    private void setValues(Object obj, String[] values)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        List<String> methodNames = writer.getMethodNameList();
        for(int i = 0 ; i < values.length ; i++) {
            obj.getClass().getDeclaredMethod(methodNames.get(i), String.class).invoke(obj, values[i]);
        }
    }
}
