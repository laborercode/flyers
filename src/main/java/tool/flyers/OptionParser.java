package tool.flyers;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class OptionParser {
    private static final String OPTION_JADE = "j";
    private static final String OPTION_JADE_LONG = "jade";
    private static final String OPTION_JADE_ARG_NAME = "jadeFileName";
    private static final String OPTION_JADE_DESC = "";

    private static final String OPTION_TEMPLATE_DIR = "t";
    private static final String OPTION_TEMPLATE_DIR_LONG = "template-dir";
    private static final String OPTION_TEMPLATE_DIR_ARG_NAME = "templateDir";
    private static final String OPTION_TEMPLATE_DIR_DESC = "";

    private CommandLineParser parser = new DefaultParser();
    private Options options;

    public OptionParser() {
        options = buildOptions();
    }

    private Options buildOptions() {
        options = new Options();

        Option helpOption = Option.builder("h")
                .longOpt("help")
                .desc("Print help for this application")
                .build();
        Option jadeOption = Option.builder(OPTION_JADE)
                .longOpt(OPTION_JADE_LONG)
                .hasArg()
                .argName(OPTION_JADE_ARG_NAME)
                .desc(OPTION_JADE_DESC)
                .build();
        Option templateDirOption = Option.builder(OPTION_TEMPLATE_DIR)
                .longOpt(OPTION_TEMPLATE_DIR_LONG)
                .hasArg()
                .argName(OPTION_TEMPLATE_DIR_ARG_NAME)
                .required()
                .desc(OPTION_TEMPLATE_DIR_DESC)
                .build();

        options.addOption(helpOption);
        options.addOption(templateDirOption);
        options.addOption(jadeOption);
        return options;
    }

    public FlyersContext parse(String[] args) {
        CommandLine cl;
        FlyersContext context = new FlyersContext();
        try {
            cl = parser.parse(options, args);

            if(cl.hasOption('h')) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("java -jar tool.Flyers", options, true);
            }
        } catch (ParseException e) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("java -jar tool.Flyers", options, true);
            System.exit(1);
        }
        return context;
    }
}
