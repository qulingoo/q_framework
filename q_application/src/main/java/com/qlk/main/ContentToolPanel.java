package com.qlk.main;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.shuyun.core.resource.WindowController;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.DownloadHandler;
import com.teamdev.jxbrowser.chromium.DownloadItem;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.StorageType;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.ConsoleListener;
import com.teamdev.jxbrowser.chromium.events.ScriptContextAdapter;
import com.teamdev.jxbrowser.chromium.events.ScriptContextEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class ContentToolPanel extends JPanel {
	private static ContentToolPanel panel;
	private static final long serialVersionUID = 1L;
	private Browser browser;

	public static JPanel getInstance() {
		synchronized (ContentToolPanel.class) {
			if (panel == null) {
				panel = new ContentToolPanel();
			}
			return panel;
		}

	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	private ContentToolPanel() {
		BrowserContextParams params = new BrowserContextParams("user-data-dir");
		params.setStorageType(StorageType.MEMORY);
		BrowserContext browserContext = new BrowserContext(params);

		browser = new Browser(browserContext);

		setBorder(null);
		browser.setDownloadHandler(new DownloadHandler() {

			@Override
			public boolean allowDownload(DownloadItem downloaditem) {
				return true;
			}
		});

		browser.addScriptContextListener(new ScriptContextAdapter() {

			@Override
			public void onScriptContextCreated(ScriptContextEvent event) {

				Browser browser = event.getBrowser();
				JSValue window = browser.executeJavaScriptAndReturnValue(event.getJSContext().getFrameId(), "window");
				window.asObject().setProperty("_", new WindowController());

			}

		});

		browser.addConsoleListener(new ConsoleListener() {
			public void onMessage(ConsoleEvent event) {
				System.out.println("Message: " + event.getMessage() + " " + event.getLineNumber() + " "
						+ event.getSource().split("/")[event.getSource().split("/").length - 1]);
			}
		});

		BrowserView browserView = new BrowserView(browser);
		setLayout(new BorderLayout());
		// browser.loadURL(System.getProperty("user.dir") + "/html"
		// + "/index.html");
		StringBuffer url=new StringBuffer(getClass().getResource("/static").getPath()+"/metadata/index.html");
		url.deleteCharAt(0);
		System.out.println(url);
		browser.loadURL(url.toString() );
//		browser.loadURL("http://localhost:8081");
		 
		add(browserView, BorderLayout.CENTER);

	}

}
