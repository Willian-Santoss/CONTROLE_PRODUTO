package CONTROLE_PRODUTO;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Testando a classe Produto
        Produto livro = new Produto("Livro", 20.00, 45.00);
        System.out.println("Produto: " + livro.getNome());
        System.out.println("Lucro: " + livro.calcularLucro());
        livro.adicionar();

        Produto notebook = new Produto("Notebook", 2000.00, 3500.00);
        System.out.println("Produto: " + notebook.getNome());
        System.out.println("Lucro: " + notebook.calcularLucro());
        notebook.adicionar();

        // Testando a classe ProdutoAlimenticio
        ProdutoAlimenticio iogurte = new ProdutoAlimenticio("Iogurte", 3.00, 6.00, new Date(), "Proteínas: 5g, Gorduras: 2g");
        System.out.println("Produto Alimentício: " + iogurte.getNome());
        iogurte.adicionar();

        ProdutoAlimenticio queijo = new ProdutoAlimenticio("Queijo", 15.00, 30.00, new Date(), "Proteínas: 25g, Gorduras: 20g");
        System.out.println("Produto Alimentício: " + queijo.getNome());
        queijo.adicionar();

        // Testando a classe ProdutoVestuario
        ProdutoVestuario jaqueta = new ProdutoVestuario("Jaqueta", 100.00, 200.00, "G", "Preto", "Couro");
        System.out.println("Produto de Vestuário: " + jaqueta.getNome());
        jaqueta.adicionar();

        // Atualizando um produto
        livro.setPrecoVenda(50.00);
        livro.atualizar();
        System.out.println("Novo preço de venda do produto: " + livro.getPrecoVenda());

        // Deletando um produto
        livro.deletar();
        System.out.println("Produto deletado: " + livro.getNome());
    }
}


