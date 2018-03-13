package builders;

import com.clic00.cursomc.domain.Categoria;

public class CategoriaBuilder {
	
	private Categoria categoria;
	
	private CategoriaBuilder() {
	}
	
	public static CategoriaBuilder umaCategoria() {
		CategoriaBuilder builder = new CategoriaBuilder();
		builder.categoria = new Categoria();
		builder.categoria.setId(1);
		builder.categoria.setNome("Informática");
		return builder;
	}
	
	public Categoria agora() {
		return categoria;
	}
		
}
