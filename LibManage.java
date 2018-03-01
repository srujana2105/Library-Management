import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

class studrec
{
	static ArrayList<student> al=new ArrayList<student>();
}
class bookrec
{
	static ArrayList<book> bl=new ArrayList<book>();
}
class book
{
	String bookno;
	String bookname;
	String author;
	boolean taken;
	book(String a,String n,String no)
	{
		bookno=a;
		bookname=n;
		author=no;
		taken=false;
	}
}
class student
{
	String admno;
	String name;
	String stbno;
	int token;
	student(String a,String n,int no)
	{
		admno=a;
		name=n;
		token=no;
		stbno="-";
	}
}
class CreBook extends Frame implements ActionListener
{
	TextField t1,t2,t3,t4;
	Button create;
	CreBook()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,300,50));
		setVisible(true);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
	 	setTitle("NEW BOOK ENTRY");
		Label l1=new Label("Enter The Book no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter The Name of The Book");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		Label l3=new Label("Enter The Name of The Author");l3.setFont(myFont);
		add(l3);
		t3=new TextField(30);t3.setFont(myFont);
		add(t3);
		Label l4=new Label("Enter The edition if present");l3.setFont(myFont);
		add(l4);
		t4=new TextField(30);t4.setFont(myFont);
		add(t4);

		create= new Button("Create");create.setFont(myFont);
		add(create);
		create.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
	public void actionPerformed(ActionEvent e) { dispose();
		String no= this.t1.getText();
		String n=this.t2.getText();
		String aut=this.t3.getText();
		String ed=this.t4.getText();
           	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:orcl","scott","2105");

Statement stmt=con.createStatement();

String q1="INSERT INTO books VALUES (?,?,?,?)";
PreparedStatement pstmt1 = con.prepareStatement(q1);
pstmt1.setString(1, no);
   pstmt1.setString(2, n);
pstmt1.setString(3, aut);
   pstmt1.setString(4, ed);
pstmt1.executeUpdate(); 
con.close();

}catch(Exception ex){ System.out.println(e);}
		new popup("NEW BOOK CREATED");
         }
}
class CreStuRec extends Frame implements ActionListener
{
	TextField t1,t2;
	Button create;
	CreStuRec()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
	 	setTitle("NEW STUDENT ENTRY");
		Label l1=new Label("Enter The admission no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter The Name of The Student");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		create= new Button("Create");create.setFont(myFont);
		add(create);
		
		create.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
	public void actionPerformed(ActionEvent e) {dispose(); 
		String no= this.t1.getText();
		String n=this.t2.getText();
           	student s=new student(no,n,0);
		studrec.al.add(s);  
		new popup("NEW STUDENT RECORD CREATED");
         }
}
class ModStuName extends Frame implements ActionListener
{
	TextField t1,t2;
	Button modify;
	ModStuName()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
	 	setTitle("MODIFY STUDENT");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The admission no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter new Name of The Student");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		modify= new Button("Modify");modify.setFont(myFont);
		add(modify);
		
