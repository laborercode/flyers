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

    private static final String OPTION_BASE_DIR = "b";
    private static final String OPTION_BASE_DIR_LONG = "base-dir";
    private static final String OPTION_BASE_DIR_ARG_NAME = "baseDir";
    private static final String OPTION_BASE_DIR_DESC = "";

    private static final String OPTION_MODEL = "m";
    private static final String OPTION_MODEL_LONG = "model";
    private static final String OPTION_MODEL_ARG_NAME = "modelFileName";
    private static final String OPTION_MODEL_DESC = "";

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
        Option baseDirOption = Option.builder(OPTION_BASE_DIR)
                .longOpt(OPTION_BASE_DIR_LONG)
                .hasArg()
                .argName(OPTION_BASE_DIR_ARG_NAME)
                .required()
                .desc(OPTION_BASE_DIR_DESC)
                .build();
        Option modelOption = Option.builder(OPTION_MODEL)
                .longOpt(OPTION_MODEL_LONG)
                .hasArg()
                .argName(OPTION_MODEL_ARG_NAME)
                .required()
                .desc(OPTION_MODEL_DESC)
                .build();

        options.addOption(helpOption);
        options.addOption(baseDirOption);
        options.addOption(jadeOption);
        options.addOption(modelOption);
        return options;
    }

    public FlyersContext parse(String[] args) {
        CommandLine cl;
        FlyersContext context = new FlyersContext();
        try {
            cl = parser.parse(options, args);

            if(cl.hasOption('h')) {
                printHelp(0);
            }

            // required
            context.setBaseDir(cl.getOptionValue(OPTION_BASE_DIR));
            context.setModelFileName(cl.getOptionValue(OPTION_MODEL));

            if(cl.hasOption(OPTION_JADE)) {
                context.setJadeFileName(cl.getOptionValue(OPTION_JADE));
            }
        } catch (ParseException e) {
            printHelp(1);
        }
        return context;
    }

    private void printHelp(int code) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("java -jar tool.Flyers", options, true);
        System.exit(code);
    }
}
