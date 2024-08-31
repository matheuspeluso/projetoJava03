package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoController {

	private Scanner scanner = new Scanner(System.in);
	/*
	 * Método para fazer a captura dos dados do produto para realização do cadastro
	 */

	public void cadastrarProduto() {
		try {
			System.out.println("CADASTRO DE PRODUTOS....:");
			// capturar os dados do produto
			System.out.print("NOME DO PRODUTO...:");
			var nome = scanner.nextLine();

			System.out.print("PREÇO....:");
			var preco = Double.parseDouble(scanner.nextLine());

			System.out.print("QUANTIDADE....:");
			var quantidade = Integer.parseInt(scanner.nextLine());
			
			//scanner.close();

			var produto = new Produto(UUID.randomUUID(), nome, preco, quantidade);
			
			//enviar o produto para ser cadastrado no banco de dados
			var produtoRepository = new ProdutoRepository();
			produtoRepository.inserir(produto);
			
		} catch (Exception e) {
			System.out.println("\nFalha ao cadastrar o produto!");
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarProduto() {
		try {
			System.out.println("\nATUALIÇÃO DE PRODUTOS: \n");
			System.out.println("INFORME O ID DO PRODUTO.:");
			var id = UUID.fromString(scanner.nextLine());
			
			//consultar o produto no banco de dados através do id
			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);
			
			//verificar se o produto foi encontrado!
			if(produto != null) {
				
				System.out.println("\nDADOS DO PRODUTO>");
				System.out.println("ID........:" +produto.getId());
				System.out.println("NOME........: "+ produto.getNome());
				System.out.println("PREÇO.....: "+ produto.getPreco());
				System.out.println("QUANTIDADE....: "+produto.getQuantidade());
				System.out.println("");
				
				System.out.println("ALTERE O NOME...:");
				produto.setNome(scanner.nextLine());
				
				System.out.println("ALTERE O PREÇO...:");
				produto.setPreco(Double.parseDouble(scanner.nextLine()));
				
				System.out.println("ALTERE A QUANTIDADE...:");
				produto.setQuantidade(Integer.parseInt(scanner.nextLine()));
				
				//atualizar os dados do produto la no banco
				
				produtoRepository.atualizar(produto);
			}
			else {
				System.out.println("\nProduto não encontrado. Verifique o ID informado.");
			}
			
		}
		catch(Exception e ) {
			System.out.println("\nFalha ao atualizar o produto!");
			System.out.println(e.getMessage());
		}
	}
	
	public void excluirProduto() {
		try {
			System.out.println("\nEXCLUSÃO DE PRODUTOS: \n");
			
			System.out.println("INFORME O ID DO PRODUTO.:");
			var id = UUID.fromString(scanner.nextLine());
			
			//consultar o produto no banco de dados através do id
			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);
			
			//verificar se o produto foi encontrado!
			if(produto != null) {
				
				System.out.println("\nDADOS DO PRODUTO>");
				System.out.println("ID........:" +produto.getId());
				System.out.println("NOME........: "+ produto.getNome());
				System.out.println("PREÇO.....: "+ produto.getPreco());
				System.out.println("QUANTIDADE....: "+produto.getQuantidade());
				System.out.println("");
				
				
				
				//excluir os dados do produto la no banco
				
				produtoRepository.excluir(produto.getId());
			}
			else {
				System.out.println("\nProduto não encontrado. Verifique o ID informado.");
			}
			
		}
		catch(Exception e ) {
			System.out.println("\nFalha ao excluir o produto!");
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Método para consultar todos os produtos
	 * e imprimi-lo no terminal
	 */
	
	public void consultarProdutos() {
		try {
			System.out.println("\nCONSULTA DE PRODUTOS:\n");
			//consultar os produtos la no banco de dados
			
			var produtoRepository = new ProdutoRepository();
			var lista = produtoRepository.consultar();
			
			for(Produto produto: lista) {
				System.out.println("ID.....: "+ produto.getId());
				System.out.println("NOME.....: "+produto.getNome());
				System.out.println("PREÇO....: "+produto.getPreco());
				System.out.println("QUANTIDADE..: "+ produto.getQuantidade());
				
				System.out.println("");
			}
			
		}
		catch(Exception e) {
			System.out.println("\nFalha ao consultar os produtos!");
			System.out.println(e.getMessage());
		}
	}
	
}
