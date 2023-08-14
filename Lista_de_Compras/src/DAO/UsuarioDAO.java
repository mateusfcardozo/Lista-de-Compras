package DAO;

import Class.Usuario;
import java.sql.PreparedStatement;
import Connection_DB.Connection_DB;
import Main_Views.View_Login;
import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

     public void cadastroUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (nomeUsuario, email, celular, senha, Data_Criacao_User) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = Connection_DB.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNomeDeUsuario());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getCelular());
            ps.setString(4, usuario.getSenha());

            java.util.Date dataCriacao = usuario.getData_Criacao_User();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(dataCriacao.getTime());
            ps.setTimestamp(5, timestamp);

            ps.execute();
            System.out.println("Usuário salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

   public void atualizarSenha(int idUsuario, String novaSenha) {
        String sql = "UPDATE USUARIO SET senha = ? WHERE idusuario = ?";

        try (PreparedStatement ps = Connection_DB.getConnection().prepareStatement(sql)) {
            ps.setString(1, novaSenha);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            System.out.println("Senha atualizada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
        }
    }
public boolean inputDoUsuarioEValido(String userInput) {
        String sql = "SELECT COUNT(*) AS count FROM USUARIO WHERE email = ? OR celular = ?";
        boolean isValid = false;

        try (PreparedStatement ps = Connection_DB.getConnection().prepareStatement(sql)) {
            ps.setString(1, userInput);
            ps.setString(2, userInput);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                isValid = (count > 0);
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar input do usuário: " + e.getMessage());
        }

        return isValid;
    }
public int getIdUsuarioPorInput(String userInput) {
    String sql = "SELECT idUsuario FROM USUARIO WHERE email = ? OR celular = ?";
    int idUsuario = -1;

    try (PreparedStatement ps = Connection_DB.getConnection().prepareStatement(sql)) {
        ps.setString(1, userInput);
        ps.setString(2, userInput);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            idUsuario = rs.getInt("idUsuario");
        }
    } catch (Exception e) {
        System.out.println("Erro ao obter ID do usuário: " + e.getMessage());
    }

    return idUsuario;
}
 public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) AS count FROM USUARIO WHERE email = ?";
        boolean existe = false;

        try (PreparedStatement ps = Connection_DB.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                existe = (count > 0);
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se o email já existe: " + e.getMessage());
        }

        return existe;
    }
  public boolean verificarEmailSenha(String email, String senha) {
        String sql = "SELECT COUNT(*) AS count FROM USUARIO WHERE email = ? AND senha = ?";
        boolean emailsenhaCorresponde = false;

        try (PreparedStatement ps = Connection_DB.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                emailsenhaCorresponde = (count > 0);
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar a senha: " + e.getMessage());
        }

        return emailsenhaCorresponde;
    }

  public int realizarLogin(String email, String senha) {
        String sql = "SELECT idUsuario FROM USUARIO WHERE email = ? AND senha = ?";
        int idUsuario = -1;

        try (PreparedStatement ps = Connection_DB.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idUsuario = rs.getInt("idUsuario");
            }
        } catch (Exception e) {
            System.out.println("Erro ao realizar login: " + e.getMessage());
        }

        return idUsuario;
    }
public int obterIdUsuarioPorEmail(String email) {
    int idUsuario = -1;
    String sql = "SELECT idUsuario FROM Usuario WHERE Email = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            idUsuario = rs.getInt("idUsuario");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erro ao obter o ID do usuário: " + e.getMessage());
    }

    return idUsuario;
}

  public void atualizarDadosUsuario(Usuario usuarioAtualizado) {
    String sql = "UPDATE Usuario SET NomeUsuario = ?, Celular = ?, Email = ?, Senha = ? WHERE idUsuario = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, usuarioAtualizado.getNomeDeUsuario());
        ps.setString(2, usuarioAtualizado.getCelular());
        ps.setString(3, usuarioAtualizado.getEmail());
        ps.setString(4, usuarioAtualizado.getSenha());
        ps.setInt(5, usuarioAtualizado.getIdUsuario());

        ps.executeUpdate();
        System.out.println("Dados do usuário atualizados com sucesso!");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erro ao atualizar dados do usuário: " + e.getMessage());
    }
}
   public Usuario obterUsuarioPorId(int idUsuario) {
    String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
    Usuario usuario = null;

    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setNomeDeUsuario(rs.getString("NomeUsuario"));
            usuario.setCelular(rs.getString("Celular"));
            usuario.setEmail(rs.getString("Email"));
            usuario.setSenha(rs.getString("Senha"));
           
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erro ao obter usuário por ID: " + e.getMessage());
    }

    return usuario;
}

  public boolean excluirContaUsuario(int idUsuario) {
    String sql = "DELETE FROM USUARIO WHERE idUsuario = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idUsuario);
        int rowsAffected = ps.executeUpdate();
        
        if (rowsAffected > 0) {
        
            View_Login loginView = new View_Login();
            loginView.setVisible(true);
            
            return true;
        }
        
        return false;
    } catch (SQLException e) {
        System.out.println("Erro ao excluir conta do usuário: " + e.getMessage());
        return false;
    }
}

 public boolean excluirListasDoUsuario(int idUsuario) {
    String sqlSelect = "SELECT idLista FROM Lista WHERE Usuario_idUsuario = ?";
    String sqlDeleteLista = "DELETE FROM Lista WHERE Usuario_idUsuario = ?";
    String sqlDeleteItems = "DELETE FROM lista_has_itens WHERE Lista_idLista = ?";

    try (java.sql.Connection connection = Connection_DB.getConnection();
         PreparedStatement psSelect = connection.prepareStatement(sqlSelect);
         PreparedStatement psDeleteLista = connection.prepareStatement(sqlDeleteLista);
         PreparedStatement psDeleteItems = connection.prepareStatement(sqlDeleteItems)) {

        psSelect.setInt(1, idUsuario);
        ResultSet rs = psSelect.executeQuery();

        while (rs.next()) {
            int idLista = rs.getInt("idLista");
            psDeleteItems.setInt(1, idLista);
            psDeleteItems.executeUpdate();
        }

        psDeleteLista.setInt(1, idUsuario);
        psDeleteLista.executeUpdate();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erro ao excluir listas do usuário: " + e.getMessage());
        return false;
    }
}
}
