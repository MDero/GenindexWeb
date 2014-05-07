<%-- 
    Document   : test
    Created on : 6 mai 2014, 14:52:32
    Author     : Maxime Derobillard
--%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "/header.html" %>


<jsp:useBean id="animal1" class="genindex.Animals" scope="application"/>

<%  
    animal1.setSpecie("Chien");
    String ani1 = animal1.getSpecie();
    
 %>
 
 <%=ani1%>

<%@include file = "/footer.html" %>
