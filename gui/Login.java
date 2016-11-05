package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.ClientThread;
import jobs.ReadingJob;

public class Login extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel result;
	private JTextField name, path;
	protected class WindowHandler extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
	 public Login() {
		 setTitle("Login");
		 addWindowListener(new WindowHandler());
		 
		 Container cp = getContentPane();
		 
		 cp.setLayout(new GridLayout(6,1));
		 cp.add(new JLabel("Client id:"));
		 name = new JTextField("",5);

		 cp.add(name);
		 
		 cp.add(new JLabel("Path:"));
		 path = new JTextField("",5);
		 cp.add(path);
		 JButton login = new JButton("Login");
		 cp.add(login);
		 login.addActionListener(this);
		 
		 result = new JLabel("*******************************");
		 cp.add(result);

		 
	 }
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String client_id = name.getText();
		String client_path = path.getText();
		result.setText("Hello, "+client_id+":"+client_path);
		
		
	}

}
