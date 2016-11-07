package com.furiapolitehnicii.loggingserver.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.furiapolitehnicii.constants.Constants;
import com.furiapolitehnicii.loggingserver.server.Configuration;
import com.furiapolitehnicii.loggingserver.server.Criteria;
import com.furiapolitehnicii.loggingserver.server.Server;

public class ServerCanvas extends JFrame {
	/**
	 * TODO check for clients duplicate etc TODO criteria for sort logs by
	 * Client name / by severity check box TODO block other fields till config
	 * is valid one
	 */

	private static final long serialVersionUID = 1L;
	private String serverName;
	private Server server;
	private Configuration config;
	private boolean isStartUnlocked;

	private Container containerPanel;
	private JButton bStart, bExit, bLogin, bCheckConfig;
	private JPanel panelServer;
	private JPanel panelClient;
	private JPanel panelConfig;
	private JLabel labelConfig;
	private JTextField textFieldSourcePath, textFieldLoggingPath;
	private JTextField textFieldNoOfThreads, textFieldLogSize,
			textFieldNoOfRotations;
	private JRadioButton radioButtonClientStrategy, radioButtonSeverityStrategy,
			radioButtonDefaultStrategy;
	private JOptionPane optionPaneAlert;

	public ServerCanvas(String serverName) {

		this.isStartUnlocked = true;
		this.serverName = serverName;
		this.containerPanel = getContentPane();
		optionPaneAlert = new JOptionPane();

		containerPanel.setLayout(new GridLayout(0, 1));

		setTitle("Server");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		initServerPanel();
		initClientPanel();
		initConfigPanel();
		setPanels();

	}
	private void loadDefaultConfiguration() {
		radioButtonClientStrategy.setSelected(true);
		radioButtonSeverityStrategy.setSelected(false);
		textFieldLoggingPath.setText("src/out");
		textFieldLogSize.setText("1000");
		textFieldNoOfRotations.setText("2");
		textFieldNoOfThreads.setText("10");
		textFieldSourcePath.setText("src/Logs");
	}

