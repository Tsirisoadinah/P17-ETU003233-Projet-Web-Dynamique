<%@ page import="jakarta.servlet.http.*,jakarta.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Etat</title>
</head>
<body>
    <h1>L'état actuel des dépenses</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Prévision</th>
                <th>Somme des dépenses</th>
                <th>Reste</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="prevision" items="${previsions}">
                <tr>
                    <td>${prevision.nom}</td>
                    <td>
                        <c:forEach var="montant" items="${montantParPrevision}">
                            <c:if test="${montant.idPrevision == prevision.id}">
                                ${montant.montant}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="montant" items="${montantParPrevision}">
                            <c:if test="${montant.idPrevision == prevision.id}">
                                ${prevision.montant - montant.montant}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p><a href="formdep">Ajouter une depense</a></p>
    <p><a href="login">Logout</a></p>
</body>
</html>