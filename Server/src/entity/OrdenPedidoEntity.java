package entity;

import java.util.List;

import javassist.compiler.ast.Pair;

public class OrdenPedidoEntity {

	private int nro;
	private String estado;
	private PedidoEntity pedidoOrigen;
	private ProductoEntity producto;
	private int cantidadPedida;
//	private List<Pair<String, Float>> ultimosTresProv;
	private List <MovimientoReservaEntity> movReserva;
}
