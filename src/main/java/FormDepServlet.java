package src.main.java;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FormDepServlet extends HttpServlet {
    Prevision prevision = new Prevision();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("depenses", prevision.findAll());
        
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors du chargement des données");
        }
        request.getRequestDispatcher("/FormDepense.jsp").forward(request, response);
    }
}


