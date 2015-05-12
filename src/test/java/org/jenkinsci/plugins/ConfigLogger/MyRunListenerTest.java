package org.jenkinsci.plugins.ConfigLogger;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.TaskListener;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static org.junit.Assert.*;

/**
 * Created by eerilio on 5/12/15.
 */
public class MyRunListenerTest {

    @Rule
    public JenkinsRule j = new JenkinsRule();

    MyRunListener listener;

    @Before
    public void setUp() throws Exception {
        listener = new MyRunListener();
    }


    @Test
    public void testOnCompleted() throws Exception {
        FreeStyleProject proj = j.jenkins.createProject(FreeStyleProject.class, "aJob");
        FreeStyleBuild build = j.buildAndAssertSuccess(proj);
        try {
            TaskListener list = j.createTaskListener();
            j.assertLogContains("SUCCESS", j.assertBuildStatusSuccess(proj.scheduleBuild2(0))); //Build Success
            listener.onCompleted(build, list);
            assertTrue(listener.isCompletedTriggered());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOnStarted() throws Exception {
        FreeStyleProject proj = j.jenkins.createProject(FreeStyleProject.class, "aJob");
        FreeStyleBuild build = j.buildAndAssertSuccess(proj);
        try {
            TaskListener list = j.createTaskListener();
            j.assertLogContains("SUCCESS", j.assertBuildStatusSuccess(proj.scheduleBuild2(0))); //Build Success
            listener.onStarted(build, list);
            assertTrue(listener.isStartedTriggered());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}