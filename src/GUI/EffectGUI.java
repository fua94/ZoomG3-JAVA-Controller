package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import DB.ControllerDB;
import MainController.Global;
import PedalController.ZoomG3Unit;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("rawtypes")
public class EffectGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ZoomG3Unit pedal;
	private int fs;
	private int[] param;

	public EffectGUI(ZoomG3Unit p) {
		pedal = p;
		param = new int[3];
		setTitle("Effects Editor");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSlot = new JLabel("Slot:");
		lblSlot.setBounds(10, 11, 46, 14);
		contentPane.add(lblSlot);
		
		JLabel lblEffect = new JLabel("Type:");
		lblEffect.setBounds(10, 43, 46, 14);
		contentPane.add(lblEffect);
		
		JLabel lblEffect_1 = new JLabel("Effect:");
		lblEffect_1.setBounds(184, 43, 46, 14);
		contentPane.add(lblEffect_1);
		
		JLabel lblParam = new JLabel("Param:");
		lblParam.setBounds(10, 79, 46, 14);
		contentPane.add(lblParam);
		
		JLabel lblValue = new JLabel("Value:");
		lblValue.setBounds(184, 79, 46, 14);
		contentPane.add(lblValue);
		
		JLabel label = new JLabel("Param:");
		label.setBounds(10, 141, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Value:");
		label_1.setBounds(184, 141, 46, 14);
		contentPane.add(label_1);
		
		JSlider s_value1 = new JSlider();
		s_value1.setBounds(10, 104, 332, 23);
		contentPane.add(s_value1);
			
		JSlider s_value2 = new JSlider();
		s_value2.setBounds(10, 166, 332, 23);
		contentPane.add(s_value2);
		
		JLabel label_2 = new JLabel("Param:");
		label_2.setBounds(10, 203, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Value:");
		label_3.setBounds(184, 203, 46, 14);
		contentPane.add(label_3);
		
		JSlider s_value3 = new JSlider();
		s_value3.setBounds(10, 228, 332, 23);
		contentPane.add(s_value3);
		
		JComboBox cb_slot = new JComboBox();
		cb_slot.setBounds(53, 8, 53, 20);
		contentPane.add(cb_slot);
		
		JComboBox cb_type = new JComboBox();
		cb_type.setBounds(53, 40, 121, 20);
		contentPane.add(cb_type);
		
		JComboBox cb_effect = new JComboBox();
		cb_effect.setBounds(221, 40, 121, 20);
		contentPane.add(cb_effect);
		
		JComboBox cb_param1 = new JComboBox();
		cb_param1.setBounds(53, 76, 121, 20);
		contentPane.add(cb_param1);
		
		JComboBox cb_param2 = new JComboBox();
		cb_param2.setBounds(53, 138, 121, 20);
		contentPane.add(cb_param2);
		
		JComboBox cb_param3 = new JComboBox();
		cb_param3.setBounds(53, 200, 121, 20);
		contentPane.add(cb_param3);
		
		JComboBox cb_value1 = new JComboBox();
		cb_value1.setBounds(221, 76, 121, 20);
		contentPane.add(cb_value1);
		
		JComboBox cb_value2 = new JComboBox();
		cb_value2.setBounds(221, 138, 121, 20);
		contentPane.add(cb_value2);
		
		JComboBox cb_value3 = new JComboBox();
		cb_value3.setBounds(221, 200, 121, 20);
		contentPane.add(cb_value3);
		
		JComboBox[] cbs = {cb_slot, cb_type, cb_effect};
		JComboBox[] cparams = {cb_param1, cb_param2, cb_param3};
		JComboBox[] cvalues = {cb_value1, cb_value2, cb_value3};
		JSlider[] svalues = {s_value1, s_value2, s_value3};
		initAllComponents(cbs, cparams, cvalues, svalues);
		
		cb_slot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateComponents(cbs, cparams, cvalues, svalues);
			}
		});
		
		cb_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = cb_type.getSelectedIndex();
				updateEffectList(cb_effect, id);
			}
		});
		
		cb_effect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb_effect.getItemCount()>0){
					int id = ControllerDB.getEffectId(cb_effect.getSelectedItem().toString());
					fs = Integer.parseInt(cb_slot.getSelectedItem().toString()) - 1;
					pedal.getController().effectAssign(fs, id);
					pedal.getController().setEffectState(fs, true);
					updateComboBox(cparams, id);
				}
			}
		});
		
		cb_param1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateValueComponents(cb_effect, cb_param1, cb_value1, s_value1);
			}
		});
		
		cb_param2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateValueComponents(cb_effect, cb_param2, cb_value2, s_value2);
			}
		});
		
		cb_param3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateValueComponents(cb_effect, cb_param3, cb_value3, s_value3);
			}
		});
		
		cb_value1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		s_value1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setValueSlider(0, e, cb_param1);
			}
		});
		
		s_value2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setValueSlider(1, e, cb_param2);
			}
		});
		
		s_value3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setValueSlider(2, e, cb_param3);
			}
		});
		
	}
	
	private void updateComponents(JComboBox[] c, JComboBox[] p, JComboBox[] v, JSlider[] s){
		fs = Integer.parseInt(c[0].getSelectedItem().toString()) - 1;
		int effect = pedal.getEffectId(fs);
		String type = ControllerDB.getEffectType(effect);
		c[1].setSelectedItem(type);
		
		int id = c[1].getSelectedIndex();
		updateEffectList(c[2], id);
		
		c[2].setSelectedItem(ControllerDB.getEffect(effect));
		updateComboBox(p, effect);
		updateValueComponents(c[2], p[0], v[0], s[0]);
		updateValueComponents(c[2], p[1], v[1], s[1]);
		updateValueComponents(c[2], p[2], v[2], s[2]);
	}
	
	@SuppressWarnings("unchecked")
	private void initAllComponents(JComboBox[] c, JComboBox[] p, JComboBox[] v, JSlider[] s){
		int i;
		String[] typeList = ControllerDB.getEffectTypeList(); 
		
		for(i=0; i<c.length; i++)
			c[i].removeAllItems();

		for(i=0; i<Global.fsCount; i++)
			c[0].addItem(i+1);
		
		for(i=0; i<typeList.length; i++)
			c[1].addItem(typeList[i]);
		
		updateComponents(c, p, v, s);
	}
	
	@SuppressWarnings("unchecked")
	private void updateEffectList(JComboBox e, int id){
		e.removeAllItems();
		String[] effectList = ControllerDB.getEffectList(id);
		
		for(int i=0; i<effectList.length; i++)
			e.addItem(effectList[i]);
	}
	
	@SuppressWarnings("unchecked")
	private void updateComboBox(JComboBox[] c, int e){
		String[] paramList = ControllerDB.getParamList(e); 
		
		for(int i=0; i<c.length; i++){
			c[i].removeAllItems();
			for(int j = 0; j<paramList.length; j++)
				c[i].addItem(paramList[j]);
			if(paramList.length > 0)
				c[i].setSelectedIndex(i);
		}

	}
	
	@SuppressWarnings("unchecked")
	private void updateValueComponents(JComboBox e, JComboBox p, JComboBox v, JSlider s){
		if(p.getItemCount()>0){
			v.removeAllItems();
			String param = p.getSelectedItem().toString();
			int eid = ControllerDB.getEffectId(e.getSelectedItem().toString());
			int pid = ControllerDB.getEffectParamId(param, eid);
			int[] maxdef = ControllerDB.getEffectParamMaxDef(pid);
			String[] valueList = ControllerDB.getValueList(pid);
			if(valueList != null){
				for(int i=0; i<valueList.length; i++)
					v.addItem(valueList[i]);
			}
			if(v.getItemCount()>0){
				v.setSelectedIndex(maxdef[1]);
				v.setEnabled(true);
				s.setEnabled(false);
			}
			else{
				v.setEnabled(false);
				s.setEnabled(true);
			}
			s.setMinimum(0);
			s.setMaximum(maxdef[0]);
			s.setValue(maxdef[1]);
		}
	}
	
	private void setValueSlider(int i, ChangeEvent e, JComboBox p){
		JSlider source = (JSlider)e.getSource();
		if (source.getValueIsAdjusting()) {
		    int level = (int)((JSlider)e.getSource()).getValue();
		    param[i] = p.getSelectedIndex() + 2;
		    pedal.getController().setEffectParameter(fs, param[i], level);
		}
	}
}