	private void initServerPanel() {
		panelServer = new JPanel();

		bStart = new JButton("Start");
		bStart.setEnabled(false);
		bStart.setBackground(Color.green);
		bStart.addActionListener(new StartBehavior());

		bExit = new JButton("Exit");
		bExit.setBackground(Color.red);
		bExit.addActionListener(new ExitBehavior());

		panelServer.add(bStart);
		panelServer.add(bExit);

	}
	private void initClientPanel() {
		panelClient = new JPanel();

		textFieldSourcePath = new JTextField(10);

		bLogin = new JButton("Login");
		bLogin.setEnabled(false);
		bLogin.addActionListener(new LoginBehavior());

		panelClient.add(new JLabel("Client path directory:"));
		panelClient.add(textFieldSourcePath);
		panelClient.add(bLogin);
	}
	private void initConfigPanel() {
		panelConfig = new JPanel();

		bCheckConfig = new JButton("Check");
		bCheckConfig.addActionListener(new CheckConfigListener());

		labelConfig = new JLabel("====Configuration====");

		radioButtonClientStrategy = new JRadioButton("Client");
		radioButtonSeverityStrategy = new JRadioButton("Severity");
		radioButtonDefaultStrategy = new JRadioButton("Default");
		radioButtonClientStrategy.setBackground(Color.red);
		radioButtonSeverityStrategy.setBackground(Color.yellow);
		radioButtonDefaultStrategy.setBackground(Color.blue);

		radioButtonClientStrategy.setActionCommand(Constants.CLIENT_CMD);
		radioButtonSeverityStrategy.setActionCommand(Constants.SEVERITY_CMD);
		radioButtonDefaultStrategy.setActionCommand(Constants.DEFAULT_CMD);

		ButtonGroup group = new ButtonGroup();
		group.add(radioButtonClientStrategy);
		group.add(radioButtonSeverityStrategy);
		group.add(radioButtonDefaultStrategy);

		Box box = Box.createVerticalBox();
		box.setVisible(true);
		box.add(labelConfig);
		box.add(new JLabel("Logging criteria"));

		Box radio = Box.createHorizontalBox();
		radio.setVisible(true);
		radio.add(radioButtonClientStrategy);
		radio.add(radioButtonSeverityStrategy);
		radio.add(radioButtonDefaultStrategy);

		box.add(radio);
		box.add(new JLabel("No of rotation"));
		box.add(textFieldNoOfRotations = new JTextField());
		textFieldNoOfRotations.addMouseListener(new MouseLockAdapter());

		box.add(new JLabel("Log size"));
		box.add(textFieldLogSize = new JTextField());
		textFieldLogSize.addMouseListener(new MouseLockAdapter());

		box.add(new JLabel("No of threads per client"));
		box.add(textFieldNoOfThreads = new JTextField());
		textFieldNoOfThreads.addMouseListener(new MouseLockAdapter());

		box.add(new JLabel("Insert Log Path"));
		box.add(textFieldLoggingPath = new JTextField());
		textFieldLoggingPath.addMouseListener(new MouseLockAdapter());

		box.add(bCheckConfig);

		panelConfig.add(box);
		loadDefaultConfiguration();
	}
	private void setPanels() {
		Box box = Box.createVerticalBox();
		box.add(panelServer);
		box.add(panelClient);
		box.add(panelConfig);
		containerPanel.add(box);
	}
	private Configuration getConfig() {
		int logSize = 1024;
		int noOfRotations = 3;
		int noOfLoggingThreads = 10;
		String logPath = "src/out";
		isStartUnlocked = true;

		String logSize_str = textFieldLogSize.getText();
		boolean logSize_b = logSize_str.matches(Constants.FIVE_DIGIT_PATTERN);
		if (logSize_b) {
			logSize = Integer.parseInt(logSize_str);
		} else {
			JOptionPane.showMessageDialog(optionPaneAlert,
					"Invalid log size please try again", "Inane warning",
					JOptionPane.ERROR_MESSAGE);
			textFieldLogSize.setText("");
			bStart.setEnabled(false);
			isStartUnlocked = false;
		}

		String noOfRotations_str = textFieldNoOfRotations.getText();
		boolean noOfRotations_b = noOfRotations_str
				.matches(Constants.THREE_DIGIT_PATTERN);
		if (noOfRotations_b) {
			noOfRotations = Integer.parseInt(noOfRotations_str);
		} else {
			JOptionPane.showMessageDialog(optionPaneAlert,
					"Invalid no of rotations please try again", "Inane warning",
					JOptionPane.ERROR_MESSAGE);
			textFieldNoOfRotations.setText("");
			bStart.setEnabled(false);
			isStartUnlocked = false;
		}

		String noOfThreads_str = textFieldNoOfThreads.getText();
		boolean noOfThreads_b = noOfThreads_str
				.matches(Constants.THREE_DIGIT_PATTERN);
		if (noOfThreads_b) {
			noOfLoggingThreads = Integer.parseInt(noOfThreads_str);
		} else {
			JOptionPane.showMessageDialog(optionPaneAlert,
					"Invalid no of threads please try again", "Inane warning",
					JOptionPane.ERROR_MESSAGE);
			textFieldNoOfThreads.setText("");
			bStart.setEnabled(false);
			isStartUnlocked = false;
		}

		String extractedlogPath = textFieldLoggingPath.getText();
		boolean tfLogPath_b = extractedlogPath.matches(Constants.PATH_PATERN);
		if (tfLogPath_b)
			logPath = extractedlogPath;
		else {
			JOptionPane.showMessageDialog(optionPaneAlert,
					"Invalid path default please try again", "Inane warning",
					JOptionPane.ERROR_MESSAGE);
			textFieldLoggingPath.setText("");
			bStart.setEnabled(false);
			isStartUnlocked = false;
		}
		Criteria criteria;
		if (radioButtonClientStrategy.isSelected()) {
			criteria = Criteria.CLIENT;
		} else if (radioButtonSeverityStrategy.isSelected()) {
			criteria = Criteria.SEVERITY;
		} else
			criteria = Criteria.DEFAULT;
		return new Configuration(serverName, logSize, noOfRotations,
				noOfLoggingThreads, logPath, criteria);
	}
	/**
	 *
	 * @author xander
	 * @start server threads
	 */
	private class StartBehavior implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			panelServer.setBackground(Color.CYAN);
			server = new Server(serverName, config);
			server.startServer();
			panelServer.setBackground(Color.BLUE);
			panelServer.repaint();
			bLogin.setEnabled(true);
			bStart.setEnabled(false);
			bCheckConfig.setEnabled(false);

		}

	}
	private class ExitBehavior implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);

		}

	}
	private class CheckConfigListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			config = getConfig();
			if (isStartUnlocked) {
				bStart.setEnabled(isStartUnlocked);
			} else
				bStart.setEnabled(isStartUnlocked);
		}

	}
	private class LoginBehavior implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String inputPath = textFieldSourcePath.getText();
			if (inputPath.matches(Constants.PATH_PATERN)) {
				server.startClients(inputPath);
			} else {
				server.startClients("src/Logs");
				JOptionPane.showMessageDialog(optionPaneAlert,
						"Default input considered for input path",
						"Inane warning", JOptionPane.WARNING_MESSAGE);
			}
			panelServer.setBackground(Color.GREEN);
			panelServer.repaint();
		}

	}
	private class MouseLockAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			bStart.setEnabled(false);
		}
	}
}
