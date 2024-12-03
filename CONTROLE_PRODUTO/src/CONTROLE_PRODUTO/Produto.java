package CONTROLE_PRODUTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Representa um produto com os atributos nome, preço de custo e preço de venda.
 */
public class Produto {
    private String nome;
    private double precoCusto;
    private double precoVenda;

    /** 
     * Construtor Produto com os parâmetros especificados. 
     * 
     * @param nome o nome do produto 
     * @param precoCusto o preço de custo do produto 
     * @param precoVenda o preço de venda do produto 
     */
    public Produto(String nome, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    /** 
     * Calcula o lucro do produto com base no preço de venda e preço de custo. 
     * 
     * @return o lucro do produto 
     */
    public double calcularLucro() {
        return precoVenda - precoCusto;
    }

    /** 
     * Método para adicionar o produto no banco de dados. 
     */
    public void adicionar() {
        try (Connection conexao = Conexao.conectar()) {
            String sql = "INSERT INTO Produto (nome, preco_custo, preco_venda) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setDouble(2, precoCusto);
                stmt.setDouble(3, precoVenda);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** 
     * Método para deletar o produto no banco de dados. 
     */
    public void deletar() {
        try (Connection conexao = Conexao.conectar()) {
            String sql = "DELETE FROM Produto WHERE nome = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** 
     * Método para atualizar o produto no banco de dados. 
     */
    public void atualizar() {
        try (Connection conexao = Conexao.conectar()) {
            String sql = "UPDATE Produto SET preco_custo = ?, preco_venda = ? WHERE nome = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setDouble(1, precoCusto);
                stmt.setDouble(2, precoVenda);
                stmt.setString(3, nome);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}

