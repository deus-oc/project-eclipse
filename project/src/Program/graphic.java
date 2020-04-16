package Program;
import java.awt.event.*; 
import javax.swing.*;
import java.io.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Cursor;
//for excel
import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//

public class graphic{
    JFrame frame;
    JLabel mainLabel, headerLabel, imageLabel, regText, notFound,  hyperlink;
    JTextField regField;
    JButton regSubmit;
    JTable tableData;
    JPanel mainPanel,firstPanel, secondPanel, panelImage, submitPanel, allReportPanel;

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
        secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(4,1));        
        //
        // create a new image icon
        ImageIcon imageA = new ImageIcon("/home/deus-oc/project/symbol.png"); 
        imageLabel = new JLabel(imageA);
        
        panelImage = new JPanel(); 
        panelImage.add(imageLabel); 
        firstPanel.add(panelImage);
        mainPanel.add(firstPanel);

        //text field
        submitPanel = new JPanel();
        regText = new JLabel("Reg No.");
        regField  = new JTextField(4);
        regSubmit = new JButton("Submit");
        notFound = new JLabel("");
        regSubmit.setBounds(50,200,50,50);  
        
        
        
        regSubmit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand(); 
                if (s.equals("Submit")) { 
                	try{  
            			File file = new File("/home/deus-oc/project/data.xlsx");   //creating a new file instance  
            			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
            			//creating Workbook instance that refers to .xlsx file  
            			XSSFWorkbook wb = new XSSFWorkbook(fis);   
            			XSSFSheet sheet = wb.getSheetAt(0);//creating a Sheet object to retrieve object  
            			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
            			System.out.println("Start Iteration");
            			int valueEntered = 0;
            			while (itr.hasNext()){
            				Row row = itr.next();
            				Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            					while (cellIterator.hasNext()){
                					Cell cell = cellIterator.next();
                					switch (cell.getCellType()){  
                						case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                							if(cell.getStringCellValue() == regField.getText()) {
                								regText.setText("String");
                								valueEntered++;
                								break;
                							}
                							else {
                								System.out.print(cell.getStringCellValue() + "\t\t\t");  
                								break;                  								
                							}
                						case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                							if(cell.getNumericCellValue() == Integer.parseInt(regField.getText())) {
                								regText.setText("Number");
                								valueEntered++;
                								break;
                							}
                							else {
                    							System.out.print(cell.getNumericCellValue() + "\t\t\t");  
                    							break;  
                							}
                						default:  
                					}  
                					break;
                				}
            				System.out.println("");
                			if(valueEntered > 0) {
                				notFound.setText("");
                				break;
                			}
            			}
            			if(valueEntered == 0) {
            				//make JLabel beside the regText "NOT FOUND"
            				notFound.setText("NOT FOUND!");
            			}
            		}  
            		catch(Exception eA){  
            			eA.printStackTrace();  
            		}  
                    new reportFrame();
//                    regText.setText(regField.getText()); 
                    regField.setText(""); 
                } 
            }
        });
        submitPanel.add(regText);
        submitPanel.add(regField);
        submitPanel.add(regSubmit);
        submitPanel.add(notFound);
        secondPanel.add(submitPanel);

        allReportPanel = new JPanel();
        hyperlink = new JLabel("For all Student abbreviated Report and Analysis");
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        allReportPanel.add(hyperlink);
        secondPanel.addMouseListener(new MouseListener() {
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
        secondPanel.add(allReportPanel);
        mainPanel.add(secondPanel);
        frame.add(mainPanel);
        frame.setSize(500, 600);
        frame.validate();  
        frame.setVisible(true);  
    }
    @SuppressWarnings("deprecation")
	public static void main(final String[] args){ 
        new graphic();
    }    
}