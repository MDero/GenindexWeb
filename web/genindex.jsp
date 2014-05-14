<%-- 
    Document   : genindex
    Created on : 14 mai 2014, 09:43:01
    Author     : Maxime
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="row">
            <div class="large-12 medium-12 columns">   
                <div class="panel">
                    <form> 
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



        <div class="row">
            <div class="large-2 medium-2 columns">
                <div class="panel">
                    Bienvenue ! <br>
                    Nom <br>
                    Prénom <br>
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
    </body>
</html>
