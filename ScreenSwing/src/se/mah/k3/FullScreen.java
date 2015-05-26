package se.mah.k3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class FullScreen extends JFrame implements KeyEventDispatcher,ActionListener, MouseListener, MouseMotionListener {


	public static DrawPanel panel;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean inFullScreenMode = false;
	private int PrevX = 100 ,PrevY = 100 ,PrevWidth = 480,PrevHeight = 640; //Dummysize
	
	/**
	 * Launch the application.
	 */
	static {
	    System.setProperty("sun.java2d.transaccel", "True");
	    // System.setProperty("sun.java2d.trace", "timestamp,log,count");
	    System.setProperty("sun.java2d.opengl", "True"); // GPU ACCEL
	    System.setProperty("sun.java2d.d3d", "True");
	    System.setProperty("sun.java2d.ddforcevram", "True");
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FullScreen frame = new FullScreen();
					frame.setVisible(true);
				    gameLoop(); // start the game loop
				    frame.setIconImage(ImageIO.read(new File("assets/icon/icontiny.png")));
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public FullScreen() {
		
		try{
		     Constants.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Roboto-Regular.ttf")).deriveFont(Constants.fontSize);
		     Constants.lightFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Roboto-Light.ttf")).deriveFont(Constants.fontSize);
		     Constants.boldFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Roboto-Bold.ttf")).deriveFont(Constants.fontSize);
			} catch (IOException e) {
			    e.printStackTrace();
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//DrawPanel panel = new DrawPanel();
		 panel = new DrawPanel();
	
		 //panel.addMouseMotionListener(this);

		//panel.setOpaque(false);
		//panel.setBackground( new Color(255, 0, 0, 20) );
		contentPane.add(panel, BorderLayout.CENTER);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager(); //Listen to keyboard
	    manager.addKeyEventDispatcher(this);
		setFullscreen(true);
			   //   DrawPanel canvas = new DrawPanel();
			        Thread t = new Thread(panel);
			        t.start();
		
	}
	  static void gameLoop() {
		
		  // panel.run();
		
		 
	  }
	
	public void setFullscreen(boolean fullscreen) {
		 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     GraphicsDevice[] gd = ge.getScreenDevices();    
			 if(fullscreen){
				PrevX = getX();
				PrevY = getY();
				PrevWidth = getWidth();
				PrevHeight = getHeight();
				dispose(); 
				//Always on last screen!
				setUndecorated(true);
				gd[gd.length-1].setFullScreenWindow(this);
				setVisible(true);
				this.inFullScreenMode = true;
			}
			else{
				setVisible(true);
				setBounds(PrevX, PrevY, PrevWidth, PrevHeight);
				dispose();
				setUndecorated(false);
				setVisible(true);
				this.inFullScreenMode = false;
			}
   }


	//Toggle fullscreen with f
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
       if (e.getID() == KeyEvent.KEY_TYPED) {
       	 if(e.getKeyChar()=='f' || e.getKeyChar()=='F'){     		 
             	setFullscreen(!inFullScreenMode);	
     		}
	       	 if(e.getKeyChar()=='#' ){     		 
	       		if(Constants.debug){
	       			Constants.debug=false;
	       		}else{
	       			Constants.debug=true;
	       		}
	  		}
        }
        return false;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(" x: "+getX() +"   y: " +getY());
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println(" hej");
	}



	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println(" hej");
	}



	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(" pressed "+e.getButton());
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println(" released");
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println("dragged"+" x: "+arg0.getX() +"   y: " +arg0.getY());
	}



	@Override
	public void mouseMoved(MouseEvent arg0) {
		System.out.println(" move"+" x: "+arg0.getX() +"   y: " +arg0.getY());
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(" action");
	}
	
	
}
