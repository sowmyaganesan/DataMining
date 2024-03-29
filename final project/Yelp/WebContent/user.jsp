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
						<h2><a href="/Yelp/CompareUsersServlet" style="color: #C0D765" >User Recommendations</a></h2>
					</div>
					<div class="block-bottom"></div>
				</div>
<table>
    		<tr>
    				<% 
    		 			if(request.getAttribute("allusers") != null)
    		 			{
    				%>
    				
    					<tr>
    						<th>user id</th><th>stars</th><th>reviews</th><th>funny</th><th>useful</th><th>cool</th><th align="left">name</th>
    					</tr>
    				<% 
    					} 
    				%>
    				<% 
    		 			if(request.getAttribute("business") != null)
    		 			{
    				%>
    				
    					<tr>
    						<th>business</th><th>city</th><th>state</th><th>stars</th><th>review count</th></th><th>category</th>
    					</tr>
    				<% 
    					} 
    				%>
    				<% 
    		 			if(request.getAttribute("bustype") != null)
    		 			{
    				%>
    				
    					<tr>
    						<th>Name</th><th>Address</th><th>Stars</th><th>Review Count</th>
    					</tr>
    				<% 
    					} 
    				%>
    				<% 
    		 			if(request.getAttribute("compuser") != null)
    		 			{
    		 				list_business_detail = (List<BusinessDetail>) request.getAttribute("compuser");
    		 				
    		 				request.setAttribute("showb", list_business_detail);
    		 				request.getSession().setAttribute("showbb", list_business_detail);
    				%>
    				
    					<tr>
    						<th>User id</th><th>Name</th><th>URL</th>
    					</tr>
    				<% 
    					} 
    				%>
    					
					<c:forEach items="${allusers}" var="user" varStatus="loop">
					<tr>
						<td>"${user.getUserid()}"</td>
						<td>"${user.getAveragestars().substring(0, 3)}"</td>
						<td align="center">"  ${user.getReviewcount()}"</td>
						<td align="center">"${user.getVote().getFunny()}"</td>
						<td align="center">"${user.getVote().getUseful()}"</td>
						<td align="center">"${user.getVote().getCool()}"</td>
						<td>"${user.getName()}"</td>
					</tr>
        			</c:forEach>
        			<c:forEach items="${business}" var="item" varStatus="loop">
					<tr>
						<td>"${item.getName()}"</td>
						<td>"${item.getCity()}"</td>
						<td>"${item.getState()}"</td>
						<td>"${item.getStars()}"</td>
						<td>"${item.getReviewcount()}"</td>
						<td>"${item.getCategories()}"</td>
						
					</tr>
        			</c:forEach>
        			<c:forEach items="${bustype}" var="item" varStatus="loop">
					<tr>
						<td>"${item.getbusiness_name()}"</td>
						<td>"${item.getbusiness_address()}"</td>
						<td>"${item.getstars()}"</td>
						<td>"${item.getreview_count()}"</td>
					</tr>
        			</c:forEach>
        			<c:forEach items="${compuser}" var="item" varStatus="loop">
					<tr>
						<td><a href ="/Yelp/ShowBus.jsp?id=${item.getUserid()}"> <c:out value =  "${item.getUserid()}"> </c:out> </a> </td>
						<td>"${item.getUserName()}"</td>
						<td><a href = <c:out value="${item.getUserUrl()}" /> >  <c:out value="${item.getUserUrl()}" ></c:out></a></td>
					</tr>
        			</c:forEach>
        			
        			
				

    		</tr>
    		
    			
			</table>
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
			<p>�2013 FoodSpotting. Design by Team HeadStart</a> </a></p>
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

