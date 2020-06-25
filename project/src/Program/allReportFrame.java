package Program;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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


public class allReportFrame extends data{
	JFrame report;
	JTable mainTable;
	JPanel mainPanel;
    static int rowCount = 0;
    static int columnCount = 0;	
	public allReportFrame() {
		report = new JFrame();
		DecimalFormat df = new DecimalFormat("#.##");
		
        
        	ArrayList<String> studentDetails = new ArrayList<String>();
    		rowCount = 0;
    		columnCount = 0;
        	openAndReadXMLFile(studentDetails);
    		String[][] rowColumn = new String[rowCount-1][11];     		
    		//filling rowColumn[][]
    		int valueOfk = columnCount;
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
		        		rowColumn[i][j+2] = String.valueOf(df.format(sumSGPA/totalcreditoneSGPA));
		        		j++;
		        		totalcreditSGPA += totalcreditoneSGPA;
		        		totalcreditoneSGPA = 0;
		        		sumSGPA = 0;
		        	}
		        }
		        double CGPA = sumtotalSGPA/totalcreditSGPA;
		        rowColumn[i][j+2] = String.valueOf(df.format(CGPA));
		        valueOfk += columnCount;
			}
			//filling rowColumn[][]

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
                  report.add(scrollBar);
                  //for table generation  	
		
        report.setSize(1000,800);
		report.pack();
		report.setVisible(true);
	}
	@SuppressWarnings("deprecation")
	static void openAndReadXMLFile(ArrayList<String> studentDetails) {
		try{
			File file = new File("src/data.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);//creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
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
		}
		catch(Exception eA){  
			eA.printStackTrace();  
		}
	}
}