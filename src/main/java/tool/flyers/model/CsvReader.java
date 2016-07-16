package tool.flyers.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvReader implements ModelReader {
    private String filePath;
    private Reader reader;

    private String[] names;
    private List<String[]> valuesList;

    public CsvReader(String path) throws IOException {
        filePath = path;
        read();
    }

    private void read() throws IOException {
        reader = new FileReader(filePath);
        CSVParser parser = CSVFormat.EXCEL.parse(reader);
        List<CSVRecord> records = parser.getRecords();

        // first row is names row
        CSVRecord record = records.get(0);
        names = new String[record.size()];
        getRecordValues(record, names);

        valuesList = new ArrayList<String[]>(records.size() - 1);
        for(int i = 1 ; i < records.size() ; i++) {
            record = records.get(i);
            String[] values = new String[record.size()];
            getRecordValues(record, values);
            valuesList.add(values);
        }
    }

    private void getRecordValues(CSVRecord record, String[] dest) {
        for(int i = 0 ; i < record.size(); i++) {
            dest[i] = record.get(i);
        }
    }

    @Override
    public String[] names() {
        if(names == null) {
            throw new IllegalStateException();
        }
        return names;
    }

    @Override
    public List<String[]> values() {
        if(valuesList == null) {
            throw new IllegalStateException();
        }
        return valuesList;
    }

}
