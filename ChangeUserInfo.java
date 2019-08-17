import javax.swing.*;
import java.awt.event.*;

public class ChangeUserInfo extends JFrame implements ActionListener
{
	private MainMenu mainMenu;
	private RegisterUser registerUser;
	private DBConnection dbConnection;
    private UserLogin userLogin;
    private UserMenu userMenu;
	private JFrame popup;
	private JTextField  userNameField;
	private JPasswordField passwordField;
	private JTextField  age;
	private JTextField  phoneNumber;
	private JRadioButton male;
	private JRadioButton female;
	private final String bloodGroup[]={"-","A+","A-","B+","B-","AB+","AB-","O+","O-"};
	private JComboBox comboBox;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JLabel ageLabel;
	private JLabel phoneNumberLabel;
	private JLabel sexLabel;
	private JLabel bloodGroupLabel;
	private JButton confirm;
	private JButton back;
	private JButton backToUserMenu;
	public ChangeUserInfo()
	{
		popup = new JFrame();
		initButtons();
        setSize(600,600);
        setTitle("Change User Info");
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		back.addActionListener(this);
		confirm.addActionListener(this);
		add(male);
		add(female);
		add(userNameField);
		add(passwordField);
		add(age);
		add(comboBox);
		add(userNameLabel);
		add(passwordLabel);
		add(ageLabel);
		add(sexLabel);
		add(bloodGroupLabel);
		add(phoneNumber);
		add(phoneNumberLabel);
		add(confirm);
		add(back);
	}

	public void initButtons()
	{
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		male.setBounds(600/2-100,250,100,30);    
        female.setBounds(600/2,250,100,30);  
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(male);
		buttonGroup.add(female);
		userNameField = new JTextField("");
		userNameField.setBounds(600/2-100,100,220,30);
		passwordField = new JPasswordField ("");
		passwordField.setBounds(600/2-100,150,220,30);
		age = new JTextField ("");
		age.setBounds(600/2-100,200,220,30);
		phoneNumber = new JTextField("");
		phoneNumber.setBounds(600/2-100,300,220,30);	
		comboBox = new JComboBox(bloodGroup);
		comboBox.setBounds(600/2+30,305+50,90,20);
		userNameLabel = new JLabel("USERNAME");
		userNameLabel.setBounds(120,98,100,30);
		passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setBounds(119,148,100,30);
		ageLabel=new JLabel("AGE");
		ageLabel.setBounds(119,198,100,30);
		sexLabel= new JLabel("SEX");
		sexLabel.setBounds(119,248,100,30);
		phoneNumberLabel = new JLabel("PHONENO");
		phoneNumberLabel.setBounds(119,298,100,30);
		bloodGroupLabel = new JLabel ("BLOODGROUP");
		bloodGroupLabel.setBounds(119,298+50,100,30);
		confirm = new JButton("CONFIRM");
		confirm.setBounds(600/2-50,360+50,90,30);
		back = new JButton ("CANCLE");
		back.setBounds(600/2-50,410+50,90,30);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==back)
		{
			dispose();
			userMenu = new UserMenu();
		}

		if(e.getSource()==confirm)
		{
			String blood=""+comboBox.getItemAt(comboBox.getSelectedIndex());
			if(userNameField.getText().equals("") || passwordField.getText().equals("") || age.getText().equals("") || ( male.isSelected()==false  && female.isSelected()==false) || blood.equals("-")|| phoneNumber.getText().equals(""))
			{
				JOptionPane.showMessageDialog(popup,"No Field Can't Be Empty","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				String sex;
				if(male.isSelected())
				{
					sex="male";
				}
				else
				{
					sex="female";
				}
				dbConnection = new DBConnection();
				dbConnection.updateAccount(userNameField.getText(),passwordField.getText(),age.getText(),phoneNumber.getText(),sex,blood);
				dispose();
				userLogin = new UserLogin();
			    
			}
			
		}

	}
}