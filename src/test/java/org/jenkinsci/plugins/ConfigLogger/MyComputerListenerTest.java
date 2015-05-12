package org.jenkinsci.plugins.ConfigLogger;

import hudson.model.AbstractProject;
import hudson.model.Computer;
import hudson.model.FreeStyleBuild;
import hudson.slaves.DumbSlave;
import hudson.slaves.SlaveComputer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static org.junit.Assert.*;

/**
 * Created by eerilio on 5/12/15.
 */
public class MyComputerListenerTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    MyComputerListener comp;

    @Before
    public void setUp() throws Exception {
        comp = new MyComputerListener();
    }

    @Test
    public void testOnConfigurationChange() throws Exception {
        DumbSlave slave = j.createSlave();
        Computer computer = slave.createComputer();
        comp.onConfigurationChange(); //Manual Trigger
        assertTrue(comp.getTriggered());
    }
}