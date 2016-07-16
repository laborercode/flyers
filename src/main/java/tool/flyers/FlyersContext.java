package tool.flyers;

public class FlyersContext {
    private static final String DEFAULT_JADEFILE = "index.jade";

    // path of template files
    private String templatePath;

    // path of resource files
    private String resourcePath;

    // file name of top level jade
    private String jadeFileName;

    public FlyersContext() {
        jadeFileName = DEFAULT_JADEFILE;
    }

    public String getJadeFileName() {
        return jadeFileName;
    }
}
