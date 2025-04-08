<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*" %>
<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <div class="container">
        <h3>Ajouter une Prevision</h3>
        
        <form name="form1" method="post" action="prevision">
            <div class="form-group">
                <label for="libelle">Libelle :</label>
                <input type="text" id="libelle" name="libelle" placeholder="Ex: Sakafo, crédit, ............" required>
            </div>

            <div class="form-group">
                <label for="montant">Montant :</label>
                <input type="number" id="montant" name="montant" required>
            </div>
            
            <button type="submit" class="btn-submit">Soumettre</button>
        </form>
        
        <p><a href="formdep">Ajouter une depense</a></p>
        <p><a href="depense">Voir l'etat du depense</a></p>
        <p><a href="login">Logout</a></p>
        
    </div>
</body>
</html>