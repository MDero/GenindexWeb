<%-- 
    Document   : genindex
    Created on : 14 mai 2014, 09:43:01
    Author     : Maxime
--%>

<%@page import="kernel.LoginBean"%>
<%@page import="kernel.Orders"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kernel.Customers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="database" class="kernel.Database"></jsp:useBean>
<jsp:useBean id="customer" class="kernel.Customers"></jsp:useBean>
<%     
    //boolean that states if login is OK
    boolean logged = customer.getFirstName()!=null && customer.getLastName()!=null;
    Integer id = null;
    
    //check login
    if (!logged && request.getParameter("login")!=null && request.getParameter("password")!=null){
        System.out.println("I ENTER THIS");
        //search the database for these
        String login=request.getParameter("login"), password=request.getParameter("password");
        boolean isCorrect = database.getCustomerLoginCorrect(login, password);
        Integer i=null;
        if (isCorrect)
            i = database.getCustomerIdWhenLoginCorrect(login, password);
        
//        Integer i =  database.getCustomerIdWhenLoginCorrect(identifiers.getLogin(), identifiers.getPassword());
        id = i==null ? 1 : i;
        if (id!=null){
        %>
        <jsp:setProperty name="customer" property="ID" value="<%=id%>"/>
        <%
        Customers thisOne = database.getCustomer(customer.getID());
        System.out.println("AND THIS"+customer.getID()+thisOne.getFirstName()+thisOne.getLastName());
           %>
           <jsp:setProperty name="customer" property="firstName" value="<%=thisOne.getFirstName()%>"/>
           <jsp:setProperty name="customer" property="lastName" value="<%=thisOne.getLastName()%>"/>
           <%
        }
    }
    //logged in 
    else{
       if (id!=null && customer.getFirstName()==null || customer.getLastName()==null){
           //search data in the database
           Customers thisOne = database.getCustomer(customer.getID());
           
           %>
           <jsp:setProperty name="customer" property="firstName" value="<%=thisOne.getFirstName()%>"/>
           <jsp:setProperty name="customer" property="lastName" value="<%=thisOne.getLastName()%>"/>
           <%
       }
    }
    
%>
<!DOCTYPE html>
<html class="no-js" lang="fr">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Genindex</title>
        <link rel="stylesheet" href="css/foundation.css" />
        <script src="js/vendor/modernizr.js"></script>
    </head>
    <body>
        <div class="row">
            <div class="large-4 medium-4 columns">
            </div>
            <div class="large-8 medium-8 columns">
                <h1>Genindex</h1>
            </div>
        </div> 
        
        <% if (!logged){
            
        %>
        <div class="row">
            <div class="large-12 medium-12 columns">   
                <div class="panel">
                    <form action="genindex.jsp" method="post"> 
                        <table>
                            <tr>
                                <td>
                                    <label>Login :  </label>
                                    <input type="text" name="login">
                                </td>
                            </tr>
              <tr>
                        <td><l de passe :</label>
                                    <input type="password" name="password"></td>
                            </tr>
                            <tr>
                                <td></td><td><input type="submit" name ="Validate" value="Valider" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <% }
        else {
        %>


        <div class="row">
            <div class="large-2 medium-2 columns">
                <div class="panel">
                    Bienvenue <br>
                    <%  
                        if (logged){
                            out.print(customer.getFirstName()+" "+customer.getLastName());
                        }
                    %><br>
                    
                    <input type="submit" name ="deconnect" value="Se déconnecter" />
                </div>
            </div>

            <div class="large-10 medium-10 columns">
                <div class="panel">
                    <h2>Vos orders : </h2>
                    <table>
                        <%
                        //Create order table
                        ArrayList<Orders> allOrders = (ArrayList<Orders>)
//                                database.getOrderList()
                                  database.getOrdersForCustomer(customer);
                                ;
//                        ArrayList<Orders> yourOrders = new ArrayList<Orders>();
//                        
                        out.print("<tr>"
                                    + "<th>Identifier</th>"
                                    + "<th>Date Order </th>"
                                    + "<th>Deadline </th>"
                                    + "<th>Priority </th>"
                                    + "<th>Status</th>"
                                    + "<th>Number of Samples</th>"
                                +"</tr>"
                        );
                        for (Orders order : allOrders)
                            out.print("<tr>"
                                        +"<td>"+order.getIdOrder()+"</td>"
                                        +"<td>"+order.getDateOrder()+"</td>"
                                        +"<td>"+order.getDateDeadline()+"</td>"
                                        +"<td>"+order.getPriorityLevel()+"</td>"
                                        +"<td>"+(order.getPaid()?"payé" : "non payé")+"</td>"
                                        +"<td>"+order.getNumberSamples()+"</td>"
                                        + "</tr>");
                        //filtrate (getOrdersForCustomer(id) not working here)
//                        if (allOrders!=null){
//                            if (allOrders.size()>0){
//                                yourOrders=(ArrayList<Orders>)database.getOrdersForCustomer(id);
//                            }
//                            
//                            if (yourOrders!=null)for (Orders order : yourOrders){
//                                out.print("<tr>"
//                                        +"<td>"+order.getId()+"</td>"
//                                        +"<td>"+order.getCustomer().getFirstName()+" "+order.getCustomer().getLastName()+"</td>"
//                                        +"<td>"+order.getDateOrder()+"</td>"
//                                        +"<td>"+order.getDateDeadline()+"</td>"
//                                        +"<td>"+order.getPriorityLevel()+"</td>"
//                                        + "</tr>");
//                            }
//                        }

                        %>
                    </table>

                </div>
            </div>

        </div> 
        
       

        <div class="row">
            <div class="large-2 medium-2 columns">
            </div>

            <div class="large-10 medium-10 columns">
                <div class="panel">
                    <h2>Sample : </h2>
                    <table>

                    </table>

                </div>
            </div>

        </div> 
        <% } %>
    </body>
</html>
