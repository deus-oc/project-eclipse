package Program;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
public class reportFrame extends data{
	JFrame report;
	JPanel mainPanel;
	static String[][] subNames = {{"Computer Fundamentals and Programming with C","Physics(Quantum Mechanics,Optics and Solid State Physics)", "Analog Electronics","Mathematics –I (Linear Algebra)","English for Communication", "LAB : Computer Fundamentals and Programming with C", "LAB : Analog Electronics"}, 
						   {"Data Structure", "Mathematics –II (Probability and Statistics)", "Discrete Mathematics","Digital Electronics", "Pyschology","LAB : Data Structure", "LAB : Digital Electronics",  "Environmental Science and Practices",},
						   {"Mathematics (Calculus)", "Computer  Organization  and Architecture", "Algorithm -I", "Formal Language and Automata", "Humanities II: Economics", "LAB : Computer Organization and Architecture", "LAB : Algorithm –I", "LAB : IT Workshop –I(Python)"},
						   {"Operating Systems", "Foundation of Data Science", "OOPS (JAVA)", "Data Communications", "System and Signals", "LAB : Operating Systems", "LAB : OOPS(JAVA)", "LAB : IT Workshop –II(SciLab)"},
						   {"Database Management Systems (DBMS)","Computer Networks", "Artificial Intelligence", "Microcontroller Systems", "Elective-I", "Management-I", "LAB : DBMS", "LAB : Networking", "LAB : Microcontroller Systems"},
						   {"Complier Design", "Graph Theory", "Machine Learning", "Elective –II", "Software Engineering", "Management –II","LAB : Complier Design", "LAB : Software Engineering", "LAB : Project –I" },
						   {"Cryptography   and Network Security", "Elective –III", "Elective –IV", "Open Elective –I", "Humanities", "LAB : Project –II"},
						   {"Elective–V", "Elective–VI","Open-Elective-II", "Cyber Law and Ethics", "LAB : Project –III", "Comprehensive Viva"}
	};
	static String[][] codeName = {{"CS-101", "PH-101",	"EC-101", "MA-101",	"HU-101", "CS-111",	"EC-111"}, 
							    {"CS-201", "MA-201", "MA-202", "EC-201", "HU-201" ,"CS-211", "EC-211", "HU-211"},
							    {"MA-301", "CS-301", "CS-302", "CS-303", "HU-301", "CS-311", "CS-312", "CS-313"},
							    {"CS-401", "CS-402", "CS-403", "EC-401", "EC-402", "CS-411", "CS-412", "CS-413"},
							    {"CS-501", "CS-502", "CS-503", "EC-501", "EL-501", "HU-501", "CS-511", "CS-512", "EC-511"},
							    {"CS-601", "CS-602", "CS-603", "EL-601", "CS-604", "HU-601", "CS-611", "CS-612", "PR-601"},
							    {"CS-701", "EL-701", "EL-702", "OE-701", "HU-701", "PR-701"},
							    {"EL-801", "EL-802", "OE-801", "CS-801", "PR-801", "CV-801"}
	};
	String semName[] =  {"Semester I" , "Semester II", "Semester III", "Semester IV", "Semester V", "Semester VI", "Semester VII", "Semester VIII"};
	String headerName[] = {"Code", "Subject Name", "Credits", "Grade Point Obtained"};
	
