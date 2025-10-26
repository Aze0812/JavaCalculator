import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.print.attribute.standard.Copies;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
      int BHeight = 500;
      int BWidth = 750;

      Color CustomBlack = new Color(0,0,0);
      Color CustomCharcoal = new Color(54, 69, 79);
      Color CustomMidnightBlue = new Color(25, 25, 112);
      Color CustomWhite = new Color(255, 255, 255);

      String[] buttonValues = {
        "AC", "+/-", "%",  "÷", 
        "7", "8",  "9",    "×", 
        "4", "5",  "6",    "-",
        "1", "2",  "3",    "+",
        "0", ".",  "√",    "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

      JFrame Frame = new JFrame("Monochrome Calculator");
      JLabel displayLabel = new JLabel();
      JPanel displayPanel = new JPanel();
      JPanel displayButtons = new JPanel();

      // TopSymbols = "AC", "+/-", "%". Variables for calculations.
      String x = "0";
      String y = "null";
      String operator = "null";

      Calculator(){
          
          Frame.setSize(BHeight, BWidth);
          Frame.setLocationRelativeTo(null);
          Frame.setResizable(false);
          Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          Frame.setLayout(new BorderLayout());

          displayLabel.setBackground(CustomBlack);
          displayLabel.setForeground(Color.white);
          displayLabel.setFont(new Font("Poppins", Font.PLAIN, 80));
          displayLabel.setHorizontalAlignment(JLabel.RIGHT);
          displayLabel.setText("0");
          displayLabel.setOpaque(true);

          displayPanel.setLayout(new BorderLayout());
          displayPanel.add(displayLabel);
          displayPanel.setPreferredSize(new Dimension(BWidth, 150));
          Frame.add(displayPanel, BorderLayout.NORTH);

          displayButtons.setLayout(new GridLayout(5, 4));
          displayButtons.setBackground(CustomWhite);
          Frame.add(displayButtons);
         

          for (int i = 0; i < buttonValues.length; i++){
                JButton button = new JButton();
                String ButtonVal = buttonValues[i];
                button.setFont(new Font ("Poppins", Font.PLAIN, 30));
                button.setText(ButtonVal);
                button.setFocusable(false);
                button.setBorder(new LineBorder(CustomMidnightBlue));
                
                if (Arrays.asList(topSymbols).contains(ButtonVal)){
                        button.setBackground(CustomWhite);
                        button.setForeground(CustomBlack);
                }
                else if (Arrays.asList(rightSymbols).contains(ButtonVal)){
                        button.setBackground(CustomWhite);
                        button.setForeground(CustomBlack);
                }
                else {
                        button.setBackground(CustomBlack);
                        button.setForeground(CustomWhite);
                }
                displayButtons.add(button);


            //Buttons Fuctionality.
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton Button = (JButton)e.getSource();
                        String BValue = button.getText();
                        
                  //RightSymbols = "÷", "×", "-", "+", "=".
                        if(Arrays.asList(rightSymbols).contains(BValue)) {
                              if (BValue.equals("=") ){
                                    if (x != null){
                                        y = displayLabel.getText();
                                        double NumX = Double.parseDouble(x);
                                        double NumY = Double.parseDouble(y);

                                        if (operator.equals("+")){
                                          displayLabel.setText(RemoveDecimal(NumX + NumY));
                                        }

                                        else if (operator.equals("-") ){
                                          displayLabel.setText(RemoveDecimal(NumX - NumY));
                                        }
                                        else if (operator.equals("×")){
                                          displayLabel.setText(RemoveDecimal(NumX * NumY));
                                        }
                                         else if (operator.equals("÷")){
                                          displayLabel.setText(RemoveDecimal(NumX  / NumY));
                                        }
                                    }
                              }
                              else if ("+-×÷".contains(BValue)){
                              x = displayLabel.getText();
                              operator = BValue;
                              displayLabel.setText("0");
                        }
                  }
                   
                   
                  // TopSymbols = "AC", "+/-", "%".
                    else if(Arrays.asList(topSymbols).contains(BValue)){

                        //Clear All.
                        if (BValue == "AC"){
                            ClearAll();
                            displayLabel.setText("0");
                        }
                        //Addition and Substraction.
                        else if (BValue == "+/-"){
                              double DisplayNum = Double.parseDouble(displayLabel.getText());
                              DisplayNum *= -1;
                              displayLabel.setText(RemoveDecimal(DisplayNum));
                        }
                        //Percentage.
                        else if (BValue.equals("%")) {
                              double DisplayNum = Double.parseDouble(displayLabel.getText());
                              DisplayNum /= 100;
                              displayLabel.setText(RemoveDecimal(DisplayNum));
                        }
                        else if (operator.equals("√")){
                              String current = displayLabel.getText();
                              if (current.equals("") || current.equals("0")) {
                                     displayLabel.setText("0");
                              }       
                        }
                    } 

                    // Numbers and decimal point.
                    else {
                        if (BValue.equals(".")){
                              if (!displayLabel.getText().contains(BValue)){
                                  displayLabel.setText(displayLabel.getText() + BValue);
                              }
                        }
                        else if ("09876543421".contains(BValue)) {

                              if (displayLabel.getText() == "0"){
                                  displayLabel.setText(BValue);
                              }
                              else {
                                  displayLabel.setText(displayLabel.getText() + BValue);
                              }
                        }
                        else if ("√".equals("√")){
                              String current = displayLabel.getText();
                              if (current.equals("") || current.equals("0")) {
                                    displayLabel.setText("0");
                        }else {
                              double num = Double.parseDouble(current);
                              if (num < 0) {
                              displayLabel.setText("Syntax Error.");
                        }else {
                              double result = Math.sqrt(num);
                              displayLabel.setText(RemoveDecimal(result));
                              x = String.valueOf(result); // update stored value for next operation
                              operator = "null";
                              }
                        }
                    }
                    }
                  }
                    
             });
      }

      Frame.setVisible(true);
}

             //Method to Clear All values.
             void ClearAll(){
                  x = "0";
                  y = "null";
                  operator = "null";
      }

            //Method for Removing a Decimals.
            String RemoveDecimal (double DisplayNum){
                  if (DisplayNum % 1 == 0) {
                        return Integer.toString((int) DisplayNum);
                  }
                  return Double.toString(DisplayNum);
      }
}
      
      
      
