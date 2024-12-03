package CONTROLE_PRODUTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Representa um produto de vestuário com tamanho, cor e material.
 * Estende a classe Produto.
 */
public class ProdutoVestuario extends Produto {
    private String tamanho;
    private String cor;
    private String material;

    public ProdutoVestuario(String nome, double precoCusto, double precoVenda, String tamanho, String cor, String material) {
        super(nome, precoCusto, precoVenda);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    @Override
    public void adicionar() {
        super.adicionar();
        try (Connection conexao = Conexao.conectar()) {
            String sql = "INSERT INTO ProdutoVestuario (produto_id, tamanho, cor, material) VALUES ((SELECT id FROM Produto WHERE nome = ?), ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, getNome());
                stmt.setString(2, tamanho);
                stmt.setString(3, cor);
                stmt.setString(4, material);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar() {
        super.deletar();
        try (Connection conexao = Conexao.conectar()) {
            String sql = "DELETE FROM ProdutoVestuario WHERE produto_id = (SELECT id FROM Produto WHERE nome = ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, getNome());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar() {
        super.atualizar();
        try (Connection conexao = Conexao.conectar()) {
            String sql = "UPDATE ProdutoVestuario SET tamanho = ?, cor = ?, material = ? WHERE produto_id = (SELECT id FROM Produto WHERE nome = ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, tamanho);
                stmt.setString(2, cor);
                stmt.setString(3, material);
                stmt.setString(4, getNome());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters e Setters
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}

