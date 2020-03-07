package ar.edu.itba.ss;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class CommandParser {

    // N
    // M
    // static file
    // dynamic file
    // rc


    private Options createOptions() {
        Options options = new Options();
        options.addOption("L", "length", true, "length of grid");
        options.addOption("S", "static", true, "Static file path");
        options.addOption("D","dynamic", true, "Dynamic file path");
        return options;
    }

    public void parseCommandLine(String[] args) {
        Options options = createOptions();
        if(args.length > 0) {
            //parse
        }
        else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("", options);
        }

    }



}
