package org.jenkinsci.plugins.ConfigLogger;

import hudson.Extension;
import hudson.XmlFile;
import hudson.model.Saveable;
import hudson.model.listeners.SaveableListener;


import static java.util.logging.Level.*;

import java.io.File;
import java.util.logging.Logger;
/**
 * Created by eerilio on 5/11/15.
 */

@Extension
public class MySaveableListener extends SaveableListener {

    private static final Logger LOG = Logger.getLogger(MySaveableListener.class.getName());

    @Override
    public void onChange(final Saveable o, final XmlFile file){
        if(isConfigFile(file) || isSystemFile(file))
            LOG.log(INFO, "SETTINGS SAVED FOR {0}", file.toString());

        else
            LOG.log(INFO, "NOT A CONFIGFILE SAVED for {0}", file.toString());
    }

    private boolean isConfigFile(XmlFile file){
        return ConfigFileFilter.accepts(new File(file.toString()));
    }

    private boolean isSystemFile(XmlFile file){
        return SystemFileFilter.accepts(new File(file.toString()));
    }

    public static Logger getLog(){
        return LOG;
    }

}
