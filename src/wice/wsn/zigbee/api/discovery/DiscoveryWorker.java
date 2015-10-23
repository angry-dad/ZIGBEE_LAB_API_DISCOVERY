package wice.wsn.zigbee.api.discovery;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.XBeeNetwork;

public class DiscoveryWorker extends SwingWorker<String, String> {

	private JTextArea textArea;
	private JButton btnDiscover;
	private String port;
	private int baud;
	private int timeout;

	public DiscoveryWorker(JTextArea textArea, JButton btnDiscover, String port, int baud, int timeout) {
		this.textArea = textArea;
		this.btnDiscover = btnDiscover;
		this.port = port;
		this.baud = baud;
		this.timeout = timeout;
	}

	@Override
	protected String doInBackground() throws Exception {
		XBeeDevice myDevice = null;
		XBeeNetwork myXBeeNetwork = null;
		DiscoveryListener discoveryCallback = null;

		try {
			myDevice = new XBeeDevice(port, baud);
			myDevice.open();
			myXBeeNetwork = myDevice.getNetwork();

			myXBeeNetwork.setDiscoveryTimeout(timeout * 1000); // GUI specifies
																// seconds,
																// method is
																// specified in
																// milliseconds

			myXBeeNetwork.addDiscoveryListener(discoveryCallback = new DiscoveryListener(this));
			myXBeeNetwork.startDiscoveryProcess();

			publish("Discovering remote XBee devices...");
			while (!this.isCancelled()) {

			}

		} catch (Exception ex) {
			publish(ex.getMessage());

		} finally {
			myDevice.close();
			if (myXBeeNetwork != null) {
				myXBeeNetwork.stopDiscoveryProcess();
				myXBeeNetwork.removeDiscoveryListener(discoveryCallback);
			}

			publish("done");
			this.cancel(true);
		}
		return null;
	}

	@Override
	protected void process(List<String> chunks) {
		// TODO Auto-generated method stub
		for (final String string : chunks) {
			if (string.equals("done")) {
				btnDiscover.setEnabled(true);
			} else {
				textArea.append(string);
				textArea.append("\n");
			}
		}
	}

	public void publishData(String data) {
		publish(data);
	}

}
