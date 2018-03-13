package builders;

import java.util.Date;

import com.clic00.cursomc.domain.Pedido;

public class PedidoBuilder {
	
		private Pedido pedido;
	
		private PedidoBuilder() {
		}
		
		public static PedidoBuilder umPedido() {
			PedidoBuilder builder = new PedidoBuilder();
			builder.pedido = new Pedido();
			builder.pedido.setId(1);
			builder.pedido.setInstante(new Date());
			return builder;
		}
		
		public Pedido agora() {
			return pedido;
		}
}
