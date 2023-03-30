package edu.school21.printer.logic;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.Parameter;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = "--white", required = true)
    private String white;

    @Parameter(names = "--black", required = true)
    private String black;

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}