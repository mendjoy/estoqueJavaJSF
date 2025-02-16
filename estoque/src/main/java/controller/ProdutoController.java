package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.ProdutoDao;
import entity.Produto;

@ManagedBean
@RequestScoped
public class ProdutoController {

	private Produto produto;
	private ProdutoDao produtoDao;

	public ProdutoController() {
		this.produto = new Produto();
		this.produtoDao = new ProdutoDao();
		produto.setCadastro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void limparInputs() {
		produto = new Produto();
	}

	public void gravarProduto() {

		if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("cadastroProduto:descricao",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Necessário preencher a descrição"));
			return;
		}

		if (produto.getDescricao().length() > 50) {
			FacesContext.getCurrentInstance().addMessage("cadastroProduto:descricao",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Descrição muito longa"));
			return;
		}

		if (produto.getPreco() == null || produto.getPreco() == 0) {
			FacesContext.getCurrentInstance().addMessage("cadastroProduto:preco",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Necessário preencher o preço do produto"));
			return;
		}

		if (produto.getValidade() != null) {

			if (!validarData(produto.getValidade())) {
				FacesContext.getCurrentInstance().addMessage("cadastroProduto:dataValidade",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Data inválida"));
				return;
			}
		}

		if (produto.getCadastro() != null) {

			if (!validarData(produto.getCadastro())) {
				FacesContext.getCurrentInstance().addMessage("cadastroProduto:dataCadastro",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Data inválida"));
				return;
			}
		}

		produtoDao.salvar(produto);

		limparInputs();
		produto.setCadastro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));	

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Produto salvo com sucesso!"));
	}

	public boolean validarData(Date date) {

		try {

			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