		modify.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
	public void actionPerformed(ActionEvent e) { 
		dispose();int flag=0;
		String no= t1.getText();
		String n=t2.getText();
		Iterator<student> itr=studrec.al.iterator();
		student el;
		while(itr.hasNext())
		{
			el=itr.next();
			if((el.admno).equals(no))
			{flag=1;
			el.name=n;
			break;
			}
		} if(flag==0)
		{String s="\nNo such Admission number";
		new popup(s);}else
		new popup("STUDENT NAME MODIFIED");
         }
}
class ModBook extends Frame implements ActionListener
{
	TextField t1,t2,t3;
	Button modify;
	ModBook()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,60));
		setVisible(true);
	 	setTitle("MODIFY BOOK");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The book no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter new Name of The book");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		Label l3=new Label("Enter new author name of The book");l3.setFont(myFont);
		add(l3);
		t3=new TextField(30);t3.setFont(myFont);
		add(t3);
		modify= new Button("Modify");modify.setFont(myFont);
		add(modify);
		modify.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
	public void actionPerformed(ActionEvent e) { 
		dispose();int flag=0;
		String no= t1.getText();
		String n=t2.getText();
		String aut=t3.getText();
		Iterator<book> itr=bookrec.bl.iterator();
		book el;
		while(itr.hasNext())
		{
			el=itr.next();
			if((el.bookno).equals(no))
			{flag=1;
			el.bookname=n;
			el.author=aut;
			break;
			}
		} if(flag==0)
		{String s="\nNo such Book number";
		new popup(s);}else
		new popup("BOOK DETAILS MODIFIED");
         }
}
class DelStuRec extends Frame implements ActionListener
{
	TextField t1,t2;
	Button delete;
	DelStuRec()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
	 	setTitle("DELETE STUDENT RECORD");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The admission no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		delete= new Button("Delete");delete.setFont(myFont);
		add(delete);
		delete.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
	public void actionPerformed(ActionEvent e) { 
		dispose();int flag=0;
		String no= t1.getText();
		Iterator<student> itr=studrec.al.iterator();
		student el;
		while(itr.hasNext())
		{
			el=itr.next();
			if((el.admno).equals(no))
			{flag=1;
			itr.remove();
			break;
			}
		} 
		if(flag==0)
		{String s="\nNo such Admission number";
		new popup(s);}else
		new popup("STUDENT RECORD DELETED");
         }
}
class DelBook extends Frame implements ActionListener
{
	TextField t1,t2;
	Button delete;
	DelBook()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
	 	setTitle("DELETE BOOK");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The book no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		delete= new Button("Delete");delete.setFont(myFont);
		add(delete);
		delete.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
	public void actionPerformed(ActionEvent e) { int flag=0;
		dispose();
		String no= t1.getText();
		Iterator<book> itr=bookrec.bl.iterator();
		book el;
		while(itr.hasNext())
		{
			el=itr.next();
			if((el.bookno).equals(no))
			{flag=1;
			itr.remove();
			break;
			}
		} 
		if(flag==0)
		{String s="\nNo such Book number";
		new popup(s);}else
		new popup("BOOK DELETED");
         }
}
class SpecStuRec extends Frame implements ActionListener
{
	TextField tf,tf5,tf6,tf7,tf8;
	Label l1,l2,l3,l4;
	SpecStuRec()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,40));
		setTitle("SPECIFIC STUDENT RECORD");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setSize(800,800);
		Label l=new Label("Enter adm. no.:");l.setFont(myFont);add(l);
		tf=new TextField(10);tf.setFont(myFont);
		add(tf);
		l1=new Label("Adm. no.");l1.setFont(myFont);
		l2=new Label("Name:");l2.setFont(myFont);
		l3=new Label("No. of Books:");l3.setFont(myFont);
		l4=new Label("Book No.:");l4.setFont(myFont);
		tf5=new TextField(10);tf5.setFont(myFont);
		tf6=new TextField(10);tf6.setFont(myFont);
		tf7=new TextField(10);tf7.setFont(myFont);
		tf8=new TextField(10);tf8.setFont(myFont);
		add(l1);add(tf5);
		add(l2);add(tf6);
		add(l3);add(tf7);
		add(l4);add(tf8);
		tf.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
	}
	public void actionPerformed(ActionEvent e)
	{
		int flag=0;
		String no=tf.getText();
		Iterator<student> itr=studrec.al.iterator();
		student el;
		while(itr.hasNext())
		{
			el=itr.next();
			if((el.admno).equals(no))
			{
			flag=1;
			tf5.setText(""+el.admno);
			tf6.setText(""+el.name);
			tf7.setText(""+el.token);
			tf8.setText(""+el.stbno);
			break;
			}
		}
		if(flag==0)
		{String s="\nNo such Admission number";
		new popup(s);}
	}
}
class SpecBook extends Frame implements ActionListener
{
	TextField tf,tf5,tf6,tf7,tf8;
	Label l1,l2,l3,l4;
	SpecBook()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,40));
		setTitle("SPECIFIC BOOK DETAILS");
		setSize(800,800);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l=new Label("Enter book no.:");l.setFont(myFont);add(l);
		tf=new TextField(10);tf.setFont(myFont);
		add(tf);
		l1=new Label("Book no.");l1.setFont(myFont);
		l2=new Label("Book Name:");l2.setFont(myFont);
		l3=new Label("author:");l3.setFont(myFont);
		l4=new Label("taken:");l4.setFont(myFont);
		tf5=new TextField(10);tf5.setFont(myFont);
		tf6=new TextField(10);tf6.setFont(myFont);
		tf7=new TextField(10);tf7.setFont(myFont);
		tf8=new TextField(10);tf8.setFont(myFont);
		add(l1);add(tf5);
		add(l2);add(tf6);
		add(l3);add(tf7);
		add(l4);add(tf8);
		tf.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
	}
	public void actionPerformed(ActionEvent e)
	{
		int flag=0;
		String no=tf.getText();
		Iterator<book> itr=bookrec.bl.iterator();
		book el;
		while(itr.hasNext())
		{
			el=itr.next();
			if((el.bookno).equals(no))
			{
			flag=1;
			tf5.setText(""+el.bookno);
			tf6.setText(""+el.bookname);
			tf7.setText(""+el.author);
			tf8.setText(""+el.taken);
			break;
			}
		}
		if(flag==0)
		{String s="\nNo such book number";
		new popup(s);}
	}
}
class AllStuRec extends Frame
{
	AllStuRec()
	{
		if(studrec.al.size()==0)
		new popup("NO STUDENT DETAILS RECORDED");
		else{
		setVisible(true);
		setSize(800,800);
		setTitle("ALL STUDENTS RECORD");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setLayout(new GridLayout((studrec.al.size()+1),3));
		Label l1=new Label("Admission no.");l1.setFont(myFont);add(l1);
		Label l2=new Label("Name");l2.setFont(myFont);add(l2);
		Label l3=new Label("No. of books");l3.setFont(myFont);add(l3);
		Iterator<student> itr=studrec.al.iterator();
		while(itr.hasNext())
		{
			student el=itr.next();
			Label l6=new Label(el.admno);l6.setFont(myFont);add(l6);
			Label l7=new Label(el.name);l7.setFont(myFont);add(l7);
			Label l9=new Label(""+el.token);l9.setFont(myFont);add(l9);
			
		}
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); } 
	}
}
class popup extends Frame
{
	String str;
	popup(String s)
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setTitle("NOTICE");
		setSize(600,200);
		str=s;
		Label l=new Label(str);l.setFont(myFont);
		add(l);	
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
	} 
}	
class BookIssue extends Frame implements ActionListener
{
	TextField tf1,tf2;
	BookIssue()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setTitle("BOOK ISSUE");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setSize(800,800);
		Label l1=new Label("Enter Book no.:");l1.setFont(myFont);
		add(l1);
		tf1=new TextField(10);tf1.setFont(myFont);
		add(tf1);
		Label l2=new Label("Enter Student Admission no.:");l2.setFont(myFont);
		add(l2);
		tf2=new TextField(10);tf2.setFont(myFont);
		add(tf2);
		Button b=new Button("Issue");b.setFont(myFont);
		add(b);
		b.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
	} 
	public void actionPerformed(ActionEvent e)
	{
		int flag=0,found=0;
		String s="";
		String bo=tf1.getText();
		String st=tf2.getText();
		Iterator<book> itr=bookrec.bl.iterator();
		while(itr.hasNext() && flag==0)
		{
			book el=itr.next();
			if(el.bookno.equals(bo))
			{
				flag=1;
				if(!el.taken)
				{
					Iterator<student> itr1=studrec.al.iterator();
					while(itr.hasNext() && found==0)
					{
						student sl=itr1.next();
						if(sl.admno.equals(st))
						{
							found=1;
							if(sl.token==0)
							{
								sl.token=1;
								sl.stbno=bo;
								el.taken=true;
								s=s+"\nbook issued successfully.\n Note: Write current date in backside of book and submit within 15 days fine Rs. 1 for each day after 15 days period";
			
							}
							else{s=s+"\nLast book was not returned!";}
						}
					}
					if(found==0)
					{s=s+"\nNo such student admission number";}
				}
				else{s=s+"\n Book is with someone else";}
			}
		}
		if(flag==0)
		{s=s+"\nNo such book number";}
		new popup(s);
	}
}
class BookDeposit extends Frame implements ActionListener
{
	TextField tf1,tf2,tf3;
	BookDeposit()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,60));
		setTitle("BOOK DEPOSIT");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setSize(800,800);
		Label l1=new Label("Enter Book no.:");l1.setFont(myFont);
		add(l1);
		tf1=new TextField(10);tf1.setFont(myFont);
		add(tf1);
		Label l2=new Label("Enter Student Admission no.:");l2.setFont(myFont);
		add(l2);
		tf2=new TextField(10);tf2.setFont(myFont);
		add(tf2);
		Label l3=new Label("Book Deposited in number of days:");l3.setFont(myFont);
		add(l3);
		tf3=new TextField(10);tf3.setFont(myFont);
		add(tf3);
		Button b=new Button("Deposit");b.setFont(myFont);
		add(b);
		b.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
	} 
	public void actionPerformed(ActionEvent e)
	{
		int flag=0,found=0;
		String s="";
		String bo=tf1.getText();
		String st=tf2.getText();
		int n=0;
		n=Integer.parseInt(tf3.getText());
		Iterator<book> itr=bookrec.bl.iterator();
		while(itr.hasNext() && flag==0)
		{
			book el=itr.next();
			if(el.bookno.equals(bo))
			{
				flag=1;
				if(el.taken)
				{
					Iterator<student> itr1=studrec.al.iterator();
					while(itr.hasNext() && found==0)
					{
						student sl=itr1.next();
						if(sl.admno.equals(st))
						{
							found=1;
							if(sl.token==1)
							{
								sl.token=0;
								sl.stbno="";
								el.taken=false;
								n-=15;
								s=s+"\nbook deposited successfully.\n Note: Fine to be paid:  Rs."+n;
							}
							else{s=s+"\nThere is no book issued on your name";}
						}
					}
					if(found==0)
					{s=s+"\nNo such student admission number";}
				}
				else{s=s+"\n Book is not issued.Wrong book number";}
			}
		}
		if(flag==0)
		{s=s+"\nNo such book number";}
		new popup(s);
	}
}
class AllBooks extends Frame
{
	AllBooks()
	{
		if(bookrec.bl.size()==0)
		new popup("NO BOOKS IN LIBRARY!");
		else{
		setVisible(true);
		setSize(800,800);
		setTitle("ALL BOOKS");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setLayout(new GridLayout((bookrec.bl.size()+1),3));
		Label l1=new Label("Book no.");l1.setFont(myFont);add(l1);
		Label l2=new Label("Book Name");l2.setFont(myFont);add(l2);
		Label l3=new Label("Author Name");l3.setFont(myFont);add(l3);
		Label l4=new Label("taken");l4.setFont(myFont);add(l4);
		Iterator<book> itr=bookrec.bl.iterator();
		while(itr.hasNext())
		{
			book el=itr.next();
			Label l5=new Label(el.bookno);l5.setFont(myFont);add(l5);
			Label l6=new Label(el.bookname);l6.setFont(myFont);add(l6);
			Label l7=new Label(el.author);l7.setFont(myFont);add(l7);
			Label l8=new Label(""+el.taken);l8.setFont(myFont);add(l8);
			
		}
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  }
	}
}	
class AdminMenu extends Frame implements ActionListener
{
	AdminMenu()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,20));
		setVisible(true);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setTitle("ADMINISTRATOR MENU");
		Button a[]=new Button[11];
		a[0]=new Button("CREATE STUDENT RECORD");
		a[1]=new Button("DISPLAY ALL STUDENTS RECORD");
		a[2]=new Button("DISPLAY SPECIFIC STUDENT RECORD");
		a[3]=new Button("MODIFY STUDENT RECORD");
		a[4]=new Button("DELETE STUDENT RECORD");
		a[5]=new Button("CREATE BOOK");
		a[6]=new Button("DISPLAY ALL BOOKS");
		a[7]=new Button("DISPLAY SPECIFIC BOOK");
		a[8]=new Button("MODIFY BOOK ");
		a[9]=new Button("DELETE BOOK ");
		a[10]=new Button("BACK TO MAIN MENU");
		for(int j=0;j<11;j++)
		{
			a[j].setFont(myFont);
			add(a[j]);
			a[j].addActionListener(this);	
		}
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
	}
	public void actionPerformed(ActionEvent e) { 
	String str=e.getActionCommand();
	if(str.equals("CREATE STUDENT RECORD")){new CreStuRec();}
	else if(str.equals("DISPLAY ALL STUDENTS RECORD")){new AllStuRec();}
	else if(str.equals("DISPLAY SPECIFIC STUDENT RECORD")){new SpecStuRec();}
	else if(str.equals("MODIFY STUDENT RECORD")){new ModStuName();}
	else if(str.equals("DELETE STUDENT RECORD")){new DelStuRec();}
	else if(str.equals("CREATE BOOK")){new CreBook();}
	else if(str.equals("DISPLAY ALL BOOKS")){new AllBooks();}
	else if(str.equals("DISPLAY SPECIFIC BOOK")){new SpecBook();}
	else if(str.equals("MODIFY BOOK ")){new ModBook();}
	else if(str.equals("DELETE BOOK ")){new DelBook();}
	else if(str.equals("BACK TO MAIN MENU")){dispose();//setVisible(false);
	}
    }  
}	
class MainFr extends Frame implements ActionListener
{
	MainFr()
	{
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setSize(800,800);
		setVisible(true);
		Font myFont1 = new Font("Times New Roman", Font.BOLD,42);
		setTitle("MAIN MENU");
		Label l=new Label("LIBRARY ORGANISATION");
		l.setFont(myFont1);
		add(l);
		Button b1=new Button("BOOK ISSUE");b1.setFont(myFont1);
		Button b2=new Button("BOOK DEPOSIT");b2.setFont(myFont1);
	  	Button b3=new Button("ADMINISTRATOR MENU");b3.setFont(myFont1);
	  	Button b4=new Button("EXIT");b4.setFont(myFont1);
	  	add(b1);
		add(b2);
		add(b3);
		add(b4);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
		}
	

public void actionPerformed(ActionEvent e) {
      		if(e.getActionCommand().equals("BOOK ISSUE")){new BookIssue();}
		else if(e.getActionCommand().equals("BOOK DEPOSIT")){new BookDeposit();}
		else if(e.getActionCommand().equals("ADMINISTRATOR MENU")){new AdminMenu();}
		else if(e.getActionCommand().equals("EXIT")){dispose();setVisible(false);}
    }
		
}
class LibManage
{
public static void main(String ar[])
{
	//intro();
	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:orcl","scott","2105");

Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery("select * from emp");
while(rs.next())
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

con.close();

}catch(Exception e){ System.out.println(e);}

	new MainFr();
}
}