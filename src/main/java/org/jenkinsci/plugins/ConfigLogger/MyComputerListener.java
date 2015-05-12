package org.jenkinsci.plugins.ConfigLogger;

import hudson.Extension;
import hudson.slaves.ComputerListener;

import java.util.logging.Logger;
import static java.util.logging.Level.*;

/**
 * Created by eerilio on 5/11/15.
 */

@Extension
public class MyComputerListener extends ComputerListener {

    private static final Logger LOG = Logger.getLogger(MyComputerListener.class.getName());

    private boolean triggered = false;
    @Override
    public void onConfigurationChange() {
        LOG.log(INFO,"Configuration Change to Slave node");
        triggered = true;
    }

    public boolean getTriggered(){
        return triggered;
    }
}
