import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.plaf.basic.BasicArrowButton;

public class Tools extends JPanel{
	private String message;
	private JToggleButton sigEp;
	private JToggleButton phiMu;
	private JToggleButton family;
	private JToggleButton random;
	private BasicArrowButton left;
	private BasicArrowButton right;
	
	
	public Tools (final Statbar stat){	
		sigEp= new JToggleButton("SigEp");
		phiMu= new JToggleButton("Phi Mu");
		family= new JToggleButton("Family");
		random= new JToggleButton("Random");
		left= new BasicArrowButton(BasicArrowButton.WEST);
		right= new BasicArrowButton(BasicArrowButton.EAST);
		
		this.add(left);
		this.add(right);
		this.add(sigEp);
		this.add(phiMu);
		this.add(family);
		this.add(random);
		
		 sigEp.addItemListener(new ItemListener() {
		 	public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					stat.setText("This button has been selected");
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED){
					stat.setText("This button has been deselected.");
				}
		 	}
		 });
		 
		 
		 phiMu.addItemListener(new ItemListener() {
			 	public void itemStateChanged (ItemEvent e){
					if (e.getStateChange() == ItemEvent.SELECTED){
						stat.setText("This button has been selected");
					}
					else if (e.getStateChange() == ItemEvent.DESELECTED){
						stat.setText("This button has been deselected.");
					}
			 	}
			 });
		 
		 family.addItemListener(new ItemListener() {
			 	public void itemStateChanged (ItemEvent e){
					if (e.getStateChange() == ItemEvent.SELECTED){
						stat.setText("This button has been selected");
					}
					else if (e.getStateChange() == ItemEvent.DESELECTED){
						stat.setText("This button has been deselected.");
					}
			 	}
			 });
		 
		 random.addItemListener(new ItemListener() {
			 	public void itemStateChanged (ItemEvent e){
					if (e.getStateChange() == ItemEvent.SELECTED){
						stat.setText("This button has been selected");
					}
					else if (e.getStateChange() == ItemEvent.DESELECTED){
						stat.setText("This button has been deselected.");
					}
			 	}
			 });
	
	left.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			stat.setText("you went back");
		}
	});
	
	right.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			stat.setText("you went forward");
		}
	});
	}	
}