	public reportFrame(ArrayList<String> studentDetails, String[][] rowColumn) {		
		report = new JFrame("Report Card");
		int index = 0, semCount = 0, studentDetailIndex = 2;
		mainPanel = new JPanel();
    	mainPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "IIIT KALYANI", TitledBorder.CENTER, TitledBorder.TOP));
    	mainPanel.setLayout(new GridLayout(semName.length+1,1));        
    	mainPanel.setSize(1260, 1200);
    	
    	JScrollPane scrollBar=new JScrollPane(mainPanel);  
    	
    	//labels panel
    	JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(3,1));        
    	JLabel registrationLabel = new JLabel("Registation No. : " + rowColumn[0][index]);
		JLabel nameLabel = new JLabel("Name : " + rowColumn[0][index+1]);
		JLabel totalCGPA = new JLabel("CGPA is : " + rowColumn[0][10]);
		labels.add(registrationLabel);
		labels.add(nameLabel);
		labels.add(totalCGPA);
		labels.setSize(1260,50);
		mainPanel.add(labels);
		//
		
		//studentDetails.get(i) this contains value of  regno name CS101 EC101 ... CV801
		//rowColumn[0][i] this contains value of regno name SGPA1 SGPA2 ... SGPA8 CGPA
		
		//now Panels for Semester1 
		JPanel sem1 = new JPanel();
		sem1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem1.setLayout(new GridLayout(2,1));
        //for Table 1
		// generating columnFill1[][]
		String[][] columnFill1 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill1[i][0] = codeName[semCount][i];
			columnFill1[i][1] = subNames[semCount][i];
			columnFill1[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill1[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill2[][]
		
    	DefaultTableModel model1 = new DefaultTableModel(columnFill1, headerName) {
			private static final long serialVersionUID = 1L;

			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};

		JTable semTable1 = new JTable(model1);
        sem1.add(new JScrollPane(semTable1));
		//for Table 1
		JLabel SGPA1 = new JLabel("SGPA1 : " + rowColumn[0][semCount+2]);
		sem1.add(SGPA1);
		
		mainPanel.add(sem1);
		semCount++;
		//now Panels for Semester1 
		
		//now Panels for Semester2 
		JPanel sem2 = new JPanel();
		sem2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem2.setLayout(new GridLayout(2,1));
        //for Table 2
		// generating columnFill2[][]
		String[][] columnFill2 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill2[i][0] = codeName[semCount][i];
			columnFill2[i][1] = subNames[semCount][i];
			columnFill2[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill2[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill2[][]
		
    	DefaultTableModel model2 = new DefaultTableModel(columnFill2, headerName) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};

		JTable semTable2 = new JTable(model2);
        sem2.add(new JScrollPane(semTable2));
		//for Table 2
		JLabel SGPA2 = new JLabel("SGPA2 : " + rowColumn[0][semCount+2]);
		sem2.add(SGPA2);
		
		mainPanel.add(sem2);
		semCount++;
		//now Panels for Semester2 


		//now Panels for Semester3 
		JPanel sem3 = new JPanel();
		sem3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem3.setLayout(new GridLayout(2,1));
		//for Table 3
		// generating columnFill3[][]
		String[][] columnFill3 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill3[i][0] = codeName[semCount][i];
			columnFill3[i][1] = subNames[semCount][i];
			columnFill3[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill3[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill3[][]
		
		
    	DefaultTableModel model3 = new DefaultTableModel(columnFill3, headerName) {
			private static final long serialVersionUID = 1L;
			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};
		JTable semTable3 = new JTable(model3);
		sem3.add(new JScrollPane(semTable3));
		//for Table 3
				
		JLabel SGPA3 = new JLabel("SGPA3 : " + rowColumn[0][semCount+2]);
		sem3.add(SGPA3);
				
		mainPanel.add(sem3);
		semCount++;
		//now Panels for Semester3 
		
		
		//now Panels for Semester4 
		JPanel sem4 = new JPanel();
		sem4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem4.setLayout(new GridLayout(2,1));
		//for Table 4
		// generating columnFill4[][]
		String[][] columnFill4 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill4[i][0] = codeName[semCount][i];
			columnFill4[i][1] = subNames[semCount][i];
			columnFill4[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill4[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill4[][]
		
		
    	DefaultTableModel model4 = new DefaultTableModel(columnFill4, headerName) {
			private static final long serialVersionUID = 1L;

			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};
		JTable semTable4 = new JTable(model4);
		sem4.add(new JScrollPane(semTable4));
		//for Table 4
				
		JLabel SGPA4 = new JLabel("SGPA4 : " + rowColumn[0][semCount+2]);
		sem4.add(SGPA4);
				
		mainPanel.add(sem4);
		semCount++;
		//now Panels for Semester4
		

		//now Panels for Semester5 
		JPanel sem5 = new JPanel();
		sem5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem5.setLayout(new GridLayout(2,1));
		//for Table 5
		// generating columnFill5[][]
		String[][] columnFill5 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill5[i][0] = codeName[semCount][i];
			columnFill5[i][1] = subNames[semCount][i];
			columnFill5[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill5[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill5[][]
		
		
    	DefaultTableModel model5 = new DefaultTableModel(columnFill5, headerName) {
			private static final long serialVersionUID = 1L;

			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};
		JTable semTable5 = new JTable(model5);
		sem5.add(new JScrollPane(semTable5));
		//for Table 5
				
		JLabel SGPA5 = new JLabel("SGPA5 : " + rowColumn[0][semCount+2]);
		sem5.add(SGPA5);
				
		mainPanel.add(sem5);
		semCount++;
		//now Panels for Semester5		

		//now Panels for Semester6 
		JPanel sem6 = new JPanel();
		sem6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem6.setLayout(new GridLayout(2,1));
		//for Table 6
		// generating columnFill6[][]
		String[][] columnFill6 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill6[i][0] = codeName[semCount][i];
			columnFill6[i][1] = subNames[semCount][i];
			columnFill6[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill6[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill6[][]
		
    	DefaultTableModel model6 = new DefaultTableModel(columnFill6, headerName) {
			private static final long serialVersionUID = 1L;

			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};

		JTable semTable6 = new JTable(model6);
		sem6.add(new JScrollPane(semTable6));
		//for Table 6
		
		JLabel SGPA6 = new JLabel("SGPA6 : " + rowColumn[0][semCount+2]);
		sem6.add(SGPA6);
				
		mainPanel.add(sem6);
		semCount++;
		//now Panels for Semester6
		
		
		//now Panels for Semester7 
		JPanel sem7 = new JPanel();
		sem7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem7.setLayout(new GridLayout(2,1));
		//for Table 7
		// generating columnFill7[][]
		String[][] columnFill7 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill7[i][0] = codeName[semCount][i];
			columnFill7[i][1] = subNames[semCount][i];
			columnFill7[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill7[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill7[][]
    	DefaultTableModel model7 = new DefaultTableModel(columnFill7, headerName) {
			private static final long serialVersionUID = 1L;

			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};
		JTable semTable7 = new JTable(model7);
		sem7.add(new JScrollPane(semTable7));
		//for Table 7
				
		JLabel SGPA7 = new JLabel("SGPA7 : " + rowColumn[0][semCount+2]);
		sem7.add(SGPA7);
				
		mainPanel.add(sem7);
		semCount++;
		//now Panels for Semester7
		
		//now Panels for Semester8 
		JPanel sem8 = new JPanel();
		sem8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),semName[semCount], TitledBorder.RIGHT, TitledBorder.TOP));
		sem8.setLayout(new GridLayout(2,1));
		//for Table 8
		// generating columnFill8[][]
		String[][] columnFill8 = new String[(int) numSubjectSem[semCount]][4];
		for(int i = 0; i < (int)numSubjectSem[semCount]; i++) {
			columnFill8[i][0] = codeName[semCount][i];
			columnFill8[i][1] = subNames[semCount][i];
			columnFill8[i][2] = String.valueOf(creditPoints[semCount][i]);
			columnFill8[i][3] = studentDetails.get(studentDetailIndex + i);
		}
		studentDetailIndex += (int)numSubjectSem[semCount];
		// generating columnFill8[][]
		
    	DefaultTableModel model8 = new DefaultTableModel(columnFill8, headerName) {
			private static final long serialVersionUID = 1L;

			@Override
    	    public boolean isCellEditable(int row, int column){
    	        return false;
    	    }
    	};
		JTable semTable8 = new JTable(model8);
		sem8.add(new JScrollPane(semTable8));
		//for Table 8
				
		JLabel SGPA8 = new JLabel("SGPA8 : " + rowColumn[0][semCount+2]);
		sem8.add(SGPA8);
				
		mainPanel.add(sem8);
		semCount++;
		//now Panels for Semester8
		scrollBar.setSize(1260, 1000);
		report.add(scrollBar);
//		report.add(mainPanel);
		report.setSize(1260,1000);
		report.setVisible(true);
	}
}