package Program;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
//for excel
import java.io.File;  
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//


public class allReportFrame {
	JFrame report;
	
	JTable mainTable;
	JPanel mainPanel;
    static double[][] creditPoints = {{4,3,3,4,3,2,2}, {3,4,4,3,3,2,2,2}, {4,3,3,3,3,2,2,4}, {3,3,4,3,3,2,2,2},{3,3,3,3,3,3,2,2,2},{3,3,3,3,3,3,2,2,2},{3,3,3,3,3,6},{3,3,3,2,6,2}};
    static double[] numSubjectSem = {7,8,8,8,9,9,6,6};

    static String[] detailName = {"RegNo.", "Name", "SGPA1", "SGPA2", "SGPA3", "SGPA4","SGPA5","SGPA6","SGPA7","SGPA8","CGPA"};
	@SuppressWarnings("deprecation")
	public allReportFrame() {
		report = new JFrame();
		
        int rowCount = 0;        
        int columnCount = 0;
        try{
    		File file = new File("src/data.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);//creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			ArrayList<String> studentDetails = new ArrayList<String>();
			while (itr.hasNext()){
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
				//till one row
				while (cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					//just for value
    					switch (cell.getCellType()){  
    						case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
    							studentDetails.add(cell.getStringCellValue());
    							break;    							
    						case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
        						studentDetails.add(String.valueOf(cell.getNumericCellValue()));
        						break;
    						default:  
    							break;
    					}
    				if(rowCount == 0) {
    					columnCount++;
    				}
				}
    			//till one row
				rowCount++;
			}
			wb.close();
			fis.close();
//			for(int i=0; i<studentDetails.size(); i++) {
//				System.out.println(studentDetails.get(i));
//			}
			
			// generating string[][]
			int valueOfk = columnCount;
			String[][] rowColumn = new String[rowCount-1][11]; //4 semester not still included
			for(int i = 0; i < rowCount-1; i++) {
				rowColumn[i][0] = studentDetails.get(valueOfk);
		        rowColumn[i][1] = studentDetails.get(valueOfk+1);
				int j = 0;
		        double sumSGPA = 0;
		        double totalcreditSGPA = 0;
		        double totalcreditoneSGPA = 0;
		        double sumtotalSGPA = 0;
		        int kincrease = -1;
		        for(int k = 2; k < columnCount; k++) {
		        	kincrease++;
		        	sumSGPA += Double.valueOf(studentDetails.get(k+valueOfk))*creditPoints[j][kincrease];
		        	totalcreditoneSGPA += creditPoints[j][kincrease]; 
		        	if(kincrease + 1 == numSubjectSem[j]) {
		        		kincrease = -1;
		        		sumtotalSGPA += sumSGPA;
		        		rowColumn[i][j+2] = String.valueOf(sumSGPA/totalcreditoneSGPA);
		        		j++;
		        		totalcreditSGPA += totalcreditoneSGPA;
		        		totalcreditoneSGPA = 0;
		        		sumSGPA = 0;
		        	}
		        }
		        double CGPA = sumtotalSGPA/totalcreditSGPA;
		        rowColumn[i][j+2] = String.valueOf(CGPA);
//		        for(int q = 0; q < 11; q++) {
//		        	System.out.println(rowColumn[i][q]);
//		        }
		        valueOfk += columnCount;
			}
			// generating string[][]
			
				//for table generation
              	  mainPanel = new JPanel();
              	  mainPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "FULL STUDENT REPORT", TitledBorder.CENTER, TitledBorder.TOP));
                
              	//create table model with data
              	DefaultTableModel model = new DefaultTableModel(rowColumn, detailName) {
					private static final long serialVersionUID = 1L;

					@Override
              	    public boolean isCellEditable(int row, int column){
              	        return false;
              	    }
              	};
              	  JScrollPane scrollBar=new JScrollPane(mainPanel);  
              	  mainTable = new JTable(model);
              	  mainTable.setRowHeight(30);
                  mainTable.setAutoCreateRowSorter(true);
                  JScrollPane scrollpane = new JScrollPane(mainTable);
                  scrollpane.setPreferredSize(new Dimension(1000, 500));
                  mainPanel.add(scrollpane);
//                  report.add(mainPanel);
                  report.add(scrollBar);
                  //
    	}  	
		catch(Exception eA){  
			eA.printStackTrace();  
		}
		report.setSize(1000,800);
		report.pack();
		report.setVisible(true);
	}	
}