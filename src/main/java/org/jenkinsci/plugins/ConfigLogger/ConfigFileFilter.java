package org.jenkinsci.plugins.ConfigLogger;

import java.io.File;
import java.io.FileFilter;
import jenkins.model.Jenkins;

/**
 * Created by eerilio on 5/11/15.
 */
public class ConfigFileFilter implements FileFilter {

    static final ConfigFileFilter INSTANCE = new ConfigFileFilter();

    @Override
    public boolean accept(File file) {
        //Checks if file is not a directory, and if the root path is the same directory as file
        return !file.isDirectory() && Jenkins.getInstance().root.getAbsolutePath().equals(file.getParent());
    }

    public static boolean accepts(File file) {
        return INSTANCE.accept(file);
    }
}
