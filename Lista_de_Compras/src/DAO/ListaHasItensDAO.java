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

   public void removerItem(int idLista, int idItem) {
        String sql = "DELETE FROM ListaHasItens WHERE Lista_idLista = ? AND Itens_idItens = ?";

        try (Connection connection = Connection_DB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idLista);
            ps.setInt(2, idItem);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Número de linhas afetadas ao remover item da lista: " + rowsAffected);
        } catch (Exception e) {
            System.out.println("Erro ao remover item da lista: " + e.getMessage());
        }
    }

    public void inserirItem(int idLista, int existingItemId) {
        String sql = "INSERT INTO ListaHasItens (Lista_idLista, Itens_idItens) VALUES (?, ?)";

        try (Connection connection = Connection_DB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idLista);
            ps.setInt(2, existingItemId);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Número de linhas afetadas ao inserir item na listaHasItens: " + rowsAffected);
        } catch (Exception e) {
            System.out.println("Erro ao inserir item na listaHasItens: " + e.getMessage());
        }
    }

   public void associarItemALista(int selectedListaId, int idItem) {
        String sql = "INSERT INTO lista_has_itens (Lista_idLista, Itens_idItens) VALUES (?, ?)";

        try (Connection connection = Connection_DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, selectedListaId);
            preparedStatement.setInt(2, idItem);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao associar item à lista: " + e.getMessage());
        }
    }

   public void desassociarItemDeLista(int selectedListaId, int idItem) {
    String sql = "DELETE FROM lista_has_itens WHERE Lista_idLista = ? AND Itens_idItens = ?";

    try (Connection connection = Connection_DB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, selectedListaId);
        preparedStatement.setInt(2, idItem);

        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Erro ao desassociar item da lista: " + e.getMessage());
    }
}
   public void removerItemEDesassociar(int idLista, int idItem) {
    String sql = "DELETE FROM Lista_has_Itens WHERE Lista_idLista = ? AND Itens_idItens = ?";

    try (Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idLista);
        ps.setInt(2, idItem);

        int rowsAffected = ps.executeUpdate();
        System.out.println("Número de linhas afetadas ao remover item da lista: " + rowsAffected);

        ItensDAO itensDAO = new ItensDAO();
        itensDAO.removerItem(idItem);

        System.out.println("Item removido e desassociado com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao remover item da lista: " + e.getMessage());
    }
}
}
