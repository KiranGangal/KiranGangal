package airline;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Reservation
 */
@WebServlet("servlet/reservations")
public class reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	 response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         String fromAirport = request.getParameter("fromAirport");
         String[] fromAirpot1 = fromAirport.split("-");
         String fromAirport2 = fromAirpot1[1];
         request.setAttribute("from",fromAirport2);
         String toAirport = request.getParameter("toAirport");
         String[] toAirpot1 = toAirport.split("-");
         String toAirport2 = toAirpot1[1];
         request.setAttribute("to",toAirport2);
         String dt = request.getParameter("depdt");
       
         
    	try {
           
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dt1=format1.parse(dt);
            DateFormat format2=new SimpleDateFormat("EEEE");
            String dayOfWeek=format2.format(dt1);
          
         
            java.sql.Date d = new java.sql.Date(dt1.getTime());
            request.setAttribute("date",d);
           
            Class.forName("com.mysql.jdbc.Driver");  //step 1 
			System.out.println("connection ready");
			java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb\",\"root\",\"kiran");
			System.out.println("Again ready");
			
			System.out.println(" again ready");
     	
            
            if(dayOfWeek.equals("Sunday")||dayOfWeek.equals("Tuesday")|| dayOfWeek.equals("Thursday")||dayOfWeek.equals("Friday")){
                if((fromAirport2.equals("SYD")&&toAirport2.equals("CCJ"))||(fromAirport2.equals("CCJ")&&toAirport2.equals("DEL"))||(fromAirport2.equals("JED")&&toAirport2.equals("DEL"))||(fromAirport2.equals("DEL")&&toAirport2.equals("DXB"))||(fromAirport2.equals("SYD")&&toAirport2.equals("DEL"))||(fromAirport2.equals("SYD")&&toAirport2.equals("KUL"))||(fromAirport2.equals("SYD")&&toAirport2.equals("JED"))||(fromAirport2.equals("CCJ")&&toAirport2.equals("KUL"))||(fromAirport2.equals("DXB")&&toAirport2.equals("JED"))||(fromAirport2.equals("KUL")&&toAirport2.equals("DXB"))||(fromAirport2.equals("JED")&&toAirport2.equals("KUL"))){
                 String nofly="No Flight available on  ";
                 request.setAttribute("nofly",nofly);
                 //System.out.println("No Flight available on  "+ d +"  from  "+fromAirport2+ "  to  " +toAirport2 );
                 //System.out.println();
                }else{
                      
                	   PreparedStatement pl =(PreparedStatement) con.prepareStatement("UPDATE airport SET DP_DT=?,AR_DT=? WHERE datecode=?");
                       pl.setDate(1,d);
                       pl.setDate(2,d);
                       pl.setInt(3,2);
                       pl.executeUpdate();
                      
                       PreparedStatement p = (PreparedStatement) con.prepareStatement("SELECT airport.A_ID,airport.DP_AP,airport.AR_AP,airport.DP_DT,airport.AR_DT,airport.DP_TM,airport.AR_TM,airport.F_NAME,PRICE.TOTAL,PRICE.CURR FROM airport JOIN PRICE ON airport.PR_ID=PRICE.P_ID WHERE airport.DP_AP=? and airport.AR_AP=? and airport.datecode=?;");
                       p.setString(1,fromAirport2);
                       p.setString(2,toAirport2);
                       p.setInt(3,1); 
                       ResultSet s;
                       s = (ResultSet) p.executeQuery();
                       while(s.next()){
                    	   
                    	   
                    	   request.setAttribute("DeprtureAirport",s.getString("airport.DP_AP"));
                    	   request.setAttribute("ArrivalAirport",s.getString("airport.AR_AP"));
                    	   request.setAttribute("Departure Date",s.getDate("airport.DP_DT")); 
                    	   request.setAttribute("Departure Time",s.getTime("airport.DP_TM"));
                           request.setAttribute("Arrival Time",s.getTime("airport.AR_TM"));
                    	   request.setAttribute("Flight Name",s.getString("airport.F_NAME"));
                    	   request.setAttribute("Price Total",s.getBigDecimal("price.TOTAL"));
                    	   request.setAttribute("Currency",s.getString("price.CURR"));
                    	   
                    	   //System.out.println(""+s.getString("AIRPORT.DP_AP")+" - "+s.getString("AIRPORT.AR_AP"));
                           //System.out.println("Departure Date :"+s.getDate("AIRPORT.DP_DT"));
                           if((s.getInt("airport.A_ID")==9)||(s.getInt("airport.A_ID")==31)||(s.getInt("airport.A_ID")==35)||(s.getInt("airport.A_ID")==37)){
                           	
                         	  Date dat=s.getDate("airport.AR_DT");
                         	  Calendar c = Calendar.getInstance(); 
                         	  c.setTime(dat); 
                         	  c.add(Calendar.DATE, 1);
                         	  dat = c.getTime();
                         	  SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
                               String dts=format3.format(dat);
                               request.setAttribute("Arrival Date",dts);
                         	   //System.out.println("Arrival Date :"+dts);
                            }else{
                            	request.setAttribute("Arrival Date",s.getDate("airport.AR_DT"));
                         	   //System.out.println("    Arrival Date :"+s.getDate("AIRPORT.AR_DT"));   
                            }
                           
                           //System.out.println("Departure  Time :"+s.getTime("AIRPORT.DP_TM")+"IST  "+"  Arrival Time :"+s.getTime("AIRPORT.AR_TM")+"IST  ");
                           //System.out.println("  Arrival Time :   "+s.getTime("AIRPORT.AR_TM")+"IST  ");
                           //System.out.println("Flight Name : "+s.getString("AIRPORT.F_NAME"));
                           //System.out.println("Total Price : "+s.getBigDecimal("PRICE.TOTAL")+" "+s.getString("PRICE.CURR"));
                           //System.out.println();
                         }
                       }
                     
                     PreparedStatement p3 = (PreparedStatement) con.prepareStatement("SELECT PRICE.BASIC,PRICE.TAX,PRICE.DISCOUNT,PRICE.CURR FROM PRICE JOIN airport ON airport.PR_ID=PRICE.P_ID WHERE airport.DP_AP=? and airport.AR_AP=? and datecode=?;");
                     p3.setString(1,fromAirport2);
                     p3.setString(2,toAirport2);
                     p3.setInt(3,1); 
                     ResultSet s;
                     s = (ResultSet) p3.executeQuery();
                     while(s.next()){
                    	 
                    	 //System.out.println("Price Details: ");
                    	 request.setAttribute("Basic Price",s.getBigDecimal("price.BASIC"));
                    	 request.setAttribute("Tax",s.getBigDecimal("price.TAX"));
                    	 request.setAttribute("Discount",s.getBigDecimal("price.DISCOUNT"));
                    	 request.setAttribute("Curr",s.getString("price.CURR"));
                         //System.out.println("Basic Fare : "+s.getBigDecimal("PRICE.BASIC")+",  TaX :"+s.getBigDecimal("PRICE.TAX")+", Discount: "+s.getBigDecimal("PRICE.DISCOUNT")+", Currency: "+s.getString("PRICE.CURR"));
                        
                       //System.out.println("-------------------------> ");
                     }
                   }
                
           else if(dayOfWeek.equals("Monday")|| dayOfWeek.equals("Wednesday")||dayOfWeek.equals("Saturday")){
             if((fromAirport2.equals("CCJ")&&toAirport2.equals("SYD"))||(fromAirport2.equals("DEL")&&toAirport2.equals("CCJ"))||(fromAirport2.equals("DEL")&&toAirport2.equals("JED"))||(fromAirport2.equals("DXB")&&toAirport2.equals("DEL"))||(fromAirport2.equals("DEL")&&toAirport2.equals("SYD"))||(fromAirport2.equals("KUL")&&toAirport2.equals("SYD"))||(fromAirport2.equals("JED")&&toAirport2.equals("SYD"))||(fromAirport2.equals("KUL")&&toAirport2.equals("CCJ"))||(fromAirport2.equals("JED")&&toAirport2.equals("DXB"))||(fromAirport2.equals("DXB")&&toAirport2.equals("KUL"))||(fromAirport2.equals("KUL")&&toAirport2.equals("JED"))){
            	 String nofly="No Flight available on ";
                 request.setAttribute("nofly",nofly);
            	 //System.out.println("No Flight available on  "+ d +"  from "+fromAirport2+ "  to  " +toAirport2 );
                 //System.out.println();   
             }else {
              
               PreparedStatement pl =(PreparedStatement) con.prepareStatement("UPDATE airport SET DP_DT=?,AR_DT=? WHERE datecode=?");
               pl.setDate(1,d);
               pl.setDate(2,d);
               pl.setInt(3,2);
               pl.executeUpdate();
              
               PreparedStatement p2 = (PreparedStatement) con.prepareStatement("SELECT airport.A_ID,airport.DP_AP,airport.AR_AP,airport.DP_DT,airport.AR_DT,airport.DP_TM,airport.AR_TM,airport.F_NAME,price.TOTAL,price.CURR FROM AIRPORT JOIN PRICE ON airport.PR_ID=PRICE.P_ID WHERE AIRPORT.DP_AP=? and airport.AR_AP=? and airport.datecode=?;");
               p2.setString(1,fromAirport2);
               p2.setString(2,toAirport2);
               p2.setInt(3,2); 
               ResultSet s;
               s = (ResultSet) p2.executeQuery();
               
               while(s.next()){
            	   request.setAttribute("DeprtureAirport",s.getString("airport.DP_AP"));
            	   request.setAttribute("ArrivalAirport",s.getString("airport.AR_AP"));
            	   request.setAttribute("Departure Date",s.getDate("airport.DP_DT"));
            	   request.setAttribute("Departure Time",s.getTime("airport.DP_TM"));
                   request.setAttribute("Arrival Time",s.getTime("airport.AR_TM"));
            	   request.setAttribute("Flight Name",s.getString("airport.F_NAME"));
            	   request.setAttribute("Price Total",s.getBigDecimal("price.TOTAL"));
            	   request.setAttribute("Currency",s.getString("price.CURR"));
            	   
            	   
                   //System.out.println(""+s.getString("AIRPORT.DP_AP")+" - "+s.getString("AIRPORT.AR_AP"));
                   //System.out.println("Departure Date :"+s.getDate("AIRPORT.DP_DT"));
                   if((s.getInt("airport.A_ID")==12)||(s.getInt("airport.A_ID")==16)||(s.getInt("airport.A_ID")==18)||(s.getInt("airport.A_ID")==30)){
                	
                	  Date dat=s.getDate("airport.AR_DT");
                	  Calendar c = Calendar.getInstance(); 
                	  c.setTime(dat); 
                	  c.add(Calendar.DATE, 1);
                	  dat = c.getTime();
                	  SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
                      String dts=format3.format(dat);
                      request.setAttribute("Arrival Date",dts);
                	   //System.out.println("Arrival Date :"+dts);
                   }else{
                	   request.setAttribute("Arrival Date",s.getDate("airport.AR_DT"));
                	   //System.out.println("    Arrival Date :"+s.getDate("AIRPORT.AR_DT"));   
                   }
                   //System.out.println("Departure  Time :"+s.getTime("AIRPORT.DP_TM")+"IST  "+"  Arrival Time :"+s.getTime("AIRPORT.AR_TM")+"IST  ");
                   //System.out.println("Arrival Time :   "+s.getTime("AIRPORT.AR_TM")+" IST");
                   //System.out.println("Flight Name : "+s.getString("AIRPORT.F_NAME"));
                   //System.out.println("Total Price : "+s.getBigDecimal("PRICE.TOTAL")+" "+s.getString("PRICE.CURR"));
                  // System.out.println();
                
               }
           }
             
             
             PreparedStatement p4 = (PreparedStatement) con.prepareStatement("SELECT price.BASIC,price.TAX,PRICE.DISCOUNT,price.CURR FROM PRICE JOIN airport ON AIRPORT.PR_ID=PRICE.P_ID WHERE airport.DP_AP=? and airport.AR_AP=? and datecode=?;");
             p4.setString(1,fromAirport2);
             p4.setString(2,toAirport2);
             p4.setInt(3,2); 
             ResultSet s;
             s = (ResultSet) p4.executeQuery();
             while(s.next()){
            	 
            	 //System.out.println("Price Details: ");
            	 request.setAttribute("Basic Price",s.getBigDecimal("price.BASIC"));
            	 request.setAttribute("Tax",s.getBigDecimal("price.TAX"));
            	 request.setAttribute("Discount",s.getBigDecimal("price.DISCOUNT"));
            	 request.setAttribute("Curr",s.getString("price.CURR"));
            	 //System.out.println("Basic Fare : "+s.getBigDecimal("PRICE.BASIC")+",  TaX :"+s.getBigDecimal("PRICE.TAX")+", Discount: "+s.getBigDecimal("PRICE.DISCOUNT")+", Currency: "+s.getString("PRICE.CURR"));
                
               //System.out.println("---------------------------------------->");
             }
           }
            

           
            
        	RequestDispatcher view = request.getRequestDispatcher("/Result.jsp");
            view.forward(request, response);
            return;
            
        } catch (Exception e) {
            System.out.println(e);
        }
    	
    	
}}


