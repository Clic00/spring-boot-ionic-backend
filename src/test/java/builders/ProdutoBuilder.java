package builders;

import com.clic00.cursomc.domain.Produto;

public class ProdutoBuilder {
	
	private Produto produto;
	
	private ProdutoBuilder() {
	}
	
	public static ProdutoBuilder umProduto() {
		ProdutoBuilder builder = new ProdutoBuilder();
		builder.produto = new Produto();
		builder.produto.setId(1);
		builder.produto.setNome("Mouse");
		builder.produto.setPreco(50.0);
		return builder;
	}
	
	public Produto agora() {
		return produto;
	}
}
