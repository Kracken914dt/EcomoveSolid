package com.EcoMove.PatronStrategy;

import org.springframework.stereotype.Service;

@Service
public class TarifaService {
    public double calcularTarifa(String tipoTransporte, int minutos) {
        TarifaStrategy strategy = selectStrategy(tipoTransporte);
        return strategy.calcular(minutos);
    }

    private TarifaStrategy selectStrategy(String tipo) {
        if (tipo == null) return new TarifaBicicleta();
        String t = tipo.trim().toLowerCase();
        if (t.contains("scooter")) return new TarifaScooter();
        return new TarifaBicicleta();
    }
}
