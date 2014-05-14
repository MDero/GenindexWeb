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
<jsp:useBean id="database" scope="session" class="kernel.Database"></jsp:useBean>
<jsp:useBean id="customer" scope="session" class="kernel.Customers"></jsp:useBean>
<%     
    System.out.println("--------------------------------------------------------------");
    if (session.getAttribute("login")==null)
        session.setAttribute("login",request.getParameter("login"));
    if (session.getAttribute("password")==null)
        session.setAttribute("password",request.getParameter("password"));
    
    String login = ""+session.getAttribute("login");
    String password = ""+session.getAttribute("password");
    
    if (session.getAttribute("id")==null)
        session.setAttribute("id",-1);
    Integer id=Integer.valueOf(""+session.getAttribute("id"));
    
    if (login!=null && password!=null && login.length()>0 && password.length()>0){
        System.out.println("Entered if 1");
        //try the values
        boolean valid = database.getCustomerLoginCorrect(login, password);
        
        if (valid){
            System.out.println("identifiers are VALID");
            //find information on the customer  and set the properties
            id = database.getCustomerIdWhenLoginCorrect(login, password);
            System.out.println("getCustomerIDWhenLoginCorrect returns "+id);
            %>
            <jsp:setProperty name="customer" property="ID" value="<%=id%>"/>
            <%
            Customers thisOne =database.getCustomer(id) ;
            System.out.println("database found : "+thisOne.getID()+" "+thisOne.getFirstName()+" "+thisOne.getLastName());
                %>
           <jsp:setProperty name="customer" property="firstName" value="<%=thisOne.getFirstName()%>"/>
           <jsp:setProperty name="customer" property="lastName" value="<%=thisOne.getLastName()%>"/>
           <%
           session.setAttribute("active", "true");
        }
        else{
            session.setAttribute("active", "false");
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
        
        <% if (session.getAttribute("active")==null && !((""+session.getAttribute("active")).equals("true"))){
            
            
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
            System.out.println("PUTAIN DE MERDE "+session.getAttribute("active")+" session "+session.getId());
        %>


        <div class="row">
            <div class="large-2 medium-2 columns">
                <div class="panel">
                    Bienvenue <br>
                    <%  
                        if (customer.getID()!=-1){
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
