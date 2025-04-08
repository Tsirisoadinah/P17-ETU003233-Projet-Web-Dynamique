package src.main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Depense extends BaseModel implements InterfaceDAO {
    private int idPrevision;
    private int montant;

    public Depense() {
    }

    public Depense(int idPrevision, int montant) {
        this.idPrevision = idPrevision;
        this.montant = montant;
    }

    public Depense(int id, int idPrevision, int montant) {
        super(id);
        this.idPrevision = idPrevision;
        this.montant = montant;
    }

    public int getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(int idPrevision) {
        this.idPrevision = idPrevision;
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
            String sql = "INSERT INTO web_depense(idprevision, montant) VALUES(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.idPrevision);
            stmt.setInt(2, this.montant);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Insertion accomplie avec succès!");
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion dans la table web_depense", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    @Override
    public void delete() throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            String sql = "DELETE FROM web_depense WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la suppression de la table web_depense", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    @Override
    public void update(int id) throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            String sql = "UPDATE web_depense SET idprevision = ?, montant = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getIdPrevision());
            stmt.setInt(2, this.getMontant());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la mise à jour de la table web_depense", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    @Override
    public List<BaseModel> findById(int id) throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> depenses = new ArrayList<>();
            String query = "SELECT * FROM web_depense WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Depense depense = new Depense(
                        rs.getInt("id"),
                        rs.getInt("idprevision"),
                        rs.getInt("montant"));
                depenses.add(depense);
            }
            rs.close();
            stmt.close();
            return depenses;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données de la table web_depense", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    @Override
    public List<BaseModel> findAll() throws SQLException, ClassNotFoundException {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> depenses = new ArrayList<>();
            String query = "SELECT * FROM web_depense";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Depense depense = new Depense(
                        rs.getInt("id"),
                        rs.getInt("idprevision"),
                        rs.getInt("montant"));
                depenses.add(depense);
            }
            rs.close();
            stmt.close();
            return depenses;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données de la table web_depense", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    public int sommemontant(int iddepense) throws SQLException, ClassNotFoundException {
        Connection con = UtilsDao.createConnection();
        try {
            int sommetotal = 0;
            String query = "SELECT SUM(montant) AS total FROM web_depense WHERE idprevision = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, iddepense);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sommetotal = rs.getInt("total");
            }
            rs.close();
            stmt.close();
            return sommetotal;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors du calcul de la somme de montant", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    public List<BaseModel> sommemontantbyid() throws SQLException, ClassNotFoundException {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> depenses = new ArrayList<>();
            String query = "SELECT SUM(montant) AS total, idprevision FROM web_depense GROUP BY idprevision";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // Créez un objet Depense ou une classe dérivée pour stocker les données
                Depense depense = new Depense();
                depense.setIdPrevision(rs.getInt("idprevision"));
                depense.setMontant(rs.getInt("total"));
                depenses.add(depense);
            }
            rs.close();
            stmt.close();
            return depenses;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données de la table web_depense", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }
}