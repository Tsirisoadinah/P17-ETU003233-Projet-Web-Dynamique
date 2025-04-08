package src.main.java;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilisateur utilisateur = new Utilisateur();

        String nom = request.getParameter("username");
        String motdepasse = request.getParameter("password");

        try {
            if (utilisateur.isValid(nom, motdepasse)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", nom);
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("error", "Nom ou mot de passe incorrect.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur serveur : " + e.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Gère la déconnexion
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Détruit la session
        }
        response.sendRedirect("Login.jsp"); // Redirige vers la page de login
    }
}