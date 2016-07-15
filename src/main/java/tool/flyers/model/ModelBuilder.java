package tool.flyers.model;

import java.util.HashMap;
import java.util.Map;

public class ModelBuilder {
    private Map<String, Object> model;

    public ModelBuilder() {
        model = new HashMap<String, Object>();
    }

    public Map<String, Object> build() {
        return model;
    }

    public ModelBuilder add(String key, Object obj) {
        model.put(key, obj);
        return this;
    }
}
