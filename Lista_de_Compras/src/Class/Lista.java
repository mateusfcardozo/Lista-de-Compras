package Class;

import java.util.Date;

public class Lista {
    private int idLista;
    private int Usuario_idUsuario;
    private String Nome_Lista;
    private int Quantidade_Total;
    private double Valor_Total;
    private String Status;


    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(int usuario_idUsuario) {
        Usuario_idUsuario = usuario_idUsuario;
    }

    public String getNome_Lista() {
        return Nome_Lista;
    }

    public void setNome_Lista(String nome_Lista) {
        Nome_Lista = nome_Lista;
    }

    public int getQuantidade_Total() {
        return Quantidade_Total;
    }

    public void setQuantidade_Total(int quantidade_Total) {
        Quantidade_Total = quantidade_Total;
    }

    public double getValor_Total() {
        return Valor_Total;
    }

    public void setValor_Total(double valor_Total) {
        Valor_Total = valor_Total;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
