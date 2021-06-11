import java.io.*;
import java.util.Scanner; 
  public class ProjectCompanyLockers { 
	 
	 
	  
	  private static final Reader Reader = null;
	void display()//Function  To display well come message
		{
			System.out.println("WELL COME TO 'COMPANY LOCKERS PVT. LTD.' ");
			System.out.println("Software Developed by:Kiran Gangal");
			System.out.println();
			System.out.println();
			
		} 
	  
	  
	  private static String username;
	  private static String password;	  
      public String getUsername() {
		return username;
	}

    //Function To Registration
	public static void Registration() 
	{
		// Try-catch Block	
	try
	//Creating BufferedReadered object
	{BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));          
    System.out.print("Enter username: ");

     username = reader.readLine();
    System.out.println("Your name is: " +username);
    
    System.out.print("Enter your Password: ");
     password = reader.readLine();
    
    System.out.println("Your password is: " + password);
	
	
    FileWriter writer = new FileWriter("G:\\SIMPLILEARN1\\projectwork1\\Company Lockerspvtltd\\Logindetails.txt");  
    BufferedWriter buffer = new BufferedWriter(writer);  
    if(buffer.equals(username))
    {
    	System.out.println("username already exist");
    }
    else {
    buffer.write(username);
    buffer.newLine();
    buffer.write(password);
    buffer.close();  
    System.out.println("Registration Success"); 
    System.out.println();
    System.out.println();
    Login();
    }
	}
	// Try-catch Block
    catch (IOException e) 
    {
        e.printStackTrace();
        System.out.println(e);
    }
	}
	
	
	//Function To Login
	public static void Login() throws IOException
	{
        System.out.println("Provide your Name and Password to Login");
    	 

     // Try-catch Block 		
        try {
        	//Creating BufferedReadered object
        	BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in)); 
                System.out.print("Enter username: ");
                String name = reader2.readLine();
                System.out.println("Your name is: " +name);
           
                System.out.print("Enter your Password: ");
                String pswd = reader2.readLine();
                System.out.println("Your password is: " + pswd);
                
                
                
                File file = new File("G:\\SIMPLILEARN1\\projectwork1\\Company Lockerspvtltd\\Logindetails.txt");
                
                BufferedReader br = new BufferedReader(new FileReader(file));
               
                String tempusername;
                String temppassword;
                while ((tempusername = br.readLine()) != null)
                  System.out.println("line 1 :"+tempusername);
                while ((temppassword = br.readLine()) != null)
                    System.out.println("line 2 :"+temppassword);
                 if((tempusername==name)&&(temppassword==pswd))
                 {
                	 System.out.println("Login Successful");
                	 option1();
                 }
                 else
                 {
                	 System.out.println("Login Successful");
                	 option1();
                 }
        
        }
     // Try-catch Block
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println(e);
        }
        
	} 

    	 		
		
	
	//Function To Option
	static void option()
 	{
 		System.out.println("Select Option ");
 		System.out.println("Press 1 to Register");
 		System.out.println("Press 2 to Login");
 		
 		
 		Scanner c = new Scanner(System.in);           
 		int choice = c.nextInt();                     
 		do {
 			switch (choice) {
 			case 1:
 				Registration();                         
 				break;
 			case 2:
 				try {
					Login();
				} catch (IOException e) {
					
					e.printStackTrace();
				}                             
 				break;
 			
 			default:
 				System.out.println("Invalid input");
 				break;
 			}
 			                        
 		} 
 		while (choice > 0 && choice <= 2);
    
    }  
	
	static void option1()
	{
		System.out.println("Select Option ");
		System.out.println("Press 1 to Create New File");
		System.out.println("Press 2 to Open file");
		System.out.println("Press 3 to Delete file");
		System.out.println("Press 4 to Search file");
		
		
		Scanner c = new Scanner(System.in);           
		int choice = c.nextInt();                     
		do {
			switch (choice) {
			case 1:
				CreateNewfile() ;                         
				break;
			case 2:
				
				Openfile();
				                             
				break;
             case 3:
				
            	 DeleteFile() ;
				                             
				break;
             
             case 4:
 				
            	 try {
                searchFiles() ;
				} 
            	 catch (Exception e) {
					
					e.printStackTrace();
				}
				                             
				break;
			
			default:
				System.out.println("Invalid input");
				break;
			}
			                        
		} 
		while (choice > 0 && choice <= 4);
  
  }  


// Function To Make New File
public static void CreateNewfile()
{
    String strPath = "", strName = "";

    // Try-catch Block
    try {

        // Creating BufferedReadered object
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        System.out.println("Enter the file name:");

        // Reading File name
        strName = br.readLine();
        System.out.println("Enter the file path:");

        // Reading File Path
        strPath = br.readLine();

        // Creating File Object
        File file1
            = new File(strPath + "" + strName + ".txt");

        // Method createNewFile() method creates blank
        // file.
        file1.createNewFile();
        br.close();
        
       
    }
        
    // Try-Catch Block
    catch (Exception ex1) {
    }
}
  
  
//Function To Make Open File
  public static void Openfile() 
  {
  
  try {
      //constructor of File class having file as argument  
      File file = new File("G:\\SIMPLILEARN1\\projectwork1\\Company Lockerspvtltd\\Logindetails.txt");
      //creates a buffer reader input stream  
      BufferedReader br = new BufferedReader(new FileReader(file));
      System.out.print("The file content is: ");
      int r = 0;
      while ((r = br.read()) != -1) {
        System.out.print((char) r);
     
      }
      br.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  
//Function To Make Delete File
  public static void DeleteFile() 
  {
	  try  
	  {         
	  File f= new File("G:\\SIMPLILEARN1\\projectwork1\\Company Lockerspvtltd\\newfile.txt");           //file to be delete  
	  if(f.delete())                      //returns Boolean value  
	  {  
	  System.out.println(f.getName() + " deleted");   //getting and printing the file name  
	  }  
	  else  
	  {  
	  System.out.println("failed");  
	  }  
	  }  
	  catch(Exception e)  
	  {  
	  e.printStackTrace();  
	  }  
	  }  
	  
//Function To Search File
  public static void searchFiles() throws Exception {
	    int i = 1;
	    BufferedReader reader = null;
	    Scanner input = null;
	    boolean fileFound = true;
	    while(i <= 5){
	        System.out.print("Enter a file name::::");
	        input = new Scanner(System.in);
	        if(input.hasNextLine()){
	            try {
	                File f = new File(input.nextLine());
	                reader = new BufferedReader(new FileReader(f));
	                String str = null;
	                while((str = reader.readLine()) != null){
	                    System.out.println(str);
	                }
	            } catch (FileNotFoundException e) {
	                System.out.println("File Not Found");
	                fileFound = false;
	                i++;
	                continue;
	            } catch (IOException e) {
	                System.out.println("IOException");
	                i++;
	                continue;
	            } catch (Exception e) {
	                System.out.println("Some Other Exception");
	                i++;
	                continue;
	            } finally{
	                if(fileFound)
	                    reader.close();
	            }
	        }

	        i++;
	    }

	}
  
  }