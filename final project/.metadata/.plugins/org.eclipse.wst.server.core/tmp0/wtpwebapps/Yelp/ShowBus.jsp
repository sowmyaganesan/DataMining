<%@ page import="java.sql.*"%>
<%@ page import="javax.imageio.*"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="java.io.ByteArrayInputStream"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.xml.bind.*"%>
<%@ page import="com.yelp.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.sql.*,java.util.*,java.text.*,java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%! 
	
	private StringBuilder votes = new StringBuilder();
	private List<YelpDataSet1> x;
	byte[] img = new byte[1];
	private List<BusinessDetail> list_business_detail = new ArrayList<BusinessDetail>();
	
%>	
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hot meals steward</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<style media="all" type="text/css">@import "css/all.css";</style>
	<!--[if lt IE 7]><style media="screen" type="text/css">@import "css/ie6.css";</style><![endif]-->
	 <script defer type="text/javascript" src="pngfix.js"></script>
	<div id="page">
		<div id="header">
			<div class="background">
				<h1><a href="index.html">Hot Meals Steward</a></h1>
				<ul>
					
					<li class="active"><a href="about.html">About Us</a></li>
					
				</ul>
			</div>
		</div>
		
		
		<div id="content">
			<div id="leftcol">
				<div class="block">
					<div class="block-top"></div>
					<div class="block-content">
						
						<%
        				
		List<BusinessDetail> list  =  (List<BusinessDetail>)  request.getSession().getAttribute("showbb");
		if(list == null)
			System.out.println(" is is nulll");
		else
			System.out.println(" is not null");
		String userid = request.getParameter("id");
		for(BusinessDetail d : list)
		{
			if(d.getUserid().equals(userid))
			{
				out.println("<table>");
				out.println("<tr><th>Business id</th><th>Business Name</th><th>Stars</th><th>Address</th></tr>");
				List<BusinessData> data = d.getBusinessData();
				for(BusinessData x : data)
				{
					if(Float.parseFloat(x.getBusinessStars()) < 3.0 ) 
						continue;
					out.println("<tr>");
					out.println("<td>" + x.getBusinessID() + "</td>" + "<td>" + x.getBusinessName() + 
							"</td>" +  "<td>" + x.getBusinessStars()  + "</td>"  + "<td>" +  x.getBusinessAddress() + "</td>" );
					out.println("</tr>");
				}
				out.println("</table>");
				break;
			}
		}

		%>
					</div>
					<div class="block-bottom"></div>
				</div>

    	</td>
    	<td valign="top" width="20%" class="pageright">
    	
    		
    	</td>
    </tr>
    <tr>
    	<td colspan=3 class="pagebottom" align="center">
        
        
         
    </td>
    </tr>
    </table>
        
    </form>

			</div>
			
		</div>
		<div id="footer">
			<p>©2013 FoodSpotting. Design by Team HeadStart</a> </a></p>
			<ul>
				<li><a href="index.html">Home</a></li>
				<li><a href="about.html">About Us</a></li>
				<li><a href="services.html">Services </a></li>
				<li><a href="contact.html">Contact us</a></li>
			</ul>
		</div>
	</div>
</body>
</html>

</head>

