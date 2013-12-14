package org.dnaselector.cli;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This simple utility provides a very simple wrapper class to help use the
 * apache commons CLI. The SimpleGNUCommandLine provides the command line
 * parser-wrapper for GNU-style options. It provides basic parsing ability
 * with built in support for printing help into system.out.
 * See example usage in main method
 *
 * @author zsugiart
 * @see <a href="http://www.zugiart.com/2010/11/simple-gnu-apache-commons-cli-java/">Found there</a>
 */
@SuppressWarnings("UnusedDeclaration")
public class SimpleGNUCommandLine {

    private Options options = new Options();
    private String[] cmdlineArgs = null;
    private CommandLine cmdLine = null;
    private boolean isParsed = false;

    public SimpleGNUCommandLine(String cmdLineArgs[]) {
        this.cmdlineArgs = cmdLineArgs;
        this.addOption("help", "print this help message", false, false);
    }

    /**
     * Adds an option into the command line parser
     *
     * @param optionName  - the option name
     * @param description - option descriptiuon
     * @param hasValue    - if set to true, --option=value, otherwise, --option is a boolean
     * @param isMandatory - if set to true, the option must be provided.
     */
    @SuppressWarnings("static-access")
    public void addOption(String optionName, String description, boolean hasValue, boolean isMandatory) {
        OptionBuilder opt = OptionBuilder.withLongOpt(optionName);
        opt = opt.withDescription(description);
        if (hasValue) opt = opt.hasArg();
        if (isMandatory) opt = opt.isRequired();
        options.addOption(opt.create());
    }

    // this method is implicitly called by accessor methods
    // and will only be called on first instance.
    private void parse()
            throws Exception {
        CommandLineParser parser = new GnuParser();
        try {
            this.cmdLine = parser.parse(this.options, this.cmdlineArgs);
        } catch (MissingOptionException moe) {
            printUsage();
        }
        this.isParsed = true;
        if (this.cmdLine.hasOption("help")) printUsage();
    }

    public void printUsage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("available options as follow:", options);
        System.exit(1);
    }

    public String getString(String optionName) throws Exception {
        if (!this.isParsed) this.parse();
        return this.cmdLine.getOptionValue(optionName);
    }

    public Integer getInteger(String optionName) throws Exception {
        return Integer.parseInt(this.getString(optionName));
    }

    public Double getDouble(String optionName) throws Exception {
        return Double.parseDouble(this.getString(optionName));
    }

    public List<String> getList(String optionName, String delimiter) throws Exception {
        List<String> arrayList = new ArrayList<String>();
        StringTokenizer tkn = new StringTokenizer(this.getString(optionName), delimiter);
        while (tkn.hasMoreTokens()) arrayList.add(tkn.nextToken());
        return arrayList;
    }

    public boolean hasOption(String optionName) throws Exception {
        if (!this.isParsed) this.parse();
        return this.cmdLine.hasOption(optionName);
    }

    /*
     * Example, to run this class, must
     */
    public static void main(String[] args)
            throws Exception {
        SimpleGNUCommandLine cli = new SimpleGNUCommandLine(args);
        cli.addOption("port", "xml-rpc port", true, true);
        cli.addOption("serviceName", "xml-rpc service point name", true, true);
        cli.addOption("optional", "xml-rpc service point name", true, false);

        System.out.print(cli.getInteger("port"));
        System.out.print(cli.getString("serviceName"));
        if (cli.hasOption("optional")) System.out.println("--optional is provided");
    }
}
