package tool.flyers.model;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CsvReaderTest {
    @Test
    public void testCsvReaderTest() throws IOException {
        CsvReader reader = new CsvReader("src/test/resources/test_csv.csv");
        String[] names = reader.names();
        List<String[]> valuesList = reader.values();

        String[] exptectedNames = new String[] { "name", "price", "item desc" };
        Assert.assertArrayEquals(exptectedNames, names);
    }
}
