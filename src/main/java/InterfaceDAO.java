package src.main.java;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO {
    public void save() throws Exception;

    public void delete() throws Exception;

    public void update(int id) throws Exception;

    public List<BaseModel> findById(int id) throws Exception;

    public List<BaseModel> findAll() throws SQLException, ClassNotFoundException;
}
