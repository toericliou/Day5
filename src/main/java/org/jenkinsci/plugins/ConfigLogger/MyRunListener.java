package org.jenkinsci.plugins.ConfigLogger;

import hudson.Extension;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;
import hudson.tasks.BuildTrigger;


import java.util.logging.Logger;
import static java.util.logging.Level.*;

/**
 * Created by eerilio on 5/11/15.
 */
@Extension
public class MyRunListener<R extends Run> extends RunListener<R> {

    private static final Logger LOG = Logger.getLogger(MyRunListener.class.getName());

    private boolean completedTriggered, startedTriggered = false;

    @Override
    public void onCompleted(R run, TaskListener listener) {
        super.onCompleted(run, listener);
        LOG.log(INFO, "COMPLETED for {0}", run);
        completedTriggered = true;
    }

    @Override
    public void onStarted(R run, TaskListener listener) {
        super.onStarted(run, listener);
        LOG.log(INFO, "STARTED for {0}", run);
        startedTriggered = true;
    }

    public boolean isCompletedTriggered(){
        return completedTriggered;
    }

    public boolean isStartedTriggered() {
        return startedTriggered;
    }
}
