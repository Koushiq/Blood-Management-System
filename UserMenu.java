import javax.swing.*;
import java.awt.event.*;


public class UserMenu extends JFrame implements ActionListener
{
	private DonateMenu donateMenu;
	private ChangeUserInfo changeUserInfo;
	private UserLogin userLogin;
	private JButton signOut;
	private JButton donate;
	private JButton receiveBlood;
	private JButton changeAccount;
	private JButton deleteAccount;
	private JTextField  userNameField;
	private JPasswordField passwordField;
	private Enlisted enlisted;
	private JLabel requestLabel;
	private DBConnection dbConnection;
	private JFrame popup;
	public UserMenu()
	{
		popup = new JFrame();
		dbConnection = new DBConnection();
		this.userNameField=userNameField;
		this.passwordField=passwordField;
		setSize(600,600);
		setTitle("User Menu");
		initButtons();
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		signOut.addActionListener(this);
		add(signOut);
		donate.addActionListener(this);
		add(donate);
		receiveBlood.addActionListener(this);
		add(receiveBlood);
		changeAccount.addActionListener(this);
		add(changeAccount);
		add(deleteAccount);
		deleteAccount.addActionListener(this);
		if(dbConnection.checkSignal().equals("true"))
		{
		   add(requestLabel);
	    }
	    else
	    {
	    	requestLabel.setText("");
	    	add(requestLabel);
	    }
		
	}
	public void initButtons()
	{
		donate = new JButton("Donate");
		donate.setBounds(600/2-50,90+10,90,30);
		receiveBlood = new JButton("Search Donors");
		receiveBlood.setBounds(600/2-80,165+10,150,30);
		changeAccount= new JButton("Update Account");
		changeAccount.setBounds(600/2-80,240+10,150,30);
		deleteAccount = new JButton("Delete Account");
		deleteAccount.setBounds(600/2-80,315+10,150,30);
		signOut = new JButton("Sign Out");
		signOut.setBounds(600/2-50,390+10,90,30);
		requestLabel = new JLabel("You've been requested to donate!");
		requestLabel.setBounds(230,40,200,30);
	}


	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==signOut)
		{
			dispose();
			userLogin= new UserLogin();
		}

		if(e.getSource()==donate)
		{
			dispose();
			dbConnection.changeRequest(UserLogin.name,false);
			donateMenu = new DonateMenu();
			
		}
		if(e.getSource()==receiveBlood)
		{
			enlisted = new Enlisted();
			
			
		}
		if(e.getSource()==changeAccount)
		{
			dispose();
			changeUserInfo = new ChangeUserInfo();
		}
		if(e.getSource()==deleteAccount)
		{
			JOptionPane.showMessageDialog(popup,"Your Account is deleted!","ALERT",JOptionPane.WARNING_MESSAGE);
			dbConnection.deleteAccount();
			dispose();
			userLogin= new UserLogin();
		}
	}
}