package org.jenkinsci.plugins.ConfigLogger;

import jenkins.model.Jenkins;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by eerilio on 5/11/15.
 */
public class SystemFileFilter implements FileFilter {

    static final SystemFileFilter INSTANCE = new SystemFileFilter();

    @Override
    public boolean accept(File file) {
        //Checks if file is not a directory, and if file is located in Jenkins log folder in root path
        return !file.isDirectory() && new File(Jenkins.getInstance().root.getPath(),"logs").getAbsolutePath().equals(file.getParent());
    }

    public static boolean accepts(File file) {
        return INSTANCE.accept(file);
    }
}
