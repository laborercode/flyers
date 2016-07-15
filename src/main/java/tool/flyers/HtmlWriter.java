package tool.flyers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HtmlWriter {
    private static final String EXT = ".html";

    private PrintWriter writer;

    public HtmlWriter(String outputDir, String fileName) throws FileNotFoundException {
        if(!outputDir.endsWith(File.separator)) {
            outputDir += File.separator;
        }
        String path = outputDir + fileName + EXT;
        writer = new PrintWriter(path);
    }

    public void write(String html) {
        writer.print(html);
    }
}
