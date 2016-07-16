package tool.flyers;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class OptionParser {
    private static final String OPTION_JADE = "j";
    private static final String OPTION_JADE_LONG = "jade";
    private static final String OPTION_JADE_DESC = "";

    private static final String OPTION_TEMPLATE_DIR = "t";
    private static final String OPTION_TEMPLATE_DIR_LONG = "templateDir";
    private static final String OPTION_TEMPLATE_DIR_DESC = "";

    private CommandLineParser parser = new DefaultParser();
    private Options options;

    public OptionParser() {
        options = buildOptions();
    }

    private Options buildOptions() {
        Options options = new Options();

        Option jadeOption = Option.builder(OPTION_JADE)
                .longOpt(OPTION_JADE_LONG)
                .hasArg()
                .desc(OPTION_JADE_DESC)
                .build();
        Option templateDirOption = Option.builder(OPTION_TEMPLATE_DIR)
                .longOpt(OPTION_TEMPLATE_DIR_LONG)
                .hasArg()
                .required()
                .desc(OPTION_TEMPLATE_DIR_DESC)
                .build();

        options.addOption(templateDirOption);
        options.addOption(jadeOption);
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
