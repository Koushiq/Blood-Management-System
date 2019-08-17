import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.text.DateFormat;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 


public class Enlisted extends JFrame implements ActionListener
{
	
	public static String[][] data;
    private JTable tTable;
	private JScrollPane tScroll;
	private DBConnection dbConnection;
	Font f1 = new Font("Calibri",Font.PLAIN,15);
	private JButton requestUser;
	private JTextField inputName;
	private JFrame jf;

    public Enlisted()
	{
		jf= new JFrame();
		
		dbConnection = new DBConnection();
        setTitle("Enlisted Donators");
        requestUser= new JButton("Request");
        inputName = new JTextField("");
        setSize(1300,600);
        requestUser.setBounds(1180,50,90,30);
        inputName.setBounds(1160,100,120,30);
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        add(requestUser);
        add(inputName);
        requestUser.addActionListener(this);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawTable();

    }

    public void drawTable()
    {
    	int c=0;
		data = new String[100][7];
		String column[] = {"Name", "Age","Sex","Phone No.","Blood Group", "Last Donated"," Next Donation"};

		int j=0;

		for(int i = 0; i<40; i++)
		{
			j = 0;
			data[i][j++] = "-";
			data[i][j++] = "-";
			data[i][j++] = "-";
			data[i][j++] = "-";
			data[i][j++] = "-";
			data[i][j++] = "-";
			data[i][j++] = "-";
			c++;
			
		}
		dbConnection.getAllInfo();


		String [][] newData = new String[c][7];
		
		for(int i=0; i<40; i++)
		{
			for(int k=0; k<7; k++)
			{
				newData[i][k]=data[i][k];
			}
		    
		}

		tTable = new JTable(newData, column){
			    DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();

			    { 
			        renderRight.setHorizontalAlignment(SwingConstants.CENTER);
			    }

			    @Override
			    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
			        return renderRight;
			    };
			};
			
			tTable.setFont(f1);
			tTable.setOpaque(false);
			tTable.setForeground(Color.GRAY);
			tTable.setBackground(Color.WHITE);
			tTable.setRowHeight(tTable.getRowHeight()+20);
    		tScroll = new JScrollPane(tTable);
			tScroll.setBounds(50,40,1100,480);

			this.add(tScroll);



    }

	public void actionPerformed(ActionEvent e)
	{
		boolean flag=false;
		String nextDate="";
		if(e.getSource()==requestUser)
		{
			for(int i = 0; i<40; i++)
		    {
			    System.out.println(data[i][0]);
			    if(inputName.getText().equals(data[i][0]))
			    {
			    	nextDate=data[i][6];
			    	flag=true;
			    	break;
			    }
		   }
		   if(flag==true)
		   {
		   	   if(nextDate.equals("N/A"))
		   	   {
		   	   	   JOptionPane.showMessageDialog(jf,"Request Sent");
		   	   	   dbConnection.changeRequest(inputName.getText(),true);
		   	   }
		   	   else if(nextDate.equals(getCurrentDate()))
		   	   {
		   	      JOptionPane.showMessageDialog(jf,"Request Sent");	
		   	      dbConnection.changeRequest(inputName.getText(),true);
		   	   }
		   	   else
		   	   {
		   	   	  JOptionPane.showMessageDialog(jf,"User Can't donate before "+nextDate);
		   	   }
		   	   
		   } 
		   else
		   {
		   	 JOptionPane.showMessageDialog(jf,"Username not found","ERROR",JOptionPane.ERROR_MESSAGE);
		   }
		}
	}
	public String getCurrentDate()
	{
		   String currTime;
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
           LocalDateTime now = LocalDateTime.now();
           currTime = dtf.format(now);  
           return currTime;
	}

	
}
