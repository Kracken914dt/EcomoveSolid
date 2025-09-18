package com.EcoMove.PatronStrategy;

public class TarifaScooter implements TarifaStrategy {
	private final double rate = 0.8; // tarifa por minuto

	@Override
	public String tipoTransporte() { return "scooter"; }

	@Override
	public double calcular(int minutos) { return rate * minutos; }
}
