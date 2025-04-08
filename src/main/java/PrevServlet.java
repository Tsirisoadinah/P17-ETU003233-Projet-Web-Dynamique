package src.main.java;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PrevServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        String nom = req.getParameter("libelle");
        String montantstring = req.getParameter("montant");
        int montant = Integer.parseInt(montantstring);
        Prevision d = new Prevision(nom, montant);
        try {
            d.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("formdep");
    }



}