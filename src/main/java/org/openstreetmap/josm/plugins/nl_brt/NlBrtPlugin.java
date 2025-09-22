package org.openstreetmap.josm.plugins.nl_brt;

import javax.swing.JMenu;

import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.util.GuiHelper;
import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;
import org.openstreetmap.josm.plugins.nl_brt.gui.BrtDownloadAction;

public class NlBrtPlugin extends Plugin {
    @SuppressWarnings("unused")
    private boolean debugMode;

    private final JMenu menu;

    public NlBrtPlugin(PluginInformation info) {
        super(info);
        menu = MainApplication.getMenu().dataMenu;

        new Thread(() -> {
            // Add menu in EDT
            GuiHelper.runInEDT(this::buildMenu);
        }).start();
    }
    
    private void buildMenu() {
        JMenu brtMenu = new JMenu("NL BRT");
        brtMenu.add(new BrtDownloadAction());
        menu.add(brtMenu);
    }
}