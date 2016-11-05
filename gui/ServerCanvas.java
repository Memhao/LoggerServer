package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
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
import server.Configuration;
import server.LoggingThread;


public class ServerCanvas extends JFrame {
	/**
	 * TODO check for clients duplicate etc
	 * TODO criteria for sort logs by Client name / by severity check box 
	 * TODO block other fields till config is valid one
	 */
	private static final long serialVersionUID = 1L;
	private String serverName;
	private Configuration config;
	private  UniqResource resource;



	private JButton bStart, bExit,bLogin;
	private JPanel panelServer;
	private JPanel panelClient;
	private JPanel panelConfig;
	private JLabel lbConfig;
	private JTextField tfIDClient,tfPath;
	private JTextField tfNoOfThreads,tfLogSize,tfNoOfRot;


	public ServerCanvas(String serverName)
	{
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(0,1));
		this.serverName = serverName;
		resource = UniqResource.getInstance();
		lbConfig = new JLabel("Configuration");

		setTitle("Server");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		panelServer = new JPanel();
		panelClient = new JPanel();
		panelConfig = new JPanel();

		Behave behave = new Behave();
		/**
		 * SERVER Panel
		 */
		bStart = new JButton("Start");
		bStart.setBackground(Color.green);
		panelServer.add(bStart);
		bStart.addActionListener(behave);

		bExit = new JButton("Exit");
		bExit.setBackground(Color.red);
		panelServer.add(bExit);
		bExit.addActionListener(behave);

		/**
		 * CLIENT Panel
		 */
		panelClient.add(new JLabel("Client id:"));
		tfIDClient = new JTextField(10);
		panelClient.add(tfIDClient);
		tfPath = new JTextField(10);
		panelClient.add(new JLabel("Client path file:"));
		panelClient.add(tfPath);
		bLogin = new JButton("Login");
		bLogin.addActionListener(behave);
		panelClient.add(bLogin);

		/**
		 * CONFIG PANEL
		 */
		panelConfig.add(lbConfig);
		panelConfig.add(new JLabel("No of rotation"));
		panelConfig.add(tfNoOfRot = new JTextField(5));
		panelConfig.add(new JLabel("Log size"));
		panelConfig.add(tfLogSize = new JTextField(5));
		panelConfig.add(new JLabel("No of threads per client"));
		panelConfig.add(tfNoOfThreads = new JTextField(5));
		
		
		cp.add(panelServer);
		cp.add(panelClient);
		cp.add(panelConfig);
	}
	class Behave implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Object ob = event.getSource();
			if(ob == bStart)
			{
				panelServer.setBackground(Color.CYAN);
				//read config and init server
				 int logSize = Integer.parseInt(tfLogSize.getText());
				 int noOfRotations =  Integer.parseInt(tfNoOfRot.getText());
				 int noOfThreadsPerClient =  Integer.parseInt(tfNoOfThreads.getText());
				 
				 ILoggingStrategy logstr = new NameLoggingStrategy(resource, "src/out", logSize, noOfRotations,noOfThreadsPerClient);
				 Job  job= new LoggingJob(logstr);
				 for(int i = 0; i < noOfThreadsPerClient; i++)
				 {
					 new Thread(new LoggingThread(job)).start();
				 }
			}else if(ob == bExit)
			{
				System.exit(0);
			}else if(ob == bLogin)
			{
				String client_id = tfIDClient.getText();
				String client_path = tfPath.getText();
				Client c = new Client(client_id ,client_path, "xx");
				new Thread(new ClientThread(c.getAddress(), c.getLoggingFileName(), new ReadingJob(c.getPath(), c.getAddress(), resource))).start();	
				panelServer.setBackground(Color.GREEN);
			}
			panelServer.repaint();
		}
	}
}
