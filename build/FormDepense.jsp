<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*" %>
<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire de dépense</title>
</head>
<body>
    <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
    </c:if>
    <form name="form2" method="post" action="depense">
        <div>
            <label for="depense">Les dépenses listées :</label>
            <select id="depense" name="depense" required>
                <option value="">-- Sélectionnez une dépense --</option>
                <c:forEach var="depense" items="${depenses}">
                    <option value="${depense.id}">
                        ${depense.nom}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="montant">Montant :</label>
            <input type="number" id="montant" name="montant" required>
        </div>

        <button type="submit" class="btn-submit">Soumettre</button>
    </form>

    <p><a href="depense">Voir l'etat du depense</a></p>
    <p><a href="login">Logout</a></p>
</body>
</html>