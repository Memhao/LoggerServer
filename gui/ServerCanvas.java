package gui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Client;
import client.ClientThread;
import jobs.Job;
import jobs.LoggingJob;
import jobs.ReadingJob;
import logging.ILoggingStrategy;
import logging.NameLoggingStrategy;
import resource.UniqResource;
import server.ClientServer;
import server.Configuration;
import server.Criteria;
import server.LoggingThread;


public class ServerCanvas extends JFrame {
	/**
	 * TODO check for clients duplicate etc
	 * TODO criteria for sort logs by Client name / by severity check box 
	 * TODO block other fields till config is valid one
	 */
	private static final long serialVersionUID = 1L;
	private String serverName;
	private  ClientServer server;


	private Container cp;
	private JButton bStart, bExit,bLogin;
	private JPanel panelServer;
	private JPanel panelClient;
	private JPanel panelConfig;
	private JLabel lbConfig;
	private JTextField tfIDClient,tfPath;
	//config
	private JTextField tfNoOfThreads,tfLogSize,tfNoOfRot, tfLogPath;

	private JCheckBox cbClient, cbSeverity;
	
	public ServerCanvas(String serverName)
	{

		this.serverName = serverName;
		this.cp = getContentPane();
		cp.setLayout(new GridLayout(0,1));
		
		setTitle("Server");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		initServerPanel();
		initClientPanel();
		initConfigPanel();
		setPanels();

	}


	private void initServerPanel()
	{
		panelServer = new JPanel();
		bStart = new JButton("Start");
		bStart.setBackground(Color.green);
		panelServer.add(bStart);
		bStart.addActionListener(new StartBehavior());

		bExit = new JButton("Exit");
		bExit.setBackground(Color.red);
		panelServer.add(bExit);
		bExit.addActionListener(new ExitBehavior());
	}
	private void initClientPanel()
	{
		panelClient = new JPanel();
		panelClient.add(new JLabel("Client id:"));
		tfIDClient = new JTextField(10);
		panelClient.add(tfIDClient);
		tfPath = new JTextField(10);
		panelClient.add(new JLabel("Client path file:"));
		panelClient.add(tfPath);
		bLogin = new JButton("Login");
		bLogin.addActionListener(new LoginBehavior());
		panelClient.add(bLogin);
	}
	private void initConfigPanel()
	{
		panelConfig = new JPanel();
		lbConfig = new JLabel("Configuration");
		cbClient = new JCheckBox("Client");
		cbSeverity = new JCheckBox("Severity");
		cbClient.setBackground(Color.white);
		cbSeverity.setBackground(Color.white);
		cbClient.addItemListener(new ClientSeveritySelected());
		cbSeverity.addItemListener(new ClientSeveritySelected());
		
		cp.add(cbClient);
		cp.add(cbSeverity);
		
		panelConfig.add(lbConfig);
		panelConfig.add(new JLabel("No of rotation"));
		panelConfig.add(tfNoOfRot = new JTextField(5));
		panelConfig.add(new JLabel("Log size"));
		panelConfig.add(tfLogSize = new JTextField(5));
		panelConfig.add(new JLabel("No of threads per client"));
		panelConfig.add(tfNoOfThreads = new JTextField(5));
		panelConfig.add(new JLabel("Insert Log Path"));
		panelConfig.add(tfLogPath = new JTextField(10));
	}
	private void setPanels()
	{
		cp.add(panelServer);
		cp.add(panelClient);
		cp.add(panelConfig);
	}
	/**
	 * 
	 * @author xander
	 * @start server threads
	 */
	private class StartBehavior implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			panelServer.setBackground(Color.CYAN);
			int logSize = Integer.parseInt(tfLogSize.getText());
			int noOfRotations =  Integer.parseInt(tfNoOfRot.getText());
			int noOfLoggingThreads =  Integer.parseInt(tfNoOfThreads.getText());
			String logPath = tfLogPath.getText();
			Criteria criteria;
			if(cbClient.isSelected())
			{
				criteria = Criteria.CLIENT;
			}else
				criteria = Criteria.SEVERITY;
			
			Configuration config = new Configuration("", logSize, noOfRotations, noOfLoggingThreads, logPath, criteria);
			server = new ClientServer("Logging Server", config);
			server.startServer();
			panelServer.setBackground(Color.BLUE);
			panelServer.repaint();
		}

	}
	private class ExitBehavior implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);

		}

	}
	private class LoginBehavior implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String client_id = tfIDClient.getText();
			String client_path = tfPath.getText();
			Client c = new Client(client_id ,client_path, "xx");
			server.startClient(c);
			panelServer.setBackground(Color.GREEN);
			panelServer.repaint();
		}

	}
	private class ClientSeveritySelected implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			if (cbClient.isSelected() && cbSeverity.isSelected() || !cbSeverity.isSelected() && !cbClient.isSelected())
			{
					//TODO
			}			
			else if (cbClient.isSelected())
			{
				cbSeverity.setSelected(false);
			}else if(cbSeverity.isSelected())
			{
				cbClient.setSelected(false);
			}
				
		}
		
	}

}
