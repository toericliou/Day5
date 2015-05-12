package org.jenkinsci.plugins.ConfigLogger;

import hudson.cli.DeleteBuildsCommand;
import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Item;
import hudson.model.Run;
import hudson.tasks.Shell;
import hudson.tools.CommandInstaller;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;


import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by eerilio on 5/11/15.
 */
public class MyItemListenerTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    MyItemListener listener = new MyItemListener();

    @Test
    public void onCreated() throws Exception {
        FreeStyleProject proj = j.createFreeStyleProject();
        proj.getBuildersList().add(new Shell("echo Created"));
        listener.onCreated(proj);
        FreeStyleBuild build = j.buildAndAssertSuccess(proj);
        j.assertLogContains("Created", build);
    }

    @Test
    public void onDeleted() throws Exception {
        FreeStyleProject proj = j.createFreeStyleProject();
        j.getInstance().onDeleted(proj);
        listener.onDeleted(proj);
        assertFalse(j.getInstance().getItems().contains(proj)); //Deleted "proj"
        assertTrue(listener.getDeletedTriggered());
    }

}