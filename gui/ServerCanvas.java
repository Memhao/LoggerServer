package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
import jobs.ReadingJob;
import resource.UniqResource;
import server.Configuration;


public class ServerCanvas extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serverName;
	private Configuration config;
	private  UniqResource resource;
	
	
	
	private JButton bStart, bExit,bLogin;
	private JPanel panelServer;
	private JPanel panelClient;
	private JLabel lbConfig;
	private JTextField tfIDClient,tfPath;

	public ServerCanvas(String serverName)
	{
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
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
		Behave behave = new Behave();
		bStart = new JButton("Start");
		bStart.setBackground(Color.green);
		panelServer.add(bStart);
		bStart.addActionListener(behave);
		
		bExit = new JButton("Exit");
		bExit.setBackground(Color.red);
		panelServer.add(bExit);
		bExit.addActionListener(behave);
		
//		bLogin = new JButton("Login");
//		bLogin.setBackground(Color.yellow);

		
		
		
		 panelClient.add(new JLabel("Client id:"));
		 tfIDClient = new JTextField(10);
		 panelClient.add(tfIDClient);
		 tfPath = new JTextField(10);
		 panelClient.add(new JLabel("Client path file:"));
		 panelClient.add(tfPath);
		 bLogin = new JButton("Login");
		 bLogin.addActionListener(behave);
		 panelClient.add(bLogin);
		 
		 
		getContentPane().add(panelServer);
		getContentPane().add(panelClient);
	}
	class Behave implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Object ob = event.getSource();
			if(ob == bStart)
			{
				panelServer.setBackground(Color.CYAN);
				
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
