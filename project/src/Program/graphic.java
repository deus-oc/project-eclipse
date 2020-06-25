package Program;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
//for excel
import java.io.File;  
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//

public class graphic extends data{
    JFrame frame;
    JLabel mainLabel, headerLabel, imageLabel, regText;
	static JLabel notFound;
	JLabel hyperlink;
    static JTextField regField;
    JButton regSubmit, individualSubmit;
    JTable hiddenTable;
    JPanel mainPanel,firstPanel, secondPanel, secondFirstPanel, second2ndPanel, panelImage, submitPanel, allReportPanel, hiddenTablePanel, hiddenButtonPanel;    
    int madeHiddenPanel = 0;
    graphic() {
        frame = new JFrame();
        frame.setTitle("Generate Student Report");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //main label
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        //
        //first and second label
        firstPanel = new JPanel();
        //
        // create a new image icon
        ImageIcon imageA = new ImageIcon("src/img.png"); 
        imageLabel = new JLabel(imageA);
        
        panelImage = new JPanel(); 
        panelImage.add(imageLabel); 
        firstPanel.add(panelImage);
        mainPanel.add(firstPanel);

        secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(2,1));        
        
        secondFirstPanel = new JPanel();
        secondFirstPanel.setLayout(new GridLayout(2,1));
        //text field and all the report link
        submitPanel = new JPanel();
        regText = new JLabel("Reg No.");
        regField  = new JTextField(3);
        regSubmit = new JButton("Submit");
        notFound = new JLabel("");
        regSubmit.setBounds(50,200,50,50);  
		String[][] rowColumn = new String[1][11];
		regSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand(); 
                if (s.equals("Submit")) { 
                	ArrayList<String> studentDetails = new ArrayList<String>();
                	int valueEntered = openAndReadXL(studentDetails);
                	
                	if(valueEntered == 0) {
            			//make JLabel beside the regText "NOT FOUND"
                		notFound.setText("NOT FOUND!");
            		}
            		else{
            			//filling rowColumn[1][11] for data(SGPA and CGPA)
            			rowColumn[0][0] = studentDetails.get(0);
                    	rowColumn[0][1] = studentDetails.get(1);
            			int j = 0;
                    	double sumSGPA = 0;
                    	double totalcreditSGPA = 0;
                    	double totalcreditoneSGPA = 0;
                    	double sumtotalSGPA = 0;
                    	int kincrease = -1;
                    	DecimalFormat df = new DecimalFormat("#.##");
                    	for(int k = 2; k < studentDetails.size(); k++) {
                    		kincrease++;
                    		sumSGPA += Double.valueOf(studentDetails.get(k))*creditPoints[j][kincrease];
                    		totalcreditoneSGPA += creditPoints[j][kincrease]; 
                    		if(kincrease + 1 == numSubjectSem[j]) {
                    			kincrease = -1;
                    			sumtotalSGPA += sumSGPA;
                    			rowColumn[0][j+2] = String.valueOf(df.format(sumSGPA/totalcreditoneSGPA));
                    			j++;
                    			totalcreditSGPA += totalcreditoneSGPA;
                    			totalcreditoneSGPA = 0;
                    			sumSGPA = 0;
                    		}
                    	}
                    	double CGPA = sumtotalSGPA/totalcreditSGPA;
                    	rowColumn[0][j+2] = String.valueOf(df.format(CGPA));
                    	}

                		//for table and button
                        
                		//check if before
                		if(madeHiddenPanel == 1) {
                        	second2ndPanel.remove(hiddenButtonPanel);
                        	second2ndPanel.remove(hiddenTablePanel);
                        	secondPanel.remove(second2ndPanel);
                        	madeHiddenPanel--;
                            secondPanel.revalidate();
                            secondPanel.repaint();
                        	}
                		//check if before
                        	
                        if(valueEntered != 0) {
                        	 madeHiddenPanel++;
                        	 second2ndPanel = new JPanel();
                             second2ndPanel.setLayout(new GridLayout(2,1));
                            
                             hiddenTablePanel = new JPanel();
                             //create table model with data
                            DefaultTableModel model = new DefaultTableModel(rowColumn, detailName) {
                            	private static final long serialVersionUID = 1L;
              					@Override
              					public boolean isCellEditable(int row, int column){
              						return false;
                            	}
                            };
                            hiddenTable = new JTable(model);
                            hiddenTable.setRowHeight(30);
                            JScrollPane scrollpane = new JScrollPane(hiddenTable);
                            scrollpane.setPreferredSize(new Dimension(1000, 52));
                            hiddenTablePanel.add(scrollpane);
                            second2ndPanel.add(hiddenTablePanel);
                              
                            hiddenButtonPanel = new JPanel();
                            individualSubmit = new JButton("Check Now!");
                            individualSubmit.setBounds(50,200,50,50);
                            hiddenButtonPanel.add(individualSubmit);
                            second2ndPanel.add(hiddenButtonPanel);
                              
	                      	individualSubmit.addActionListener(new ActionListener(){
	                    		public void actionPerformed(ActionEvent e) {
	                                   String s = e.getActionCommand(); 
	                                   if (s.equals("Check Now!")) {
	                                	   new reportFrame(studentDetails, rowColumn);
	                                   } 
	                                }
	                      		});
                              
                              secondPanel.add(second2ndPanel);
                              secondPanel.revalidate();
                              secondPanel.repaint();
                        	}
                        	//for table and button                     		  
                    regField.setText(""); 
                } 
            }
        });
        submitPanel.add(regText);
        submitPanel.add(regField);
        submitPanel.add(regSubmit);
        submitPanel.add(notFound);
        secondFirstPanel.add(submitPanel);

        allReportPanel = new JPanel();
        hyperlink = new JLabel("For all Student abbreviated Report and Analysis");
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        allReportPanel.add(hyperlink);
        hyperlink.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                new allReportFrame();  
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        secondFirstPanel.add(allReportPanel);
                
        secondPanel.add(secondFirstPanel);
        mainPanel.add(secondPanel);
        frame.add(mainPanel);
        frame.setSize(1000, 500);
        frame.validate();  
