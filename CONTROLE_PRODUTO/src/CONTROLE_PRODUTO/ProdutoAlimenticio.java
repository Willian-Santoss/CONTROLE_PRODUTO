package CONTROLE_PRODUTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Representa um produto alimentício com data de validade e informações nutricionais.
 * Estende a classe Produto.
 */
public class ProdutoAlimenticio extends Produto {
    private Date dataValidade;
    private String informacoesNutricionais;

    public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda, Date dataValidade, String informacoesNutricionais) {
        super(nome, precoCusto, precoVenda);
        this.dataValidade = dataValidade;
        this.informacoesNutricionais = informacoesNutricionais;
    }

    @Override
    public void adicionar() {
        super.adicionar();
        try (Connection conexao = Conexao.conectar()) {
            String sql = "INSERT INTO ProdutoAlimenticio (produto_id, data_validade, informacoes_nutricionais) VALUES ((SELECT id FROM Produto WHERE nome = ?), ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, getNome());
                stmt.setDate(2, new java.sql.Date(dataValidade.getTime()));
                stmt.setString(3, informacoesNutricionais);
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
            String sql = "DELETE FROM ProdutoAlimenticio WHERE produto_id = (SELECT id FROM Produto WHERE nome = ?)";
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
            String sql = "UPDATE ProdutoAlimenticio SET data_validade = ?, informacoes_nutricionais = ? WHERE produto_id = (SELECT id FROM Produto WHERE nome = ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setDate(1, new java.sql.Date(dataValidade.getTime()));
                stmt.setString(2, informacoesNutricionais);
                stmt.setString(3, getNome());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters e Setters
    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getInformacoesNutricionais() {
        return informacoesNutricionais;
    }

    public void setInformacoesNutricionais(String informacoesNutricionais) {
        this.informacoesNutricionais = informacoesNutricionais;
    }
}

