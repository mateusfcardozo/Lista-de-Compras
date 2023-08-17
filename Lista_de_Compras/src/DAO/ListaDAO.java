package DAO;

import Class.Lista;
import java.sql.PreparedStatement;
import Connection_DB.Connection_DB;
import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO {


    public int criarLista(Lista lista) {
        int idLista = -1;
        String sql = "INSERT INTO Lista (Usuario_idUsuario, Nome_Lista, Quantidade_Total, Valor_Total, Status) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection connection = Connection_DB.getConnection(); PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, lista.getUsuario_idUsuario());
            ps.setString(2, lista.getNome_Lista());
            ps.setInt(3, lista.getQuantidade_Total());
            ps.setDouble(4, lista.getValor_Total());
            ps.setString(5, lista.getStatus());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idLista = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar item: " + e.getMessage());
        }

        return idLista;
    }

    public void atualizarQuantidadeValor(int idLista, int quantidadeTotal, double valorTotal) {
        String sql = "UPDATE Lista SET Quantidade_Total = ?, Valor_Total = ? WHERE idLista = ?";

        try (java.sql.Connection connection = Connection_DB.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantidadeTotal);
            ps.setDouble(2, valorTotal);
            ps.setInt(3, idLista);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar a quantidade e valor total da lista: " + e.getMessage());
        }
    }

    public boolean verificarExistenciaListaPorUsuario(int idUsuario, String nomeLista) {
        boolean listaExiste = false;
        String sql = "SELECT idLista FROM Lista WHERE Usuario_idUsuario = ? AND Nome_Lista = ?";

        try (java.sql.Connection connection = Connection_DB.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, nomeLista);
            ResultSet rs = ps.executeQuery();
            listaExiste = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao verificar a existência da lista: " + e.getMessage());
        }

        return listaExiste;
    }

    public void atualizarStatus(int idLista, String novoStatus) {
        String sql = "UPDATE Lista SET Status = ? WHERE idLista = ?";

        try (java.sql.Connection connection = Connection_DB.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, novoStatus);
            ps.setInt(2, idLista);
            ps.executeUpdate();
            System.out.println("Status da lista atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar status da lista: " + e.getMessage());
        }
    }

    public List<Lista> getListasPorUsuario(int idUsuario) {
        List<Lista> listas = new ArrayList<>();
        String sql = "SELECT * FROM Lista WHERE Usuario_idUsuario = ?";

        try (java.sql.Connection connection = Connection_DB.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            System.out.println("getListasPorUsuario - Executando SQL: " + ps.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Lista lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setUsuario_idUsuario(rs.getInt("Usuario_idUsuario"));
                lista.setNome_Lista(rs.getString("Nome_Lista"));
                lista.setQuantidade_Total(rs.getInt("Quantidade_Total"));
                lista.setValor_Total(rs.getDouble("Valor_Total"));
                lista.setStatus(rs.getString("Status"));
                listas.add(lista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao obter listas por usuário: " + e.getMessage());
        }

        System.out.println("getListasPorUsuario - Quantidade de listas retornadas: " + listas.size());

        return listas;
    }

    public boolean excluirListasDoUsuario(int idUsuario) {
        String sqlDeleteItens = "DELETE FROM LISTA_HAS_ITENS WHERE Lista_idLista IN (SELECT idLista FROM LISTA WHERE Usuario_idUsuario = ?)";
        String sqlDeleteLista = "DELETE FROM LISTA WHERE Usuario_idUsuario = ?";

        try (java.sql.Connection connection = Connection_DB.getConnection(); PreparedStatement psDeleteItens = connection.prepareStatement(sqlDeleteItens); PreparedStatement psDeleteLista = connection.prepareStatement(sqlDeleteLista)) {

            psDeleteItens.setInt(1, idUsuario);
            psDeleteItens.executeUpdate();

            psDeleteLista.setInt(1, idUsuario);
            int rowsAffected = psDeleteLista.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir listas do usuário: " + e.getMessage());
            return false;
        }
    }

   public boolean dellListasDoUsuario(int idUsuario, String nomeLista) {
    String sqlDeleteItens = "DELETE FROM LISTA_HAS_ITENS WHERE Lista_idLista IN (SELECT idLista FROM LISTA WHERE Usuario_idUsuario = ? AND Nome_Lista = ?)";
    String sqlDeleteLista = "DELETE FROM LISTA WHERE Usuario_idUsuario = ? AND Nome_Lista = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement psDeleteItens = connection.prepareStatement(sqlDeleteItens);
         PreparedStatement psDeleteLista = connection.prepareStatement(sqlDeleteLista)) {

        psDeleteItens.setInt(1, idUsuario);
        psDeleteItens.setString(2, nomeLista);
        psDeleteItens.executeUpdate();

        psDeleteLista.setInt(1, idUsuario);
        psDeleteLista.setString(2, nomeLista);
        int rowsAffected = psDeleteLista.executeUpdate();

        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Erro ao excluir listas do usuário: " + e.getMessage());
        return false;
    }
}

    public void atualizarNomeLista(int idUsuario, String nomeListaAntigo, String novoNomeLista) {
    String sql = "UPDATE Lista SET Nome_Lista = ? WHERE Usuario_idUsuario = ? AND Nome_Lista = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, novoNomeLista);
        ps.setInt(2, idUsuario);
        ps.setString(3, nomeListaAntigo);
        ps.executeUpdate();
        System.out.println("Nome da lista atualizado com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao atualizar nome da lista: " + e.getMessage());
    }
}
public static boolean excluirListaEItens(int idLista) {
    String sqlDeleteItens = "DELETE FROM Itens WHERE idItens IN (SELECT Itens_idItens FROM Lista_has_Itens WHERE Lista_idLista = ?)";
    String sqlDeleteListaItens = "DELETE FROM Lista_has_Itens WHERE Lista_idLista = ?";
    String sqlDeleteLista = "DELETE FROM Lista WHERE idLista = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection()) {
        connection.setAutoCommit(false); 

        try (PreparedStatement psDeleteItens = connection.prepareStatement(sqlDeleteItens);
             PreparedStatement psDeleteListaItens = connection.prepareStatement(sqlDeleteListaItens);
             PreparedStatement psDeleteLista = connection.prepareStatement(sqlDeleteLista)) {

            psDeleteItens.setInt(1, idLista);
            psDeleteItens.executeUpdate();

            psDeleteListaItens.setInt(1, idLista);
            psDeleteListaItens.executeUpdate();

            psDeleteLista.setInt(1, idLista);
            int rowsAffected = psDeleteLista.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Lista e itens excluídos com sucesso!");
                connection.commit();  
                return true;
            } else {
                System.out.println("Nenhuma lista encontrada com o ID fornecido.");
                connection.rollback(); 
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir lista e itens: " + e.getMessage());
            connection.rollback();  
        } finally {
            connection.setAutoCommit(true); 
        }
    } catch (Exception e) {
        System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
    }

    return false;
}

   public void atualizarValorTotal(int idLista, double novoValorTotalLista) {
    String sql = "UPDATE lista SET Valor_Total = ? WHERE idLista = ?";
    
    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setDouble(1, novoValorTotalLista);
        ps.setInt(2, idLista);
        int rowsAffected = ps.executeUpdate();
        System.out.println("Número de linhas afetadas: " + rowsAffected);
        System.out.println("Valor total da lista atualizado com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao atualizar valor total da lista: " + e.getMessage());
    }
}

public void atualizarQuantidadeTotal(int idLista, int novaQuantidadeTotalLista) {
    String sql = "UPDATE lista SET Quantidade_Total = ? WHERE idLista = ?";
    
    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, novaQuantidadeTotalLista);
        ps.setInt(2, idLista);
        int rowsAffected = ps.executeUpdate();
        System.out.println("Número de linhas afetadas: " + rowsAffected);
        System.out.println("Quantidade total da lista atualizada com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao atualizar quantidade total da lista: " + e.getMessage());
    }
}

public int obterIdListaPorNomeEUsuario(String nomeLista, int idUsuario) {
    int idLista = -1;
    String sql = "SELECT idLista FROM lista WHERE Nome_Lista = ? AND Usuario_idUsuario = ?";
    
    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, nomeLista);
        ps.setInt(2, idUsuario);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                idLista = rs.getInt("idLista");
            }
        }
    } catch (Exception e) {
        System.out.println("Erro ao obter ID da lista: " + e.getMessage());
    }
    
    return idLista;
}

}
