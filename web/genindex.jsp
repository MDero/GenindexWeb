<%-- 
    Document   : genindex
    Created on : 14 mai 2014, 09:43:01
    Author     : Maxime
--%>

<%@page import="kernel.Orders"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kernel.Customers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="database" scope="session" class="kernel.Database"></jsp:useBean>
<% 
    //define fields
    String login = request.getParameter("login"),
           password = request.getParameter("password"),
            firstName = null ,lastName = null;
    
    //boolean that states if login is OK
    boolean logged = false;
    Integer id = null ;
    
    //check login
    if (login!=null && login.length()>0 ){
        logged = database.getCustomerLoginCorrect(login, password, id);
        if (id!=null){
            //search for names 
            Customers customer = database.getCustomer(id);
            firstName=customer.getFirstName();
            lastName=customer.getLastName();
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
                                <td><label>Mot de passe :</label>
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
                        if (logged && firstName!=null && lastName!=null){
                            out.print(firstName+ " "+lastName);
                        }
                    %><br>
                    
                    <input type="submit" name ="deconnect" value="Se déconnecter" />
                </div>
            </div>

            <div class="large-10 medium-10 columns">
                <div class="panel">
                    <h2>Vos orders : </h2>
                    <table>
                        <tr>
                            <td>Info </td>
                            <td>Info </td>
                            <td>Info </td>
                            <td>Info </td>
                        </tr>
                        <tr>
                            <td>Info </td>
                            <td>Info </td>
                            <td>Info </td>
                            <td>Info </td>
                        </tr>
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
                        <%
                        //Create order table
                        ArrayList<Orders> yourOrders = 
                                //(ArrayList<Orders>)database.getOrdersForCustomer(id)
                                (ArrayList<Orders>)database.getOrderList()
                                ;
                        
//                        for (Orders order : yourOrders)
//                            out.print("<tr>"
//                                    +"<td>"+order.getId()+"</td>"
//                                    +"<td>"+order.getCustomer().getFirstName()+" "+order.getCustomer().getLastName()+"</td>"
//                                    +"<td>"+order.getDateOrder()+"</td>"
//                                    +"<td>"+order.getDateDeadline()+"</td>"
//                                    +"<td>"+order.getPriorityLevel()+"</td>"
//                                    + "</tr>");
                        %>
                    </table>

                </div>
            </div>

        </div> 
        <% } %>
    </body>
</html>
