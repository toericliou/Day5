package org.jenkinsci.plugins.ConfigLogger;

import hudson.Extension;
import hudson.model.Item;
import hudson.model.listeners.ItemListener;
import jenkins.model.Jenkins;

import java.util.logging.Logger;

import static java.util.logging.Level.*;

/**
 * Created by eerilio on 5/11/15.
 */
@Extension
public class MyItemListener extends ItemListener {

    private static final Logger LOG = Logger.getLogger(MyItemListener.class.getName());
    private boolean createTriggered, deleteTriggered;

    @Override
    public void onCreated(Item item) {
        super.onCreated(item);
        LOG.log(INFO, "New Item {0} Created ", item);
        createTriggered = true;
    }

    @Override
    public void onDeleted(Item item) {
        super.onDeleted(item);
        LOG.log(INFO, "Item {0} Deleted ", item);
        deleteTriggered = true;
    }

    public boolean getCreatedTriggered(){
        return createTriggered;
    }

    public boolean getDeletedTriggered(){
        return deleteTriggered;
    }

}
