package wice.wsn.zigbee.api.discovery;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frmXbeeZigbeeApi;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtPort;
	private JTextField txtBaud;
	private JButton btnDiscover;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnClear;
	private JLabel lblDiscoveryTimeouts;
	private JTextField txtTimeout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmXbeeZigbeeApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmXbeeZigbeeApi = new JFrame();
		frmXbeeZigbeeApi.setTitle("XBee: ZigBee API Discovery");
		frmXbeeZigbeeApi.setBounds(100, 100, 470, 300);
		frmXbeeZigbeeApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 84, 218, 152, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		frmXbeeZigbeeApi.getContentPane().setLayout(gridBagLayout);

		lblNewLabel = new JLabel("Serial Port:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frmXbeeZigbeeApi.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtPort = new JTextField();
		txtPort.setText("/dev/tty.usbserial-A902UUM4");
		GridBagConstraints gbc_txtPort = new GridBagConstraints();
		gbc_txtPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPort.insets = new Insets(0, 0, 5, 5);
		gbc_txtPort.gridx = 1;
		gbc_txtPort.gridy = 0;
		frmXbeeZigbeeApi.getContentPane().add(txtPort, gbc_txtPort);
		txtPort.setColumns(10);

		lblNewLabel_1 = new JLabel("Baud Rate:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		frmXbeeZigbeeApi.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtBaud = new JTextField();
		txtBaud.setText("115200");
		GridBagConstraints gbc_txtBaud = new GridBagConstraints();
		gbc_txtBaud.insets = new Insets(0, 0, 5, 5);
		gbc_txtBaud.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBaud.gridx = 1;
		gbc_txtBaud.gridy = 1;
		frmXbeeZigbeeApi.getContentPane().add(txtBaud, gbc_txtBaud);
		txtBaud.setColumns(10);

		btnDiscover = new JButton("Start Discovery");
		btnDiscover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDiscover.setEnabled(false);
				DiscoveryWorker discover = new DiscoveryWorker(textArea, btnDiscover, txtPort.getText(),
						Integer.parseInt(txtBaud.getText()), Integer.parseInt(txtTimeout.getText()));
				discover.execute();

			}

		});
		GridBagConstraints gbc_btnDiscover = new GridBagConstraints();
		gbc_btnDiscover.insets = new Insets(0, 0, 5, 0);
		gbc_btnDiscover.gridx = 2;
		gbc_btnDiscover.gridy = 1;
		frmXbeeZigbeeApi.getContentPane().add(btnDiscover, gbc_btnDiscover);

		lblDiscoveryTimeouts = new JLabel("Timeout (s):");
		GridBagConstraints gbc_lblDiscoveryTimeouts = new GridBagConstraints();
		gbc_lblDiscoveryTimeouts.anchor = GridBagConstraints.EAST;
		gbc_lblDiscoveryTimeouts.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscoveryTimeouts.gridx = 0;
		gbc_lblDiscoveryTimeouts.gridy = 2;
		frmXbeeZigbeeApi.getContentPane().add(lblDiscoveryTimeouts, gbc_lblDiscoveryTimeouts);

		btnClear = new JButton("Clear Output");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});

		txtTimeout = new JTextField();
		txtTimeout.setText("20");
		txtTimeout.setColumns(10);
		GridBagConstraints gbc_txtTimeout = new GridBagConstraints();
		gbc_txtTimeout.insets = new Insets(0, 0, 5, 5);
		gbc_txtTimeout.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTimeout.gridx = 1;
		gbc_txtTimeout.gridy = 2;
		frmXbeeZigbeeApi.getContentPane().add(txtTimeout, gbc_txtTimeout);
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 0);
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 2;
		frmXbeeZigbeeApi.getContentPane().add(btnClear, gbc_btnClear);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		frmXbeeZigbeeApi.getContentPane().add(scrollPane, gbc_scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
	}
}
