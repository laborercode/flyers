package tool.flyers;

public class FlyersContext {
    private static final String DEFAULT_JADEFILE = "index.jade";

    private String baseDir;

    private String modelFileName;

    private String jadeFileName;

    public FlyersContext() {
        jadeFileName = DEFAULT_JADEFILE;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String dir) {
        baseDir = dir;
    }

    public String getJadeFileName() {
        return jadeFileName;
    }

    public void setJadeFileName(String name) {
        jadeFileName = name;
    }

    public String getModelFileName() {
        return modelFileName;
    }

    public void setModelFileName(String name) {
        modelFileName = name;
    }
}
