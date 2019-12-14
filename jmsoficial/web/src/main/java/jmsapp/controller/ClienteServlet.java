package jmsapp.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmsapp.ejb.CarrinhoEjb;
import jmsapp.modelo.Cliente;
import jmsapp.modelo.Produto;
import jmsapp.service.ClienteService;

@WebServlet(name = "cliente", urlPatterns = "/cliente")
public class ClienteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	Cliente cliente;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		//vai pegar do ret o id do cliente
		//teste-ini
		
		
			 
			// DECLARANDO O OBJETO DA NOSSA CLASSE QUE ACESSA O SERVIÇO REST
			ClienteService client = new ClienteService();
	 
			// CAPTURA O CÓDIGO A SER CONSULTADO
			String id = req.getParameter("id");
	 
			// CONSULTA UMA PESSOA PELO CÓDIGO
			System.out.println(id);
			Cliente cliente = client.ConsultarPessoaPorCodigo(Integer.parseInt(id));
	 
			if (cliente == null) {
	 
				System.out.println("Cliente não encontrada!");
				
				req.setAttribute("mensagem", "Cliente não cadastrado");
				req.getRequestDispatcher("venda.jsp").forward(req, resp);
	 
			} else {
	 
				String resultado = null;
	 
				System.out.println(resultado);
				System.out.println(cliente);
				
				req.setAttribute("cliente", cliente);
				req.getRequestDispatcher("venda.jsp").forward(req, resp);

			}
	 
		


	}

}
