package DAO;

import Connection_DB.Connection_DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListaHasItensDAO {
   public void Insert(int idLista, int idItem) {
        String sql = "INSERT INTO Lista_has_Itens (Lista_IdLista, Itens_idItens) VALUES (?, ?)";

        try (Connection connection = Connection_DB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idLista);
            ps.setInt(2, idItem);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar associação de lista e itens: " + e.getMessage());
        }
    }

}