//        frame.pack();
        frame.setVisible(true);  
    }
    public static void main(final String[] args){ 
        new graphic();
    }    
    @SuppressWarnings("deprecation")
	public static int openAndReadXL(ArrayList<String> studentDetails) {
		int valueEntered = 0;
    	try {
    		File file = new File("src/data.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);//creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
//			System.out.println("Start Iteration");
			//
			//
			while (itr.hasNext()){
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
				//till one row
				while (cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					//just for value
    					switch (cell.getCellType()){  
    						case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
    							if(cell.getStringCellValue() == regField.getText()) {
    								valueEntered++;
    								studentDetails.add(regField.getText());
    								break;
    							}
    							else {
    								if(valueEntered == 1) {
        								studentDetails.add(cell.getStringCellValue());
    								}
    								break;                  								
    							}
    						case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
    							if(cell.getNumericCellValue() == Integer.parseInt(regField.getText())) {
    								valueEntered++;
    								studentDetails.add(regField.getText());
    								break;
    							}
    							else {
    								if(valueEntered == 1) {
        								studentDetails.add(String.valueOf(cell.getNumericCellValue()));
    								}
        							break;  
    							}
    						default:  
    					}
					//just for value
					if(valueEntered == 0) {
						break;
					}
				}
				//till one row
//				System.out.println("");
    			if(valueEntered == 1) {
    				notFound.setText("");
    				break;
    			}
			}
			//till reading of excel sheet
        	wb.close();
        	fis.close();
        	//
    	}
		catch(Exception eA){  
			eA.printStackTrace();  
		}                      
    	return valueEntered;
    }
}