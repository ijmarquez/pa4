<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="org.glassfish.jersey.client.ClientConfig" %>
<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.client.WebTarget" %>
<%@ page import="java.net.URI" %>
<%@ page import="javax.ws.rs.core.UriBuilder" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ page import="org.codehaus.jackson.type.TypeReference" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/header_footer.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <link rel="stylesheet" type="text/css" href="css/footerContent.css">
    <link rel="stylesheet" type="text/css" href="css/items_list.css">
    <link rel="stylesheet" type="text/css" href="css/items.css">
    <link rel="stylesheet" type="text/css" href="css/buyItem.css">
    <link rel="stylesheet" type="text/css" href="css/customerOrder.css">
    <script type="text/javascript" src="js/buyItem.js"></script>
    <script type="text/javascript" src="js/item.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-3-typeahead/4.0.1/bootstrap3-typeahead.min.js"></script>
</head>
<title> Clothing Couture </title>
<body>
<div class="pageContainer">
    <div class="homeContainer">
        <div class="home-logo">
            <h1>Clothing Couture</h1>
        </div>

        <div class="navMenu">
            <ul class="navBar">
                <li> <a class="userOption" href="home"> Welcome </a> </li>
                <li> <a class="userOption" href="itemsList.jsp"> Home </a> </li>
                <%--<li> <a class="userOption" href="ShoppingCart"> Shopping model.Cart </a> </li>--%>
            </ul>
        </div>
    </div>

    <!-- start here -->
    <div class="items-container">
        <div class="items-contents">
            <!-- get items here -->
            <table class="itemListTable">
                <%--//this is where it'll show the list of different items--%>
                <jsp:include page="/MainItems"/>
            </table>
        </div>
    </div>


    <%--<footer>--%>
        <%--<div class="footer">--%>
            <%--<a class="footerOption" href="about.php">about us</a>--%>
            <%--<a class="footerOption" href="contact.php">contact us</a>--%>
        <%--</div>--%>
    <%--</footer>--%>
</div>
</body>
</html>

