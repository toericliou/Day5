package org.jenkinsci.plugins.ConfigLogger;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by eerilio on 5/11/15.
 */
public class SystemFileFilterTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    private File file1;
    private File file2;
    private File rootDir;
    private File logDir;

    @Before
    public void setUp() throws Exception {
        logDir = new File(j.getInstance().root, "logs"); //creates log File object in new dir
        rootDir = new File(j.getInstance().root, "root"); //sets rootDir to Jenkins root

        if(!rootDir.isDirectory())
            rootDir.mkdirs(); //create directory
        if(!logDir.isDirectory())
            logDir.mkdirs(); //create directory

        file1 = new File(logDir, "temp1.xml"); //create temp file in rootDir
        file2 = new File(rootDir, "temp2.xml"); //create temp file in logDir
    }

    @Test
    public void testAccepts() throws Exception {
        assertTrue(SystemFileFilter.accepts(file1)); //accepts only if file is located in folder "log"
        assertFalse(SystemFileFilter.accepts(file2)); //file2 is located in "root" folder, not accepted
    }
}