import javax.swing.*;
import java.awt.event.*;

public class UserLogin extends JFrame implements ActionListener
{
	private UserMenu userMenu;
	private JButton back;
	private JButton login;
	private MainMenu mainMenu;
	private JTextField  userNameField;
	private JPasswordField passwordField;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private DBConnection dbConnection;
	private JFrame jf;
	public static String name;
	public static String pass;
	public UserLogin()
	{
		setSize(600,600);
		initButtons();
        setTitle("UserLogin");
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(back);
		add(login);
		add(userNameField);
		add(userNameLabel);
		add(passwordField);
		add(passwordLabel);
		back.addActionListener(this);
		login.addActionListener(this);
	}
	public void initButtons()
	{
		jf = new JFrame ();
		back  = new JButton("BACK");
		back.setBounds(600/2-50,410+50,90,30);
		login = new JButton("LOGIN");
		login.setBounds(600/2-50,410,90,30);
		userNameField = new JTextField("");
		userNameField.setBounds(600/2-100,200,220,30);
		passwordField = new JPasswordField ("");
		passwordField.setBounds(600/2-100,350-30,220,30);
		userNameLabel = new JLabel("USERNAME");
		userNameLabel.setBounds(120,198,100,30);
		passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setBounds(119,348-30,100,30);
	}
	public void actionPerformed(ActionEvent e)
	{
	      if(e.getSource()==back)
	      {
	      	   dispose();
	      	   mainMenu= new MainMenu();
	      }
	      if(e.getSource()==login)
	      {
	      	  if(userNameField.getText().equals("") || passwordField.getText().equals(""))
    	      {
    		       JOptionPane.showMessageDialog(jf,"No field can't be empty","ERROR",JOptionPane.ERROR_MESSAGE);
    	      }
    	      else
    	      { 
	      	     dbConnection = new DBConnection();
	      	     if(dbConnection.validateLogin(userNameField.getText(),passwordField.getText()))
	      	     {
	      	     	JOptionPane.showMessageDialog(jf,"Login Successful");
	      	     	name=userNameField.getText();
	      	     	pass=passwordField.getText();
	      	     	userMenu = new UserMenu();
	      	     	dispose();
	      	     }
	      	     else
	      	     {
	      	     	JOptionPane.showMessageDialog(jf,"Invalid Username or Password","ERROR",JOptionPane.ERROR_MESSAGE);
	      	     }
	      	  }
	      }
	}

}