package com.bsx.stancore.utilities;

import com.bsx.stancore.Stancore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public class CrashAnalyzer {
    //in development
    private Stancore stancore;
    public CrashAnalyzer(Stancore stancore) {
        this.stancore = stancore;
    }

    public void analyzeCrash(File serverCrashLog) {
        if (serverCrashLog.exists() && serverCrashLog.canRead() && serverCrashLog.canExecute()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(serverCrashLog));
                Stream<String> lines = br.lines();
                String[] strings = (String[])lines.toArray();
                int recognizedCrashes = 0;
                for (int i = 0; i < strings.length - 1; i++) {
                    String s= strings[i];
                    if (s.contains("FileNotFoundException")) {
                        String fileName = s.substring(60);
                        Logger.log("[Stancore] The CrashAnalyzer recognized the missing file error " + fileName + ". Please contact the developer of this plugin with the full server log to get this error fixed.");
                        recognizedCrashes++;
                    } else if (s.contains("ArithmeticException")) {
                        String reason = s.substring(58);
                        if (reason.equalsIgnoreCase("/ by zero")) {
                            Logger.log("[Stancore] The CrashAnalyzer recognized a mathematical error in the plugin's code (zero divided by zero). Please contact the developer of this plugin with the full server log to get this error fixed.");
                            recognizedCrashes++;
                        } else if (reason.equalsIgnoreCase("Non-terminating decimal expansion; no exact representable decimal result.")) {
                            Logger.log("[Stancore] The CrashAnalyzer recognized a mathmetical error in the plugin's code (non terminating decimal). Please contact the developer of this plugin with the full server log to get this error fixed.");
                            recognizedCrashes++;
                        } else {
                            Logger.log("[Stancore] The CrashAnalyzer recognized a mathematical error in the plugin's code (unknown reason). Please contact the developer of this plugin with the full server log to get this error fixed.");
                            recognizedCrashes++;
                        }
                    } else if (s.contains("ArrayIndexOutOfBoundsException")) {
                        Logger.log("[Stancore] The CrashAnalyzer recognized an out of index error in the plugin's code (" + strings[i + 1] + "). Please contact the developer of this plugin with the full server log to get this error fixed.");
                        recognizedCrashes++;
                    } else if (s.contains("ArrayStoreException")) {
                        Logger.log("[Stancore] The CrashAnalyzer recognized an invalid input in an array in the plugin's code. Please contat the developer of this plugin with the full server log to get this error fixed.");
                        recognizedCrashes++;
                    } else if (s.contains("ClassCastException")) {

                    }
                }
                Logger.log("[Stancore] The CrashAnalyzer recognized " + recognizedCrashes + " possible causes of crashes in this server log.");
                return;
            } catch (FileNotFoundException fnfex) {
                fnfex.printStackTrace();
            }
        }
    }
}
