package src.main.java;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DepenseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        try {
            String depenseString = req.getParameter("depense");
            String montantstring = req.getParameter("montant");
            int montant = Integer.parseInt(montantstring);
            int iddepense = Integer.parseInt(depenseString);
            Depense d = new Depense(iddepense, montant);
            int montanttotal = d.sommemontant(iddepense);
            Prevision prevision = new Prevision();
            List<BaseModel> prev = prevision.findById(iddepense);
            int montantprevision = 0;
            if (!prev.isEmpty() && prev.get(0) instanceof Prevision) {
                montantprevision = ((Prevision) prev.get(0)).getMontant();
            }
        if(montantprevision < montanttotal){
            req.setAttribute("error", "Le montant que vous avez entré depasse celui de la prevision ");
        }else {
            d.save();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("formdep");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Depense depense = new Depense();
            Prevision prevision = new Prevision();

            // Charger tous les employés par défaut
            List<BaseModel> depenses = depense.findAll();
            List<BaseModel> previsions = prevision.findAll();

            // Charger les totaux des montants groupés par idprevision
            List<BaseModel> montantParPrevision = depense.sommemontantbyid();

            // Ajouter les données comme attributs de la requête
            request.setAttribute("depenses", depenses);
            request.setAttribute("previsions", previsions);
            request.setAttribute("montantParPrevision", montantParPrevision);
        } catch (Exception e) {
            request.setAttribute("error", "Une erreur s'est produite lors du chargement des données.");
            e.printStackTrace();
        }

        request.getRequestDispatcher("/etat.jsp").forward(request, response);
    }
}
