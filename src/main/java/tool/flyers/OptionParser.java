package tool.flyers;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class OptionParser {
    private static final String OPTION_ = "";

    private CommandLineParser parser = new DefaultParser();
    private Options options;

    public OptionParser() {
        options = buildOptions();
    }

    private Options buildOptions() {
        Options options = new Options();
        
        return options;
    }

    public FlyersContext parse(String[] args) {
        FlyersContext context = new FlyersContext();
        try {
            parser.parse(options, args);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return context;
    }
}
