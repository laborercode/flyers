package tool.flyers.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PojoBuilderTest {
    @Test
    public void testPojoBuilderTest() {
        final String className = "TestClass";
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("name");
        fieldNames.add("price");
        fieldNames.add("item description");

        PojoBuilder builder = new PojoBuilder(className, fieldNames);
        String pojo = builder.build();
        System.out.println(pojo);

        Assert.assertTrue(pojo.contains("public class " + className));
        Assert.assertTrue(pojo.contains("private String price;"));
        Assert.assertTrue(pojo.contains("public String getName()"));

        List<String> methodNames = builder.getMethodNameList();
        Assert.assertTrue(methodNames.contains("Name"));
        Assert.assertTrue(methodNames.contains("Price"));
        Assert.assertTrue(methodNames.contains("ItemDescription"));
    }

    @Test(expected=IllegalStateException.class)
    public void testPojoWriterTest_getMethodNameBeforeBuild() {
        final String className = "TestClass";
        List<String> fieldNames = new ArrayList<String>();

        PojoBuilder builder = new PojoBuilder(className, fieldNames);
        builder.getMethodNameList();
    }
}
