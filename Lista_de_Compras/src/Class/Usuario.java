package Class;

import DAO.ListaDAO;
import DAO.UsuarioDAO;
import java.util.Date;
import java.util.List;

public class Usuario {
    private int idUsuario;
    private String NomeDeUsuario;
    private String Celular;
    private String Email;
    private String Senha;
    private Date Data_Criacao_User;
    
    public Usuario() {
        idUsuario = -1;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
         System.out.println("id do logado" + idUsuario);
    }

    public String getNomeDeUsuario() {
        return NomeDeUsuario;
    }

    public void setNomeDeUsuario(String NomedeUsuario) {
        NomeDeUsuario = NomedeUsuario;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public Date getData_Criacao_User() {
        return Data_Criacao_User;
    }

    public void setData_Criacao_User(Date data_Criacao_User) {
        Data_Criacao_User = data_Criacao_User;
    }
 public void cadastroUsuario(Usuario dados) {
        new UsuarioDAO().cadastroUsuario(dados);
}

    public List<Lista> getListas() {
       ListaDAO listaDAO = new ListaDAO();
    return listaDAO.getListasPorUsuario(idUsuario);
    }

}
