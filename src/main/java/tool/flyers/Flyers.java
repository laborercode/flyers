package tool.flyers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tool.flyers.model.ExcelReader;
import tool.flyers.model.ItemGenerator;
import tool.flyers.model.ModelBuilder;
import tool.flyers.model.ModelReader;

public class Flyers {

    public static void main(String[] args) {
        OptionParser optionParser = new OptionParser();
        FlyersContext context = optionParser.parse(args);

        ItemGenerator generator = new ItemGenerator("Item");

        // read Items from resource
        ModelBuilder builder = new ModelBuilder();
        ModelReader reader = new ExcelReader();
        List<Object> items = new ArrayList<Object>();

        for(String field : reader.names()) {
            generator.addField(field);
        }
        try {
            for(String[] valueArr : reader.values()) {
                Object obj = generator.newInstance(valueArr);
                items.add(obj);
            }
            builder.add("items", items);
            Map<String, Object> model = builder.build();
    
            // render
            //Jade4J.render(filename, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
