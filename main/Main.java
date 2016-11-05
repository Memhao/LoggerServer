package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Client;
import gui.Buttons;
import gui.Login;
import gui.ServerCanvas;
import server.Configuration;
import server.Server;

public class Main {
	public static void main(String agb[])
	{
//		System.out.println("HJ");
//		LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
//		LineBufferResource buffer = new LineBufferResource(queue);
//		
//		ReadingJob job_a = new ReadingJob("src/file.txt","000" ,buffer);
//		ReadingJob job_b = new ReadingJob("src/flie.txt","001" ,buffer);
//		ReadingJob job_c = new ReadingJob("src/fiel.txt","010" ,buffer);
//		
//		ILoggingStrategy rot = new NameLoggingStrategy(buffer,"src/out",1000,3,3);
//		LoggingJob logingJob = new LoggingJob(rot);
//		
//		
//		ClientThread cl0 = new ClientThread("000", "xxx", job_a);
//		ClientThread cl1 = new ClientThread("001", "yyy", job_b);
//		ClientThread cl2 = new ClientThread("010", "zzz", job_c);
//		LoggingThread lg0 = new LoggingThread(logingJob);
//		LoggingThread lg1 = new LoggingThread(logingJob);
//		Thread _cl0 = new Thread(cl0);
//		Thread _cl1 = new Thread(cl1);
//		Thread _cl2 = new Thread(cl2);
//		Thread _lg1 = new Thread(lg0);
//		Thread _lg2 = new Thread(lg1);
//		
//		
//		_cl0.start();
//		_cl1.start();
//		_cl2.start();
//		_lg1.start();
//		_lg2.start();
		Server srv = new Server("", new Configuration("", 1, 2, 2));
		srv.subscribeClient(new Client("199.123.111", "src/file.txt", "cl1"));
		srv.subscribeClient(new Client("199.123.112", "src/flie.txt", "cl2"));
		srv.subscribeClient(new Client("199.123.113", "src/fiel.txt", "cl3"));
		
//		JFrame window = new JFrame();
//		window.setSize(640, 480);
//		window.setTitle("Server");
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setVisible(true);
//		Button bStart = new Button("START");
//		Button bExit = new Button("EXIT");
//		bStart.setSize(100, 100);
//		bStart.setLocation(250, 250);
//		bExit.setSize(100, 100);
//		bExit.setLocation(0, 0);
//		bStart.setBackground(Color.BLUE);
//		bExit.setBackground(Color.red);
//		bStart.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				srv.start();
//			}
//		});
//		bExit.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				System.exit(1);
//			}
//		});
//		window.add(bStart);
//		window.add(bExit);
		
//		JFrame window = new  Buttons(srv);
//		window.setSize(640, 480);
//		window.setVisible(true);
//
		JFrame jf = new ServerCanvas("");
		jf.setSize(640, 200);
		jf.setVisible(true);
		
	}
}
