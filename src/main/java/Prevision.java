package src.main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Prevision extends BaseModel implements InterfaceDAO {
    private String nom;
    private int montant;

    public Prevision(){

    }
    
    public Prevision(String nom, int montant) {
        this.nom = nom;
        this.montant = montant;
    }
    public Prevision(int id, String nom, int montant) {
        super(id);
        this.nom = nom;
        this.montant = montant;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }
    @Override
    public void save() throws Exception {
                Connection con = UtilsDao.createConnection();
        try {
            String sql = "INSERT INTO web_prevision(libelle, montant) VALUES(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.nom);
            stmt.setInt(2,(int) this.montant);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Insertion accomplie avec succes!");
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion dans la table typemateriel");
        } finally {
            UtilsDao.closeConnection(con);
        }
    }
    @Override
    public void delete() throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            String sql = "DELETE FROM web_prevision WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la suppression de la table Departement");
        } finally {
            UtilsDao.closeConnection(con);
        }
    }
    @Override
    public void update(int id) throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            String sql = "UPDATE web_prevision SET libelle = ?, SET montant = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.getNom());
            stmt.setInt(2, this.getMontant());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la mise à jour de la table Departement");
        } finally {
            UtilsDao.closeConnection(con);
        }
    }
    @Override
    public List<BaseModel> findById(int id) throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> typeMateriels_liste = new ArrayList<>();
            String query = "SELECT * FROM web_prevision WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prevision typeMateriel = new Prevision(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getInt("montant"));

                typeMateriels_liste.add(typeMateriel);

            }

            rs.close();
            stmt.close();
            return typeMateriels_liste;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données de la table materiel", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }
    @Override
    public List<BaseModel> findAll() throws SQLException, ClassNotFoundException {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> typeMateriels_liste = new ArrayList<>();
            String query = "select * from web_prevision";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Prevision typeMateriel = new Prevision(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getInt("montant"));

                typeMateriels_liste.add(typeMateriel);
            }

            rs.close();
            stmt.close();
            return typeMateriels_liste;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données de la table materiel", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    public List<BaseModel> findBylibelle(String libelle) throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> typeMateriels_liste = new ArrayList<>();
            String query = "SELECT * FROM web_prevision WHERE libelle = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, libelle);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prevision typeMateriel = new Prevision(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getInt("montant"));

                typeMateriels_liste.add(typeMateriel);

            }

            rs.close();
            stmt.close();
            return typeMateriels_liste;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données de la table materiel", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }
    
}
