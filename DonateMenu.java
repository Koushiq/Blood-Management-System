import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.text.DateFormat;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class DonateMenu extends JFrame implements ActionListener
{
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DonateMenu donateMenu;
	private JFrame popup;
	private JButton donateButton;
	private JButton backButton;
	private JButton updateMenu;
	private JLabel messageToShow;
	private JLabel messageToShow2;
	private UserMenu userMenu;
	private DBConnection dbConnection;
	private String date;
	private String targetDate;
	private boolean flag=false;
	public DonateMenu()
	{
		dbConnection = new DBConnection();
		this.date = dbConnection.getDate();
		popup = new JFrame();
		setSize(600,600);
		setTitle("Donation Menu");
		initButtons();
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		donateButton.addActionListener(this);
		backButton.addActionListener(this);
		updateMenu.addActionListener(this);
		add(donateButton);
		add(backButton);
		add(updateMenu);
		add(messageToShow);
		add(messageToShow2);
	}
	public void initButtons()
	{
		donateButton = new JButton("DONATE!");
		donateButton.setBounds(600/2-60,600/2-30,90,30);
		backButton = new JButton("BACK");
		backButton.setBounds(600/2-60,600/2+10,90,30);
		updateMenu = new JButton("Refresh");
		updateMenu.setBounds(500,10,90,30);
		messageToShow = new JLabel();
		messageToShow2 = new JLabel();
		if(this.date.equals("N/A"))
		{
			messageToShow.setBounds(600/2-50,150,100,30);
			messageToShow.setText("Please donate");
			donateButton.setEnabled(true);
			
		}
		else
		{
			messageToShow.setBounds(600/2-80,150,300,30);
			messageToShow2.setBounds(600/2-80,200,300,30);
			messageToShow.setText("You have donated at "+this.date);
			this.targetDate = calculateMonths(this.date);
			messageToShow2.setText("You have to wait till "+this.targetDate);
			donateButton.setEnabled(false);
			if( this.targetDate.equals(getCurrentDate()) )
			{
				dbConnection.setDateNA();
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==donateButton)
		{
			dispose();
			dbConnection.setCurDate();
			donateMenu = new DonateMenu();
		}
		if(e.getSource()==backButton)
		{
			dispose();
			userMenu = new UserMenu();
		}
		if(e.getSource()==updateMenu)
		{
			dispose();
			donateMenu = new DonateMenu();
		}
	}
	public String calculateMonths(String date)
	{
		String targetDate="NA";
		try
		{
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DATE, 120);
            Date currentDatePlusFourty = c.getTime();
            targetDate=dateFormat.format(currentDatePlusFourty);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return targetDate;
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