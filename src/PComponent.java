import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;


public class PComponent extends JPanel implements MouseListener,MouseMotionListener {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage im;
	
	private JScrollPane scroll;
	
	private boolean isFlipped;
	
	//for text
	private String str;
	private boolean texty;
	private int textBoxX;
	private int textBoxY;
	private char letter;
	private ArrayList<String> notes = new ArrayList<String>();
	private List<Integer> notesX = new ArrayList<Integer>();
	private List<Integer> notesY = new ArrayList<Integer>();

	
	//for drawing
	private ArrayList<Point> pts = new ArrayList<Point>();

	public static int imW, imH;
	
	
	public PComponent(final JFrame frame) {
		imW = 100;
		imH = 100;
		scroll = new JScrollPane();
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setViewportView(this);
	    scroll.getViewport().setBackground(Color.LIGHT_GRAY);
	    isFlipped = false;
		texty = false;
		this.setBackground(Color.ORANGE);
	    frame.getContentPane().add(BorderLayout.CENTER,scroll);
	    addMouseListener(this);
	    addMouseMotionListener(this);
	    setFocusable(true);
	    addKeyListener(new KeyboardControls(texty));
	    
	}
	
	public void drawWrapString(Graphics g, String s, int textBoxX, int textBoxY, int imW, int imH){
		FontMetrics fm= g.getFontMetrics();
		int lineHeight = fm.getHeight();
		int x= textBoxX;
		int y= textBoxY;
		
		String[] words = s.split(" ");
		
		for (String w:words){
			//find the width of the word
			int wordW= fm.stringWidth(w + " ");
			
		//if text exceeds width
			if (x+wordW >= x+imW){
				if (lineHeight+1 < imH){
					y+=lineHeight;
					x=textBoxX;
				}
			}
			else {
				break;
			}
			g.drawString(w, x, y);
			
			x+= wordW;
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(im != null && !isFlipped) {
			System.out.println("Yes");
			setPreferredSize(new Dimension(imW,imH));
			g.drawImage(im,0,0,null);
			scroll.setViewportView(this);
		}
		else if(im != null && isFlipped) {
			g.setColor(Color.WHITE);
            g.fillRect(0,0,imW,imH);
            
       if (!notes.isEmpty()){
    	   for (int i = 0; i < notes.size(); i++) {
    		   drawWrapString(g,notes.get(i),notesX.get(i),notesY.get(i),imW,imH);
       		}
       }
            
        if(texty && str!=null) {
        	g.setColor(Color.BLUE);
        	String s = "" + letter;
            drawWrapString(g, s, textBoxX, textBoxY, imW, imH);
        }
        
        for (int i = 0; i < pts.size() - 2; i++) {
        	Point p1 = pts.get(i);
            Point p2 = pts.get(i+1);

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            //************NEEEDDS TO BE CHANGES FOR MULT COLORS
            g2.setColor(Color.BLACK);
            g2.drawLine(p1.x,p1.y,p2.x,p2.y);
        }
		}
	}
		@Override
		public void mouseClicked(MouseEvent ev) {
			//if not flipped
			if(im != null && !isFlipped && ev.getClickCount() == 2 && ev.getX() <= imW 
					&& ev.getY() <= imH) {
				System.out.println("IN!");
				isFlipped = true;
				texty = false;
				repaint();
			}
			
			//already flipped
			else if(im != null && isFlipped && ev.getClickCount() == 2 && ev.getX() <= imW 
					&& ev.getY() <= imH) {
				
				isFlipped = false;
				texty = false;
				repaint();
			}
			
			//flipped and want to add text
			else if(im != null && isFlipped && ev.getClickCount() == 1 && ev.getX() <= imW
					&& ev.getY() <= imH) {
				if (str!=null){
					notes.add(str);
					notesX.add(textBoxX);
					notesY.add(textBoxY);
					str = null;
				}
				texty = true;
				textBoxX = ev.getX();
				textBoxY = ev.getY();
				System.out.println(texty);
				repaint();
			}
	    }
		
		

		public void mouseEntered(MouseEvent ev) {
		}
	 
	    public void mouseExited(MouseEvent ev) {
	    }

	    public void mousePressed(MouseEvent ev) {
	    }
	    
	    public void mouseDragged(MouseEvent ev) {
	    	if(im != null && isFlipped && ev.getX() <= imW
	    			&& ev.getY() <= imH) {
	    		pts.add(ev.getPoint());
	    	    repaint();
	    	}
	    }
	    
	    public void mouseReleased(MouseEvent ev) {
	    }
	    
	    public void mouseMoved(MouseEvent ev) {
		}
		
		public void setImage(BufferedImage im) {
			this.im = im;
			System.out.println("paint");
			imW = im.getWidth();
			imH = im.getHeight();
			pts.clear();
			notes.clear();
			notesX.clear();
			notesY.clear();
//			Driver.d.setSize(imW, imH);
//			Driver.p.setPreferredSize(Driver.d);
			repaint();
		}

		private class KeyboardControls implements KeyListener {
		
			private boolean texty;
		
			public KeyboardControls(boolean texty) {
				this.texty = texty;
			}

			public void keyPressed(KeyEvent ev) {
				System.out.println("pshh");
			}

			public void keyReleased(KeyEvent ev) {
			}

			public void keyTyped(KeyEvent ev) {
				if(texty) {
					char letter = ev.getKeyChar();
					if(str == null) {
						str = "" + letter;
					}
					else {
						str = str+letter;
					}
					repaint();
				}
			}
		}

		
}



