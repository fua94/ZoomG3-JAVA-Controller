package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import MainController.BackgroundProcess;
import MainController.Global;
import MainController.MainController;

import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;

public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_main;
	private JTextField tf_fs1;
	private JTextField tf_fs2;
	private JTextField tf_fs3;
	private JTextField tf_fs4;
	private JTextField tf_fs5;
	private JTextField tf_fs6;
	
	private MainController mc = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("Zoom G3 Controller");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf_main = new JTextField();
		tf_main.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mc.holdAssign(0);
			}
		});
		tf_main.setHorizontalAlignment(SwingConstants.CENTER);
		tf_main.setBackground(new Color(152, 251, 152));
		tf_main.setFont(new Font("Courier New", Font.BOLD, 25));
		tf_main.setEditable(false);
		tf_main.setBounds(10, 29, 234, 74);
		contentPane.add(tf_main);
		tf_main.setColumns(16);
		
		tf_fs1 = new JTextField();
		tf_fs1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mc.holdAssign(1);
			}
		});
		tf_fs1.setHorizontalAlignment(SwingConstants.CENTER);
		tf_fs1.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_fs1.setEditable(false);
		tf_fs1.setBounds(130, 200, 114, 23);
		contentPane.add(tf_fs1);
		tf_fs1.setColumns(10);
		
		tf_fs2 = new JTextField();
		tf_fs2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mc.holdAssign(2);
			}
		});
		tf_fs2.setHorizontalAlignment(SwingConstants.CENTER);
		tf_fs2.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_fs2.setEditable(false);
		tf_fs2.setColumns(10);
		tf_fs2.setBounds(293, 200, 114, 23);
		contentPane.add(tf_fs2);
		
		tf_fs3 = new JTextField();
		tf_fs3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mc.holdAssign(3);
			}
		});
		tf_fs3.setHorizontalAlignment(SwingConstants.CENTER);
		tf_fs3.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_fs3.setEditable(false);
		tf_fs3.setColumns(10);
		tf_fs3.setBounds(450, 200, 114, 23);
		contentPane.add(tf_fs3);
		
		tf_fs4 = new JTextField();
		tf_fs4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mc.holdAssign(4);
			}
		});
		tf_fs4.setHorizontalAlignment(SwingConstants.CENTER);
		tf_fs4.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_fs4.setEditable(false);
		tf_fs4.setColumns(10);
		tf_fs4.setBounds(130, 277, 114, 23);
		contentPane.add(tf_fs4);
		
		tf_fs5 = new JTextField();
		tf_fs5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mc.holdAssign(5);
			}
		});
		tf_fs5.setHorizontalAlignment(SwingConstants.CENTER);
		tf_fs5.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_fs5.setEditable(false);
		tf_fs5.setColumns(10);
		tf_fs5.setBounds(293, 277, 114, 23);
		contentPane.add(tf_fs5);
		
		tf_fs6 = new JTextField();
		tf_fs6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mc.holdAssign(6);
			}
		});
		tf_fs6.setHorizontalAlignment(SwingConstants.CENTER);
		tf_fs6.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_fs6.setEditable(false);
		tf_fs6.setColumns(10);
		tf_fs6.setBounds(450, 277, 114, 23);
		contentPane.add(tf_fs6);
		
		JButton btnArriba = new JButton("UP");
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.normalAssign('U');
			}
		});
		btnArriba.setBounds(32, 217, 74, 23);
		contentPane.add(btnArriba);
		
		JButton btnAbajo = new JButton("DOWN");
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.normalAssign('D');
			}
		});
		btnAbajo.setBounds(32, 294, 74, 23);
		contentPane.add(btnAbajo);
		
		JButton b_1 = new JButton("1");
		b_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.normalAssign(1);
			}
		});
		b_1.setBounds(162, 234, 56, 23);
		contentPane.add(b_1);
		
		JButton b_2 = new JButton("2");
		b_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.normalAssign(2);
			}
		});
		b_2.setBounds(323, 234, 56, 23);
		contentPane.add(b_2);
		
		JButton b_3 = new JButton("3");
		b_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.normalAssign(3);
			}
		});
		b_3.setBounds(480, 234, 56, 23);
		contentPane.add(b_3);
		
		JButton b_4 = new JButton("4");
		b_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.normalAssign(4);
			}
		});
		b_4.setBounds(162, 311, 56, 23);
		contentPane.add(b_4);
		
		JButton b_5 = new JButton("5");
		b_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.normalAssign(5);
			}
		});
		b_5.setBounds(323, 311, 56, 23);
		contentPane.add(b_5);
		
		JButton b_6 = new JButton("6");
		b_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.normalAssign(6);
			}
		});
		b_6.setBounds(480, 311, 56, 23);
		contentPane.add(b_6);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 633, 21);
		contentPane.add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem goToPatch = new JMenuItem("Go to patch");
		goToPatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.getPedal().goToPatch();
			}
		});
		
		JMenuItem newPreset = new JMenuItem("New Preset");
		newPreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.getPC().newPreset();
			}
		});
		
		JMenuItem savePreset = new JMenuItem("Save Preset");
		savePreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.getPC().savePreset();
			}
		});
		
		JMenuItem editPreset = new JMenuItem("Edit Preset");
		editPreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.getPC().editPreset();
			}
		});
		
		JMenuItem deletePreset = new JMenuItem("Delete Preset");
		deletePreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.getPC().deletePreset();
			}
		});
		
		JMenuItem goToPreset = new JMenuItem("Go To Preset");
		goToPreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.getPC().goToPreset();
			}
		});
		
		JToggleButton tb_mode = new JToggleButton("LIVE");
		tb_mode.setBounds(269, 57, 89, 23);
		contentPane.add(tb_mode);
		
		JMenuItem refresh = new JMenuItem("Refresh");

		mnMenu.add(newPreset);
    	mnMenu.add(savePreset);
    	mnMenu.add(editPreset);
    	mnMenu.add(deletePreset);
    	mnMenu.add(goToPreset);
    	mnMenu.add(refresh);
    	
    	JMenu mnEffect = new JMenu("Admin");
    	menuBar.add(mnEffect);
    	
    	JMenuItem mntmEffects = new JMenuItem("Effects");
    	mntmEffects.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			new EffectGUI(mc.getPedal()).setVisible(true);
    		}
    	});
    	mnEffect.add(mntmEffects);
		
		JSlider s_tempo = new JSlider();
		s_tempo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (source.getValueIsAdjusting()) {
				    int tempo = (int)((JSlider)e.getSource()).getValue();
				    if(!Global.disconnected)
				    	mc.getPedal().getController().setTempo(tempo);
				}
			}
		});
		
		s_tempo.setFont(new Font("Tahoma", Font.PLAIN, 9));
		s_tempo.setPaintTicks(true);
		s_tempo.setPaintLabels(true);
		s_tempo.setMajorTickSpacing(20);
		s_tempo.setToolTipText("Tempo");
		s_tempo.setValue(120);
		s_tempo.setMaximum(250);
		s_tempo.setMinimum(40);
		s_tempo.setBounds(359, 134, 205, 55);
		contentPane.add(s_tempo);
		
		JSlider s_level = new JSlider();
		s_level.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (source.getValueIsAdjusting()) {
				    int level = (int)((JSlider)e.getSource()).getValue();
				    if(!Global.disconnected)
				    	mc.getPedal().getController().setPatchLevel(level);
				}
			}
		});
		s_level.setValue(100);
		s_level.setToolTipText("Tempo");
		s_level.setPaintTicks(true);
		s_level.setPaintLabels(true);
		s_level.setMaximum(120);
		s_level.setMajorTickSpacing(20);
		s_level.setFont(new Font("Tahoma", Font.PLAIN, 9));
		s_level.setBounds(32, 134, 205, 55);
		contentPane.add(s_level);
		
		JButton btnMenu = new JButton("SAVE");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.normalAssign('S');
			}
		});
		btnMenu.setBounds(368, 57, 89, 23);
		contentPane.add(btnMenu);
		
		JLabel lblTempo = new JLabel("Tempo:");
		lblTempo.setBounds(366, 121, 46, 14);
		contentPane.add(lblTempo);
		
		JLabel lblVolume = new JLabel("Level:");
		lblVolume.setBounds(39, 121, 46, 14);
		contentPane.add(lblVolume);
		
		JButton btnPage = new JButton("PAGE");
		btnPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.normalAssign('P');
			}
		});
		btnPage.setBounds(467, 57, 89, 23);
		contentPane.add(btnPage);
		
		JTextField[] displays = { tf_main, tf_fs1 , tf_fs2 , tf_fs3 , tf_fs4 , tf_fs5 , tf_fs6 };
		JButton[] buttons = {b_1, b_2, b_3, b_4, b_5, b_6};

		mc = new MainController(displays, buttons);
		
		tb_mode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if(!Global.disconnected){
			        int state = itemEvent.getStateChange();
			        if (state == ItemEvent.SELECTED){ 
			        	mc.setLiveModeOn();
			        	mnMenu.removeAll();
			    		mnMenu.add(goToPatch);
			    		mnMenu.add(refresh);
			        }
			        else{
			        	mnMenu.removeAll();
			        	mnMenu.add(newPreset);
			        	mnMenu.add(savePreset);
			        	mnMenu.add(editPreset);
			        	mnMenu.add(deletePreset);
			        	mnMenu.add(goToPreset);
			        	mnMenu.add(refresh);
			        	mc.setLiveModeOff();
			        }
		        }
				else
					tb_mode.setEnabled(false);
			}
		});
		
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mc.getPedal().getController().start();
				Global.disconnected = false;
				mc.setLiveModeOn();
				tb_mode.setEnabled(true);
				tb_mode.setSelected(true);
				Global.delay(Global.effectDelay);
				new BackgroundProcess(mc.getPedal(),displays).start();
			}
		});
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	if(Global.disconnected)
		    		mc.close();
		    	else
		    		System.exit(0);
		    }
		});
	}
}
