package bsu.rfe.java.group7.lab2.Lipnitskij.varB;

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

//@SuppressWarnings("serial")


public class MainFrame extends JFrame 
{
	private static final int width = 400;
	private static final int height = 320;

	private JTextField TFX; 
	private JTextField TFY; 
	private JTextField TFZ; 
	
	private JTextField TRF;
	
	private ButtonGroup RB = new ButtonGroup();
	private Box Ftype = Box.createHorizontalBox();
	
	private int fId = 1;
	
	public Double formula1 (Double x, Double y, Double z)
	{
		return 1.0;
	}
	
	public Double formula2 (Double x, Double y, Double z)
	{
		return 2.0;
	}
	
	private void addRB(String buttonName, final int fId) {

		JRadioButton button = new JRadioButton(buttonName);
	
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev)
			{
				MainFrame.this.fId = fId;
				}
		});
		RB.add(button);

		Ftype.add(button);
		}
	public MainFrame()
	{
		super("Вычисление");
		setSize(width, height);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - width)/2,
		(kit.getScreenSize().height - height)/2);
		
		Ftype.add( Box.createHorizontalGlue() );
		addRB( "Формула 1", 1);
		addRB( "Формула 2", 2);
		RB.setSelected(RB.getElements().nextElement().getModel(), true);
		Ftype.add( Box.createHorizontalGlue() );
		Ftype.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		
		JLabel labelX = new JLabel("X:");
		TFX = new JTextField("0", 10);
		TFX.setMaximumSize(TFX.getPreferredSize());
		
		JLabel labelY = new JLabel("Y:");
		TFY = new JTextField("0", 10);
		TFY.setMaximumSize(TFY.getPreferredSize());
		
		JLabel labelZ = new JLabel("Z:");
		TFZ = new JTextField("0", 10);
		TFZ.setMaximumSize(TFZ.getPreferredSize());
		
		Box hbox = Box.createHorizontalBox();
		hbox.setBorder(BorderFactory.createLineBorder(Color.RED));
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
		
		JLabel labelResult = new JLabel("Результат:");
		TRF = new JTextField("0", 10);

		Box hboxResult = Box.createHorizontalBox();
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.add(labelResult);
		hboxResult.add(Box.createHorizontalStrut(10));
		hboxResult.add(labelResult);
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		JButton buttonCalc = new JButton("Вычислить");
		buttonCalc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
			try {
				Double x = Double.parseDouble(TFX.getText());
				Double y = Double.parseDouble(TFY.getText());
				Double z = Double.parseDouble(TFZ.getText());
				
				Double result;
				
				if (fId==1)
		               result = formula1(x, y,z);
		        else
	                	result = formula2(x, y,z);
				labelResult.setText(result.toString());
		        } catch (NumberFormatException ex) {
		        	JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",JOptionPane.WARNING_MESSAGE);
		        	}
		   }
		});

		JButton buttonReset = new JButton("Очистить поля");

		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
		        TFX.setText("0");
		        TFY.setText("0");
		        TRF.setText("0");
		}
		});

		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(Box.createVerticalGlue());
		contentBox.add(Ftype);
		contentBox.add(hbox);
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