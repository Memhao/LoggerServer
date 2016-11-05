package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import server.Server;

public class Buttons extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton bStart, bExit,bLogin;
	private JPanel panel;
	
	private Server server;
	public Buttons(Server server)
	{
		this.server = server;
		setTitle("Server");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		panel = new JPanel();
		
		Behave behave = new Behave();
		bStart = new JButton("Start");
		bStart.setBackground(Color.green);
		panel.add(bStart);
		bStart.addActionListener(behave);
		
		bExit = new JButton("Exit");
		bExit.setBackground(Color.red);
		panel.add(bExit);
		bExit.addActionListener(behave);
		
		bLogin = new JButton("Login");
		bLogin.setBackground(Color.yellow);
		panel.add(bLogin);
		bLogin.addActionListener(behave);
		
		getContentPane().add(panel);
	}
	
	class Behave implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Object ob = event.getSource();
			if(ob == bStart)
			{
				panel.setBackground(Color.CYAN);
				server.start();
			}else if(ob == bExit)
			{
				System.exit(0);
			}else if(ob == bLogin)
			{
				panel.setBackground(Color.GREEN);
			}
			panel.repaint();
		}
	}
}
