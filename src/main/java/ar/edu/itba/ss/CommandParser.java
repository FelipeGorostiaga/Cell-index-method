package ar.edu.itba.ss;

import org.apache.commons.cli.*;

public class CommandParser {

    static String staticFilePath;
    static String dynamicFilePath;
    static int M;
    static double RC;
    static boolean periodicContour = false;
    static boolean bruteForce = false;


    private static Options createOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Show help menu");
        options.addOption("m","matrix", true, "Size of the squared matrix.");
        options.addOption("s", "static", true, "Static file path");
        options.addOption("d","dynamic", true, "Dynamic file path");
        options.addOption("rc", "radius", true, "Interaction radius");
        options.addOption("p", "periodic contour", false, "Activate periodic contour distance");
        options.addOption("b", "brute force", false, "Activate brute force algorithm");
        return options;
    }

    private static void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Help", options);
        System.exit(0);
    }

    public static void parseCommandLine(String[] args) {
        Options options = createOptions();
        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("h")) printHelp(options);
            if(cmd.hasOption("b")) bruteForce = true;
            if (!cmd.hasOption("d") || !cmd.hasOption("s")){
                System.out.println("You must specify a static file and a dynamic file!");
                System.exit(1);
            }
            dynamicFilePath = cmd.getOptionValue("d");
            staticFilePath = cmd.getOptionValue("s");

            if (cmd.hasOption("m")) M = Integer.parseInt(cmd.getOptionValue("m"));
            if (cmd.hasOption("rc")) RC = Double.parseDouble(cmd.getOptionValue("rc"));
            if (cmd.hasOption("p")) periodicContour = true;

        }catch (Exception e){
           System.out.println("Invalid command format");
           printHelp(options);
        }
    }

}
