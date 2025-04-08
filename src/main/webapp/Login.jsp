<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form method="post" action="login">
        <p>Username = Eleve1</p>
        <p>mot de passe = mdp123</p>
        Username: <input type="text" name="username" value="Eleve1" required><br><br>
        Password: <input type="password" name="password" value="mdp123" required><br><br>
        <input type="submit" value="Login">
    </form>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
