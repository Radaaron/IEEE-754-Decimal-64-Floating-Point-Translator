import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	//declare variables
	JPanel mainPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel convertPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	final JButton convert = new JButton("Convert");
	final JTextField inputBox = new JTextField("Enter 16-digit hex or 64-bit binary here", 45);
	final JTextField outputBox = new JTextField("Result", 45);
	
	//this is the input to the converter program
	String toConvert;
	//this is the output of the converter program
	String converted="";
	
	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() {
		//program check
		System.out.println("Running");
		
		this.setTitle("IEEE 754 Decimal-64 floating point translator");
		this.setSize(600,150);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//auto erase for the input text box
		inputBox.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e){
				inputBox.setText("");
			}
		} );
		
		//adds button action listener on click
		convert.addActionListener(this);
		
		outputBox.setEditable(false);
		outputBox.setForeground(Color.red);
		
		//gridLayout
		mainPanel.setLayout(new GridLayout(3,1));
		
		//add the components to their panels
		inputPanel.add(inputBox);
		convertPanel.add(convert);
		outputPanel.add(outputBox);
		
		//add the panels to the main panel
		mainPanel.add(inputPanel);
		mainPanel.add(convertPanel);
		mainPanel.add(outputPanel);
		
		//add the main panel to the frame
		this.add(mainPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Translator translator = new Translator();
		
		if(e.getSource() == convert);
		{
			toConvert = inputBox.getText();
			converted=translator.translate(toConvert);
			
			//output checker to cmd line
			System.out.println("converted to ieee:"+converted);
			//ouput gui
			outputBox.setText(converted);
		}
		
	}
	
}

/**
* Cases
* FFFFFFFFFFFFFFFF = NaN
* 78FFFFFFFFFFFFFF = +infinity
* F8FFFFFFFFFFFFFF = -infinity
* 2220000000000025 = +0000000000000025x10^-6
*/

