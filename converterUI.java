import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class converterUI extends JFrame implements ActionListener {

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
		new converterUI();
	}
	
	public converterUI() {
            //program check
            System.out.println("Running");
            
            this.setTitle("IEEE 756 Decimal-64 floating point translator");
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
			if(e.getSource() == convert);
                        {
                            toConvert = inputBox.getText();
                            System.out.println(toConvert);
                            System.out.println("Converted");
                            
                            System.out.println(toConvert);
                            outputBox.setText(converted.concat(toConvert));
                        }
        	
        }
 
        /**pwede mag lagay ng history make output panel grid into 1,2
         * create new panels for each
         * make labels
         * make text areas that are editable-false
         * output to each 
         */
       
}


