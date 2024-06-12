/*
  Hospital Database Management System
  
  This program provides a comprehensive management system for hospital databases.
  It allows users to perform various tasks including adding and removing doctors,
  listing attending staff, nurses, residents & interns, patients, and room details,
  as well as viewing the highest-paid doctors.
  
 */
 
import java.sql.*;
import java.util.Scanner;

public class HospitalDBMSystem{

    private static Connection conn;

    public static void main(String[] args) {
        
        Scanner keyb = new Scanner(System.in);

        establishConnection();

        int num;
        do {
            System.out.println("\n------------------------------------------------");
            System.out.println("Welcome to Hospital Albadawi Database Management System."); 
            System.out.println("\nYou are currently logged in as DBMSadminUser19267.");
            System.out.println();
            System.out.println("\nWhat do you like to do today? ");
            System.out.println("\n1- Add New Doctor");
            System.out.println("2- Remove Doctor");
            System.out.println("3- List Attendings");
            System.out.println("4- List Nurses");
            System.out.println("5- List Residents & Interns");
            System.out.println("6- List Patients");
            System.out.println("7- List Emergency Room");
            System.out.println("8- List Operating Room");
            System.out.println("9- View Highest Paid Doctors");
            System.out.println("0- Log Out");

            System.out.print("\nEnter your choice: ");
            num = keyb.nextInt();
            keyb.nextLine();

            switch (num) {

                case 1:
                    addDoctor(keyb);
                    break;

                case 2:
                    removeDoctor(keyb);
                    break;

                case 3:
                    listAttendings();
                    break;

                case 4:
                    listNurses();
                    break;

                case 5:
                    listResidentsInterns();
                    break;

                case 6:
                    listPatients();
                    break;

                case 7:
                    listEmergencyRoom();
                    break;

                case 8:
                    listOperatingRoom();
                    break;

                case 9:
                    viewHighestPaidDoctors();
                    break;

                case 0:
                    System.out.println("Successfully Logged out.");
                    break;

                default:
                    System.out.println("Try Again, ONLY 0 - 9");
            }
        } while (num != 0);

        // Close database connection
        closeConnection();
    }

