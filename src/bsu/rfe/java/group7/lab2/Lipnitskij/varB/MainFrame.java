package bsu.rfe.java.group7.lab2.Lipnitskij.varB;

import java.lang.Math;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")


public class MainFrame extends JFrame 
{
	private static final int width = 400;
	private static final int height = 320;
	
	private double x=0;
	private double y=0;
	private double z=0;
	private Double result=0.0;
 
	private JTextField TFX; 
	private JTextField TFY;
	private JTextField TFZ;
	
	private JTextField TRF;
	
	private ButtonGroup RBF = new ButtonGroup();
	private ButtonGroup RBP = new ButtonGroup();
	private Box Ftype = Box.createHorizontalBox();
	private Box Ptype = Box.createHorizontalBox();
	
	private int fId = 1;
	private int pId = 1;
	
	public Double formula1 (Double x, Double y, Double z)
	{
		return  Math.pow( (Math.cos( Math.pow(Math.E,y) ) + Math.pow(Math.E,y)+ Math.pow((1/x),0.5) ),1/4)/ Math.pow( ( Math.cos( Math.PI*Math.pow( z, 3.0 ) ) + Math.log(Math.pow( (1+z), 2.0)) ), Math.sin( y ));
	}
	
	public Double formula2 (Double x, Double y, Double z)
	{
		return Math.pow( ( 1+Math.pow(x,2) ), y)/ Math.pow( Math.E, ( Math.sin(z) + x) );
	}
	
	private void addRBF(String buttonName, final int fId) {

		JRadioButton button = new JRadioButton(buttonName);
	
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev)
			{
				MainFrame.this.fId = fId;
				}
		});
		RBF.add(button);

		Ftype.add(button);
	}
	
	private void addRBP(String buttonName, final int pId) {

		JRadioButton button = new JRadioButton(buttonName);
	
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev)
			{
				MainFrame.this.pId = pId;
				}
		});
		RBP.add(button);

		Ptype.add(button);
	}
	
	public MainFrame()
	{	
		super("Вычисление");
		setSize(width, height);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - width)/2,
		(kit.getScreenSize().height - height)/2);
		
		Ftype.add( Box.createHorizontalGlue() );
		addRBF( "Формула 1", 1);
		addRBF( "Формула 2", 2);
		RBF.setSelected(RBF.getElements().nextElement().getModel(), true);
		Ftype.add( Box.createHorizontalGlue() );
		Ftype.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		Ptype.add( Box.createHorizontalGlue() );
		addRBP( "X", 1);
		addRBP( "Y", 2);
		addRBP( "Z", 3);
		RBP.setSelected(RBP.getElements().nextElement().getModel(), true);
		Ptype.add( Box.createHorizontalGlue() );
		Ptype.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel labelX = new JLabel("X:");
		TFX = new JTextField("0", 10);
		TFX.setMaximumSize(TFX.getPreferredSize());
		
		JLabel labelY = new JLabel("Y:");
		TFY = new JTextField("0", 10);
		TFY.setMaximumSize(TFY.getPreferredSize());
		
		JLabel labelZ = new JLabel("Z:");
		TFZ = new JTextField("0", 10);
		TFZ.setMaximumSize(TFZ.getPreferredSize());
		
		JLabel labelResult = new JLabel("Результат:");
		TRF = new JTextField("0", 10);
		TRF.setMaximumSize(TRF.getPreferredSize());
		
		Box hbox = Box.createHorizontalBox();
		hbox.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
		hbox.add(Box.createHorizontalGlue());
		hbox.add(labelX);
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(TFX);
		hbox.add(Box.createHorizontalStrut(100));
		hbox.add(labelY);
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(TFY);
		hbox.add(Box.createHorizontalStrut(100));
		hbox.add(labelZ);
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(TFZ);
		hbox.add(Box.createHorizontalGlue());

		Box hboxResult = Box.createHorizontalBox();
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.add(labelResult);
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JButton buttonCalc = new JButton("Вычислить");
		buttonCalc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
			 {
				x = Double.parseDouble(TFX.getText());
				y = Double.parseDouble(TFY.getText());
				z = Double.parseDouble(TFZ.getText());
				
				if (fId==1)
		               result = formula1(x, y,z);
		        else
	                	result = formula2(x, y,z);
				labelResult.setText(result.toString());
		        } 
		   }
		});

		JButton buttonReset = new JButton("Очистить поля");

		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
		        TFX.setText("0");
		        TFY.setText("0");
		        TRF.setText("0");
		        labelResult.setText("Результат");
		}
		});
		
		JButton buttonM = new JButton("M+");

		buttonM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(pId ==1) {
				    x = Double.parseDouble(TFX.getText());
				    result += x;
				}
				else if(pId==2) {
					y = Double.parseDouble(TFY.getText());
					result+=y;
				}
				else {
					z = Double.parseDouble(TFZ.getText());
					result+=z;
				}
				labelResult.setText(result.toString());
		}
		});
		
		JButton buttonMC = new JButton("MC");

		buttonMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(pId ==1) TFX.setText("0");
				else if(pId==2) TFY.setText("0");
				else TFZ.setText("0");
		}
		});
		
		
		Box hpbox = Box.createHorizontalBox();
		hpbox.add(Box.createHorizontalGlue());
		hpbox.add(buttonM);
		hpbox.add(Box.createHorizontalStrut(30));
		hpbox.add(buttonMC);
		hpbox.add(Box.createHorizontalGlue());
		hpbox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(Box.createVerticalGlue());
		contentBox.add(Ftype);
		contentBox.add(hbox);
		contentBox.add(Ptype);
		contentBox.add(hpbox);
		contentBox.add(hboxResult);
		contentBox.add(hboxButtons);
		contentBox.add(Box.createVerticalGlue());
		getContentPane().add(contentBox, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) 
	{
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}