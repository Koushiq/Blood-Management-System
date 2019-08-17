import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.text.DateFormat;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
public class DBConnection
{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private boolean flag;
    private String Password;
    private String Username;
    private JTextField userNameField;
    private JPasswordField passwordField;
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private JFrame jf= new JFrame();
    public DBConnection()
    {
       
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Blood Management System","root","");
            System.out.println("Connection success !!");
            st = con.createStatement();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Can't access reason is "+e);
        }
    }


    public void addAccount(String username,String password,String age,String sex,String phonenumber,String bloodgroup)
    {
    	String donatedate="N/A";
        boolean requestSignal = false;
    	        try
                {
                   String query = "insert into membersinfo (username,password,age,sex,phonenumber,bloodgroup,donatedate,requestsignal) values ('"+ username + "','" + password + "','" + age + "','" + sex + "','" + phonenumber + "','"+bloodgroup+"','"+ donatedate +"','"+requestSignal+"');";
                   st.executeUpdate(query);
                   JOptionPane.showMessageDialog(jf,"Account Successfully Added");
                }
                catch(Exception e)
                {
                   JOptionPane.showMessageDialog(jf,"Name Exists try a different name","ERROR",JOptionPane.ERROR_MESSAGE);
                }
    }

    public boolean validateLogin(String username, String password)
    {
        flag=false;
    	
    	try
    	{
    		String query = "select username, password from membersinfo where username= '"+ username +"' and password= '"+ password + "';";
    		rs=st.executeQuery(query);
    		while(rs.next())
    		{
    			Username=rs.getString("username");
    			Password=rs.getString("password");
    			if(username.equals(Username) && password.equals(Password))
    			{
    				flag=true;
    			}
    		}
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
        return flag;
      
    }
    public void updateAccount(String username,String password,String age,String phonenumber,String sex,String bloodgroup)
    {
        
        if(validateLogin(UserLogin.name,UserLogin.pass)){
        try
        {
            String query="UPDATE membersinfo SET username = '"+ username +"', password = '"+password+ "', age= '"+age+ "', sex = '" +sex + "' , phonenumber= '"+ phonenumber+ "', bloodgroup = '"+ bloodgroup +"' where username= '"+UserLogin.name+"';";
            st.executeUpdate(query);
            UserLogin.name="";
            UserLogin.pass="";
        }
        catch (Exception e)
        {
            System.out.println("Here 4"+e);
        }
    }
       else
       {
            JOptionPane.showMessageDialog(jf,"Something is wrong ","ERROR",JOptionPane.ERROR_MESSAGE);
       }
    }
    public String getDate()
    {
        String date="N/A";
        if(validateLogin(UserLogin.name,UserLogin.pass))
        {
          try
          {

            String query =  "select donatedate from membersinfo where username = '" + UserLogin.name + "'; ";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                date= rs.getString("donatedate");
               
            }

         }
          catch (Exception e)
          {
            System.out.println("Here 3"+e);
          }
        }
        return date;
    }
    public void setCurDate()
    {
        if(validateLogin(UserLogin.name,UserLogin.pass))
        {
          try
          {

            String query = "Update membersinfo set donatedate = CURDATE() where username = '"+ UserLogin.name+"' ;";
            st.executeUpdate(query);

         }
          catch (Exception e)
          {
            System.out.println("Here 1"+e);
          }
        }
    }

    public void setDateNA()
    {
        String n="N/A";
        if(validateLogin(UserLogin.name,UserLogin.pass))
        {
          try
          {

            String query = "Update membersinfo set donatedate = '"+n+"' where username = '"+ UserLogin.name+"' ;";
            st.executeUpdate(query);

         }
          catch (Exception e)
          {
            System.out.println("Here 2"+e);
          }
        }
    }
    public void getAllInfo()
    {
        
        int i=0,j=0;
        try
        {
            String query="select username , age ,sex , phonenumber ,bloodgroup, donatedate from membersinfo where username != '" + UserLogin.name+ " ' ;";
            rs=st.executeQuery(query);
            while(rs.next())
            {
               
                 Enlisted.data[i][j++] = rs.getString("username");
                 Enlisted.data[i][j++]= rs.getString("age");
                 Enlisted.data[i][j++] = rs.getString("sex");
                 Enlisted.data[i][j++] = rs.getString("phonenumber");
                 Enlisted.data[i][j++] = rs.getString("bloodgroup");
                 Enlisted.data[i][j++] = rs.getString("donatedate");
                 Enlisted.data[i][j++]=calculateMonths(rs.getString("donatedate"));
                 i++;
                 j=0;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
   public String calculateMonths(String date)
    {
        String targetDate="N/A";
        if(!date.equals("N/A")){
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
    }
        return targetDate;
    }
    public void changeRequest(String name,boolean signal)
    {
        try
          {

            String query = "Update membersinfo set requestsignal = '"+signal+"' where username ='"+ name+"' ;";
            st.executeUpdate(query);

         }
          catch (Exception e)
          {
            System.out.println("Here 2313"+e);
          }

    }
    public String checkSignal()
    {
        String signal="false";
        try
          {
            System.out.println(UserLogin.name);

            String query = "select requestsignal from membersinfo where username = '" + UserLogin.name + "';"; 
            rs=st.executeQuery(query);
            while(rs.next())
            {
                signal= rs.getString("requestsignal");
            }

         }
          catch (Exception e)
          {
            System.out.println("Here 2313"+e);
          }
          System.out.println(signal);
          return signal;

    }
    public void deleteAccount()
    {
        try
        {
            String query = "DELETE from membersinfo where username = '"+UserLogin.name+"'; ";
            st.executeUpdate(query);
        }
        catch(Exception e)
        {

        }

    }



}
