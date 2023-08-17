package DAO;

import Class.Itens;
import Connection_DB.Connection_DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Itens> obterItensDaListaPorNomeEUsuario(String nomeLista, int idUsuario) {
    List<Itens> itensList = new ArrayList<>();

    String sql = "SELECT i.* FROM Itens i " +
                 "INNER JOIN lista_has_itens lhi ON i.idItens = lhi.Itens_idItens " +
                 "INNER JOIN Lista l ON l.idLista = lhi.Lista_idLista " +
                 "WHERE l.Nome_Lista = ? AND l.Usuario_idUsuario = ?";

    try (Connection connection = Connection_DB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setString(1, nomeLista);
        preparedStatement.setInt(2, idUsuario);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Itens item = new Itens();
                item.setIdItens(resultSet.getInt("idItens"));
                item.setNome_Produto(resultSet.getString("Nome_Produto"));
                item.setMarca(resultSet.getString("Marca"));
                item.setQuantidade(resultSet.getInt("Quantidade"));
                item.setValor_Produto(resultSet.getDouble("Valor_Produto"));

                itensList.add(item);
            }
        }

   } catch (Exception e) {
        System.out.println("Erro ao obter itens da lista por nome e usuário: " + e.getMessage());
    }

    return itensList;
}

public void atualizarItem(Itens item) {
    String sql = "UPDATE Itens SET Nome_Produto = ?, Marca = ?, Quantidade = ?, Valor_Produto = ? WHERE idItens = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, item.getNome_Produto());
        ps.setString(2, item.getMarca());
        ps.setInt(3, item.getQuantidade());
        ps.setDouble(4, item.getValor_Produto());
        ps.setInt(5, item.getIdItens());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Erro ao atualizar item: " + e.getMessage());
    }
}

public int getIdItemByDetails(String novoProduto, String novaMarca, double novoValor) {
    String sql = "SELECT idItens FROM Itens WHERE Nome_Produto = ? AND Marca = ? AND Valor_Produto = ?";

    try (Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, novoProduto);
        ps.setString(2, novaMarca);
        ps.setDouble(3, novoValor);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idItens");
            }
        }
    } catch (Exception e) {
        System.out.println("Erro ao buscar ID do item por detalhes: " + e.getMessage());
    }

    return -1; 
}

   public int inserirItem(String produto, String marca, int quantidade, double valor) {
    String sql = "INSERT INTO Itens (Nome_Produto, Marca, Quantidade, Valor_Produto) VALUES (?, ?, ?, ?)";

    try (Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, produto);
        ps.setString(2, marca);
        ps.setInt(3, quantidade);
        ps.setDouble(4, valor);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); 
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; 
}

 public void removerItem(int idItem) {
    String sql = "DELETE FROM Itens WHERE idItens = ?";

    try (Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idItem);

        int rowsAffected = ps.executeUpdate();
        System.out.println("Número de linhas afetadas ao remover item: " + rowsAffected);
    } catch (Exception e) {
        System.out.println("Erro ao remover item: " + e.getMessage());
    }
}

   public List<Itens> obterItensPorIdLista(int selectedListaId) {
    List<Itens> itensList = new ArrayList<>();

    String sql = "SELECT * FROM Itens WHERE idItens IN " +
                 "(SELECT Itens_idItens FROM lista_has_itens WHERE Lista_idLista = ?)";

    try (Connection connection = Connection_DB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, selectedListaId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Itens item = new Itens();
                item.setIdItens(resultSet.getInt("idItens"));
                item.setNome_Produto(resultSet.getString("Nome_Produto"));
                item.setMarca(resultSet.getString("Marca"));
                item.setQuantidade(resultSet.getInt("Quantidade"));
                item.setValor_Produto(resultSet.getDouble("Valor_Produto"));

                itensList.add(item);
            }
        }

   } catch (Exception e) {
        System.out.println("Erro ao obter itens por ID da lista: " + e.getMessage());
    }

    return itensList;
}

public void atualizarQuantidadeValorItem(int idItem, int novaQuantidade, double novoValor) {
    String sql = "UPDATE Itens SET Quantidade = ?, Valor_Produto = ? WHERE idItens = ?";
    
    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, novaQuantidade);
        ps.setDouble(2, novoValor);
        ps.setInt(3, idItem);
        int rowsAffected = ps.executeUpdate();
        System.out.println("Número de linhas afetadas: " + rowsAffected);
        System.out.println("Quantidade e valor do item atualizados com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao atualizar quantidade e valor do item: " + e.getMessage());
    }
}


   public void atualizarNomeProduto(int idItens, String novoNomeProduto) {
    String sql = "UPDATE Itens SET Nome_Produto = ? WHERE idItens = ?";
    
    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, novoNomeProduto);
        ps.setInt(2, idItens);
        int rowsAffected = ps.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Nome do produto atualizado com sucesso!");
        } else {
            System.out.println("Nenhum item encontrado com o ID fornecido.");
        }
    } catch (Exception e) {
        System.out.println("Erro ao atualizar nome do produto: " + e.getMessage());
    }
}
}