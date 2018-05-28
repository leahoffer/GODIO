package business;

import java.util.ArrayList;
import java.util.List;

import dto.BonificacionDTO;
import dto.CondicionDTO;
import dto.CuentaCorrienteDTO;
import dto.DescuentoDTO;
import dto.MovimientoCCDTO;

public class CuentaCorriente {

	private int id;
	private float saldo;
	private float limite;
	private List<MovimientoCC> movimientos;
	private List<Condicion> condiciones;
	
	public CuentaCorriente()
	{
		this.movimientos = new ArrayList<MovimientoCC>();
		this.condiciones = new ArrayList<Condicion>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public float getLimite() {
		return limite;
	}
	public void setLimite(float limite) {
		this.limite = limite;
	}
	public List<MovimientoCC> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoCC> movimientos) {
		this.movimientos = movimientos;
	}
	public List<Condicion> getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public void agregarMovimiento(float monto, boolean signo) {
		MovimientoCC mcc = new MovimientoCC(monto, signo);
		this.movimientos.add(mcc);
	}

	public CuentaCorrienteDTO toDTO() {
		CuentaCorrienteDTO ccdto = new CuentaCorrienteDTO();
		List<MovimientoCCDTO> mccdtos = new ArrayList<MovimientoCCDTO>();
		List<CondicionDTO> cdtos = new ArrayList<CondicionDTO>();
		ccdto.setId(this.id);
		ccdto.setLimite(this.limite);
		ccdto.setSaldo(this.saldo);
		for (MovimientoCC mcc : this.movimientos)
		{
			mccdtos.add(mcc.toDTO());
		}
		ccdto.setMovimientos(mccdtos);
		for (Condicion c : this.condiciones)
		{
			if (c instanceof Bonificacion)
			{
				BonificacionDTO bdto = new BonificacionDTO();
				bdto.setCondicion(c.getCondicion());
				bdto.setMonto(((Bonificacion) c).getMonto());
				cdtos.add(bdto);
			}
			else if (c instanceof Descuento)
			{
				DescuentoDTO ddto = new DescuentoDTO();
				ddto.setCondicion(c.getCondicion());
				ddto.setPorcentaje(((Descuento) c).getPorcentaje());
				cdtos.add(ddto);
			}
		}
		ccdto.setCondiciones(cdtos);
		return ccdto;
	}
	
	
	
	
	
}
