package src.main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur extends BaseModel implements InterfaceDAO {
    private String nom;
    private String motdepasse;

    public Utilisateur(){
        
    }

    public Utilisateur(String nom,  String motdepasse) {
        this.nom = nom;
        this.motdepasse = motdepasse;
    }

    public Utilisateur(int id, String nom,  String motdepasse) {
        super(id);
        this.nom = nom;
        this.motdepasse = motdepasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @Override
    public void save() throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            String sql = "INSERT INTO web_utilisateur(nom, motdepasse) VALUES(?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.getNom());
            stmt.setString(4, this.getMotdepasse());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Insertion accomplie avec succes!");
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion dans la table utilisateur");
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    @Override
    public List<BaseModel> findAll() throws SQLException, ClassNotFoundException {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> utilisateurs = new ArrayList<>();
            String query = "SELECT * FROM web_utilisateur ORDER BY id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("motdepasse"));

                utilisateurs.add(utilisateur);
            }

            rs.close();
            stmt.close();
            return utilisateurs;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données de la table utilisateur", e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    @Override
    public void delete() throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            String sql = "DELETE FROM web_utilisateur WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Suppression accomplie avec succes!");
        } catch (Exception e) {
            throw new Exception("Erreur lors de la suppression dans la table utilisateur");
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    public void update(int id) throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            String sql = "UPDATE web_utilisateur SET nom = ?, motdepasse = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.getNom());
            stmt.setString(4, this.getMotdepasse());
            stmt.setInt(5, id);

            int rowsUpdated = stmt.executeUpdate();
            stmt.close();

            if (rowsUpdated > 0) {
                System.out.println("Mise à jour de l'utilisateur réussie!");
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Erreur lors de la mise à jour dans la table utilisateur: " + e.getMessage());
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    @Override
    public List<BaseModel> findById(int id) throws Exception {
        Connection con = UtilsDao.createConnection();
        try {
            List<BaseModel> utilisateurs = new ArrayList<>();
            String sql = "SELECT * FROM web_utilisateur WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("motdepasse"));
                utilisateurs.add(utilisateur);
            }

            rs.close();
            stmt.close();

            if (utilisateurs.isEmpty()) {
                System.out.println("Aucun utilisateur trouvé avec l'ID: " + id);
            }

            return utilisateurs;
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération de l'utilisateur avec l'ID: " + id, e);
        } finally {
            UtilsDao.closeConnection(con);
        }
    }

    public boolean isValid(String nom, String motdepasse) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = UtilsDao.createConnection();
            String sql = "SELECT * FROM web_utilisateur WHERE nom = ? AND motdepasse = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, motdepasse);
            
            rs = stmt.executeQuery();
            
            // Si on a un résultat, les identifiants sont valides
            return rs.next();
            
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la vérification des identifiants", e);
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) UtilsDao.closeConnection(con);
        }
    }
}