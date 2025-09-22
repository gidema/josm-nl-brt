package org.openstreetmap.josm.plugins.nl_brt.gui;

import static org.openstreetmap.josm.tools.I18n.tr;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.swing.AbstractAction;

import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.PleaseWaitRunnable;
import org.openstreetmap.josm.io.OsmTransferException;
import org.openstreetmap.josm.plugins.nl_brt.io.MultiFeatureDownloader;
import org.openstreetmap.josm.plugins.nl_brt.jts.Boundary;
import org.openstreetmap.josm.tools.ImageProvider;
import org.xml.sax.SAXException;

public class BrtDownloadAction extends AbstractAction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private LocalDateTime startDate;
    private boolean cancelled = false;
    private Boundary boundary;
    private final SlippyMapDownloadDialog slippyDialog;
    private final FixedBoundsDownloadDialog fixedDialog;

    private MultiFeatureDownloader downloader;

    public BrtDownloadAction() {
        super("Download", ImageProvider.get("download"));
        slippyDialog = new SlippyMapDownloadDialog();
        fixedDialog = new FixedBoundsDownloadDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.downloader = new MultiFeatureDownloader();
        run();
    }

    public void run() {
        cancelled = false;
        boundary = getBoundary();
        startDate = LocalDateTime.now();
        if (!cancelled) {
            DownloadTask task = new DownloadTask();
            MainApplication.worker.submit(task);
        }
    }

    private Boundary getBoundary() {
        var dialog = slippyDialog;
        dialog.restoreSettings();
        dialog.setVisible(true);
        if (dialog.isCanceled()) {
            cancelled = true;
            return null;
        }
        dialog.rememberSettings();
        return new Boundary(dialog.getSelectedDownloadArea());
    }

    private class DownloadTask extends PleaseWaitRunnable {

        public DownloadTask() {
            super(tr("Downloading data"));
        }

        @Override
        protected void cancel() {
//            downloader.cancel();
        }

        @Override
        protected void realRun() throws SAXException, IOException,
        OsmTransferException {
            downloader.run(boundary);
        }

        @Override
        protected void finish() {
//            MainApplication.getLayerManager().setActiveLayer(getContext().getComponent(OdLayerManager.class).getOsmDataLayer());
        }
    }
}