    private static void establishConnection() {
        try {
            String url = "jdbc:mysql://cs.neiu.edu:3306/SP24CS3151_aalbadawi?serverTimezone=UTC";
            String username = "SP24CS3151_aalbadawi";
            String password = "aalbadawi693182";
            conn = DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } 
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private static void addDoctor(Scanner keyb) {
     
        try {
            
            System.out.print("Enter ID (integers only): "); 
            int docId = keyb.nextInt();
            
            System.out.print("Enter Last Name: "); 
            String docLName = keyb.next();
            
            System.out.print("Enter First Name: "); 
            String docFName = keyb.next();
            
            System.out.print("Enter Pager Number (integers only. Ommit any dashes/hyphens): "); 
            int pagerNum = keyb.nextInt();
            
            System.out.print("Enter age: "); 
            int docAge = keyb.nextInt();
            
            System.out.print("Enter Sex (M or F): "); 
            String docSex = String.valueOf(keyb.next().charAt(0));keyb.nextLine();
            
            System.out.print("Enter Specialty: "); 
            String docSpecialty = keyb.nextLine();
            
            System.out.print("Enter DOB (YYYY-MM-DD, include hypens/dashes): "); 
            String docDOB = keyb.nextLine();
            
            System.out.print("Enter Salary (Format: 000000.00): "); 
            String docSal = keyb.nextLine();
            
            System.out.print("Enter Workday (Separate by comma): "); 
            String docWDays = keyb.nextLine();
            
            Statement stmt = conn.createStatement();
         
            String update = "insert into Attendings values (" + docId + ", '" + docLName + "', '" + docFName + "', " + pagerNum + ", " + docAge + ", '" + docSex + "', '" + docSpecialty + "', '" + docDOB + "', '" + docSal + "', '" + docWDays + "')";     
            
            stmt.executeUpdate(update);
            
            System.out.println("Doctor added successfully.");

            
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    private static void removeDoctor(Scanner keyb) {
       
        try {
              System.out.print("Enter the ID of the doctor you want to remove: ");
              int Id = keyb.nextInt();
              
              Statement stmt = conn.createStatement();
             
              String remove = "DELETE FROM Attendings WHERE staffId = " + Id + " ";
              
              // Execute the deletion
              int rowsAffected = stmt.executeUpdate(remove);
              
              if (rowsAffected > 0) {
                  System.out.println("Doctor with ID " + Id + " removed successfully.");
              } 
              else {
                  System.out.println("No doctor found with ID " + Id + ".");
              }
        } 
        catch (SQLException sqle) {
              sqle.printStackTrace();
        }
        
    }

    private static void listAttendings() {
       
       try{ 
             Statement stmt = conn.createStatement();
               
             String query = "select * from Attendings";
               
             ResultSet rs = stmt.executeQuery(query);
             
               while(rs.next()){
               
                  int id = rs.getInt("staffId");
                  String ln = rs.getString("lName");
                  String fn = rs.getString("fName");
                  int pagNum = rs.getInt("pagerNum");
                  int age = rs.getInt("age");
                  String sex = rs.getString("sex");
                  String splty = rs.getString("specialty");
                  String DOB = rs.getString("DOB");
                  String sal = rs.getString("salary");
                  String days = rs.getString("workDays");
                  
                  
                  
                  System.out.println("ID: " + id + "\tName: " + fn + " " + ln + "\tPager: " + pagNum + "\tAge: " + age + "\tSex: " + sex + "\tSpecialty: " + splty + "\tDOB: " + DOB + "\tSalary: " + sal + "\t Days On duty: " + days + "\n");        
               } 
         
         }
         catch (SQLException sqle) {
              sqle.printStackTrace();
         }


    }

    private static void listNurses() {
        // Implement the logic to list nurses
        try{ 
             Statement stmt = conn.createStatement();
               
             String query = "select * from Nurses";
               
             ResultSet rs = stmt.executeQuery(query);
             
               while(rs.next()){
               
                  int id = rs.getInt("staffId");
                  String ln = rs.getString("lName");
                  String fn = rs.getString("fName");
                  int pagNum = rs.getInt("pagerNum");
                  int age = rs.getInt("age");
                  String sex = rs.getString("sex");
                  String type = rs.getString("nurseType");
                  String DOB = rs.getString("DOB");
                  String sal = rs.getString("salary");
                  String days = rs.getString("workDays");
                  
                  
                  
                  System.out.println("ID: " + id + "\tName: " + fn + " " + ln + "\tPager: " + pagNum + "\tAge: " + age + "\tSex: " + sex + "\tType of Nurse: " + type +"\tDOB: " + DOB + "\tSalary: " + sal + "\t Days On duty: " + days + "\n");        
               } 
         
         }
         catch (SQLException sqle) {
              sqle.printStackTrace();
         } 
        
    }

    private static void listResidentsInterns() {
       
       try{ 
             Statement stmt = conn.createStatement();
               
             String query = "select * from Residents_Interns";
               
             ResultSet rs = stmt.executeQuery(query);
             
               while(rs.next()){
               
                  int id = rs.getInt("ID");
                  String ln = rs.getString("lName");
                  String fn = rs.getString("fName");
                  String ssnStr = rs.getString("SSN");
                     // Remove dashes/hyphens from SSN
                     String ssn = ssnStr.replaceAll("-", "");
                     int SSN = Integer.parseInt(ssn);
                  int age = rs.getInt("age");
                  String sex = rs.getString("sex");
                  String grad = rs.getString("Graduted_College");
                  String sal = rs.getString("salary");
                  String days = rs.getString("workDays");
                  String stat = rs.getString("status");
                  
                  
                  
                  System.out.println("ID: " + id + "\tName: " + fn + " " + ln + "\tSSN: " + SSN + "\tAge: " + age + "\tSex: " + sex + "\tGrduated Or Still in College: " + grad + "\tSalary: " + sal + "\t Days On duty: " + days + "\tStatus: " + stat + "\n");        
               } 
         
         }
         catch (SQLException sqle) {
              sqle.printStackTrace();
         }
    }

    private static void listPatients() {
       
       try{ 
             Statement stmt = conn.createStatement();
               
             String query = "select * from Patients";
               
             ResultSet rs = stmt.executeQuery(query);
             
               while(rs.next()){
               
                  int id = rs.getInt("PatientId");
                  String ln = rs.getString("lName");
                  String fn = rs.getString("fName");
                  int age = rs.getInt("age");
                  String sex = rs.getString("sex");
                  String insur = rs.getString("insurance");
                  int doc = rs.getInt("Doctor");
                  int nurse = rs.getInt("Nurse");
                  int res = rs.getInt("Resident");
                  String con = rs.getString("CurrentCondition");
                  String phone = rs.getString("phone");
                  
                  
                  
                  System.out.println("ID: " + id + "\tName: " + fn + " " + ln + "\tAge: " + age + "\tSex: " + sex + "\tInsurance: " + insur + "\tDoctor ID: " + doc + "\tNurse ID: " + nurse + "\tResident ID: " + res + "\tCondition: " + con +"\tPhone: " + phone + "\n");        
               } 
         
         }
         catch (SQLException sqle) {
              sqle.printStackTrace();
         }
    }

    private static void listEmergencyRoom() {
       
       try{ 
             Statement stmt = conn.createStatement();
               
             String query = "select * from EmergencyRoom";
               
             ResultSet rs = stmt.executeQuery(query);
             
               while(rs.next()){
               
                  String ln = rs.getString("lName");
                  String fn = rs.getString("fName");
                  int age = rs.getInt("age");
                  String diag = rs.getString("diagnosis");
                  int admitNum = rs.getInt("admitNum");
                  int assignNurse = rs.getInt("assignedNurse");
                  int doc = rs.getInt("doctor");
                  int intern = rs.getInt("intern");
                  int cStatus = rs.getInt("caseStatus");
                  String notes = rs.getString("notes");
                  
                  
                  
                  System.out.println("\tName: " + fn + " " + ln + "\tAge: " + age + "\tDiagnosis: " + diag + "\tAdmit Number: " + admitNum + "\tAssigned Nurse ID: " + assignNurse + "\tDoctor ID: " + doc + "\tIntern ID: " + intern +"\tCase Status: " + cStatus + "\tNotes: " + notes + "\n");        
               } 
         
         }
         catch (SQLException sqle) {
              sqle.printStackTrace();
         }
    }

    private static void listOperatingRoom() {
        
        try{ 
             Statement stmt = conn.createStatement();
               
             String query = "select * from OperatingRoom";
               
             ResultSet rs = stmt.executeQuery(query);
             
               while(rs.next()){
               
                  int room = rs.getInt("room");
                  int surgeon = rs.getInt("surgeon");
                  String operation = rs.getString("operation");
                  int pID = rs.getInt("patientId");
                  int nID = rs.getInt("nurse");
                  int rID = rs.getInt("resident");
                  String sTime = rs.getString("startTime");
                  String eTime = rs.getString("EndTime");
                  String stat = rs.getString("CurrentStatus");
                  
                  
                  
                  System.out.println("OR Room Number: " + room + "\tSurgeon ID: " + surgeon + "\tOperation/Prodecure: " + operation + "\tPatient ID: " + pID + "\tNurse ID: " + nID + "\tResident ID: " + rID + "\tStarting Time: " + sTime + "\tEnd Time: " + eTime + "\tStatus: " + stat + "\n");        
               } 
         
         }
         catch (SQLException sqle) {
              sqle.printStackTrace();
         }
    }

    private static void viewHighestPaidDoctors() {
       
       try{ 
             Statement stmt = conn.createStatement();
               
             String query = "select * from HighestPaid";
               
             ResultSet rs = stmt.executeQuery(query);
             
               while(rs.next()){
               
                  int id = rs.getInt("staffId");
                  String ln = rs.getString("lName");
                  String fn = rs.getString("fName");
                  int age = rs.getInt("age");
                  String sal = rs.getString("salary");
                 
                  
                  
                  
                  System.out.println("ID: " + id + "\tName: " + fn + " " + ln + "\tAge: " + age + "\tSalary: " + sal + "\n");        
               } 
         
         }
         catch (SQLException sqle) {
              sqle.printStackTrace();
         }
        
    }
}
