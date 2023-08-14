package Class;

public class Itens {
    private int idItens;
    private int Lista_idLista;
    private String Nome_Produto;
    private String Marca;
    private int Quantidade;
    private double Valor_Produto;


    public int getIdItens() {
        return idItens;
    }

    public void setIdItens(int idItens) {
        this.idItens = idItens;
    }

    public int getLista_idLista() {
        return Lista_idLista;
    }

    public void setLista_idLista(int lista_idLista) {
        Lista_idLista = lista_idLista;
    }

    public String getNome_Produto() {
        return Nome_Produto;
    }

    public void setNome_Produto(String nome_Produto) {
        Nome_Produto = nome_Produto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }


    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public double getValor_Produto() {
        return Valor_Produto;
    }

    public void setValor_Produto(double valor_Produto) {
        Valor_Produto = valor_Produto;
    }
}

