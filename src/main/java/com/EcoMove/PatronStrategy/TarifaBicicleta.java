package com.EcoMove.PatronStrategy;

public class TarifaBicicleta implements TarifaStrategy {
	private final double rate = 0.5; // tarifa por minuto

	@Override
	public String tipoTransporte() { return "bicicleta"; }

	@Override
	public double calcular(int minutos) { return rate * minutos; }
}
