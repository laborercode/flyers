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

        Assert.assertEquals(2, valuesList.size());

        String[] row0 = new String[] { "아이템1", "9500", "설명1" };
        Assert.assertArrayEquals(row0, valuesList.get(0));

        String[] row1 = new String[] { "아이템2", "10000", "설명2" };
        Assert.assertArrayEquals(row1, valuesList.get(1));
    }
}
