import java.awt.*;

import javax.swing.*;

public class Driver extends JFrame{
	private menu menu;
	private Statbar stat;
	private Tools tools;
	private JPanel panel;
	public static PComponent pc;
	public static Dimension d;
	
	
	public Driver(){
		stat= new Statbar();
		pc= new PComponent(this);
		tools= new Tools(stat);
		menu= new menu(this, stat);
		d= new Dimension();
		//change layout dynamically
		panel= new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(800,700));
		this.getContentPane().add(panel);
		panel.setVisible(true);
		panel.add(menu, BorderLayout.NORTH);
		panel.add(stat, BorderLayout.SOUTH);
		panel.add(tools, BorderLayout.WEST);
		panel.add(pc, BorderLayout.CENTER);
		this.setVisible(true);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		System.out.println(d);
	}
	
	public static void main (String[] args){
		Driver d= new Driver();
	}
}