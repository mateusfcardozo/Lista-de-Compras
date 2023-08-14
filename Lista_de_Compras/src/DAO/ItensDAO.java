package DAO;

import Connection_DB.Connection_DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItensDAO {
        public int criarItem(String nomeProduto, String marca, int quantidade, double valorProduto) {
        int idItem = -1;
        String sql = "INSERT INTO Itens (Nome_Produto, Marca, Quantidade, Valor_Produto) VALUES (?, ?, ?, ?)";

        try (Connection connection = Connection_DB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nomeProduto);
            ps.setString(2, marca);
            ps.setInt(3, quantidade);
            ps.setDouble(4, valorProduto);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idItem = rs.getInt(1);
            }
            System.out.println("Item salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar Item: " + e.getMessage());
        }

        return idItem;
    }
}