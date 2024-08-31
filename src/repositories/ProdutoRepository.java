package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Produto;
import factories.ConnectionFactory;

public class ProdutoRepository {
	/*
	 * Método para receber um objeto produto e gravalo-lo na tabela do banco de
	 * dados do PostGreSQL
	 */
	public void inserir(Produto produto) {
		try {

			// abrindo conexão
			var connection = ConnectionFactory.getConnection();

			// escrever o script SQL para gravar os dados do produto da tabela

			var statement = connection
					.prepareStatement("INSERT INTO produto(id, nome, preco, quantidade) VALUES(?,?,?,?)");
			statement.setObject(1, produto.getId()); // prencendo o id
			statement.setString(2, produto.getNome());// prencendo o nome
			statement.setDouble(3, produto.getPreco());// prencendo o preço
			statement.setInt(4, produto.getQuantidade());// prencendo a quantidade
			statement.execute(); // executando o comando no banco de dados

			// FECHANDO A CONEXÃO COM O BANCO DE DADOS
			connection.close();

			System.out.println("\nPRODUTO CADASTRADO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("\nFalha ao inserir produto:");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(Produto produto) {
		try {
			// abrir conexão com o banco de dados
			var connection = ConnectionFactory.getConnection();

			var statement = connection.prepareStatement("UPDATE produto SET nome=?, preco=?, quantidade=? WHERE id=?");
			statement.setString(1, produto.getNome()); // preenchendo o nome
			statement.setDouble(2, produto.getPreco()); // preenchendo o preco
			statement.setInt(3, produto.getQuantidade()); // preenchendo a quantidade
			statement.setObject(4, produto.getId()); // preenchendo o id
			statement.execute();// executar o comando SQL

			// fechando a conexão
			connection.close();

		}

		catch (Exception e) {
			System.out.println("\nFalha ao atualizar produto!");
			System.out.println(e.getMessage());
		}
	}

	public void excluir(UUID id) {
		try {
			// abrindo conexão
			var connection = ConnectionFactory.getConnection();

			// escrever o comando SQL para excluir o banco de dados
			var statement = connection.prepareStatement("DELETE FROM produto WHERE id=?");
			statement.setObject(1, id);
			statement.execute();

			connection.close();
			System.out.println("\nPRODUTO EXCLUIDO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("\nFalha ao excluir produto!");
			System.out.println(e.getMessage());
		}
	}
	/*
	 * Método para consultar todos os produtos do bando de dados e retornar uma
	 * lista com esses produtos
	 */

	public List<Produto> consultar() {

		var lista = new ArrayList<Produto>();

		try {

			// abrir conexão com o banco de dados
			var connection = ConnectionFactory.getConnection();

			// escrevendo o comando sql para consultar os produtos no banco de dados
			var statement = connection.prepareStatement("SELECT id, nome, preco, quantidade FROM produto ORDER BY nome");
			var result = statement.executeQuery();

			while (result.next()) {
				// capturando os dados de cada produto lido de cada consulta
				var produto = new Produto();
				produto.setId(UUID.fromString(result.getString("id")));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));

				lista.add(produto);
			}
			// fechando a conexão do bando de dados
			connection.close();
		} catch (Exception e) {
			System.out.println("\nFalha ao consultar produtos!");
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public Produto obterPorId(UUID id) {

		Produto produto = null;

		try {
			// abrindo conexões com o banco de dados
			var connection = ConnectionFactory.getConnection();

			// escrever o comando sql que será executado no banco de dados
			var statement = connection.prepareStatement("SELECT id,nome,preco,quantidade FROM produto WHERE id=?");
			statement.setObject(1, id);
			var result = statement.executeQuery();

			// lendo o produto se for encontrado!
			if (result.next()) {
				produto = new Produto();

				produto.setId(UUID.fromString(result.getString("id")));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("\nFalha ao consultar por id!");
			System.out.println(e.getMessage());
		}

		return produto;
	}

}
