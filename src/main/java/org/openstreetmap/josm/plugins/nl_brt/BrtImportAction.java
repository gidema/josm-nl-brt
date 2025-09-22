package org.openstreetmap.josm.plugins.nl_brt;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.openstreetmap.josm.plugins.nl_brt.gui.FixedBoundsDownloadDialog;
import org.openstreetmap.josm.plugins.nl_brt.gui.SlippyMapDownloadDialog;
import org.openstreetmap.josm.tools.ImageProvider;

public class BrtImportAction extends AbstractAction {

    private static final long serialVersionUID = 1L;
    
    private final SlippyMapDownloadDialog slippyDialog;
    private final FixedBoundsDownloadDialog fixedDialog;

    public BrtImportAction() {
        super("Download", ImageProvider.get("download"));
        slippyDialog = new SlippyMapDownloadDialog();
        fixedDialog = new FixedBoundsDownloadDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
}
