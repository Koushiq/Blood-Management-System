import javax.swing.*;
import java.awt.event.*;



public class MainMenu extends JFrame implements ActionListener
{
	private JButton loginAdmin;
	private JButton loginUser;
	private JButton  register;

	private RegisterUser registerUser;
	private UserLogin userLogin;

	public MainMenu()
	{
		//loginAdmin= new JButton("Log In As Admin");
		loginUser = new JButton("Log In As User");
		register = new JButton("Click Here To Register");
		setSize(600,500);
		setTitle("Main Menu");
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//loginAdmin.setBounds(600/2-110,600/2-200,220,30);
		loginUser.setBounds(600/2-110,600/2-50-100,220,30);
		register.setBounds(600/2-110,600/2+50-100,220,30);
		register.addActionListener(this);
		loginUser.addActionListener(this);
		//add(loginAdmin);
		add(loginUser);
		add(register);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==register)
		{
			registerUser= new RegisterUser();
			dispose();
		}

		if(e.getSource()==loginUser)
		{
			userLogin = new UserLogin();
			dispose();
		}
	}

}