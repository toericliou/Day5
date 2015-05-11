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
public class ConfigFileFilterTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    private File file1;
    private File file2;
    private File rootDir;
    private File logDir;

    @Before
    public void setUp() throws Exception {
        logDir = new File(j.getInstance().root, "logs"); //creates log File object in new dir
        rootDir = j.getInstance().root; //sets rootDir to Jenkins root

        if(!rootDir.isDirectory())
            rootDir.mkdirs(); //create directory
        if(!logDir.isDirectory())
            logDir.mkdirs(); //create directory

        file1 = new File(rootDir, "temp1.xml"); //create temp file in rootDir
        file2 = new File(logDir, "temp2.xml"); //create temp file in logDir
    }

    @Test
    public void testAccepts() throws Exception {
        assertTrue(ConfigFileFilter.accepts(file1)); //accepts files located in rootDir
        assertFalse(ConfigFileFilter.accepts(file2)); //File 2 located in "logs" folder, not accepted
    }
}