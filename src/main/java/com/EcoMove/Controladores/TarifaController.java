package com.EcoMove.Controladores;

import com.EcoMove.dto.TarifaCalcRequest;
import com.EcoMove.PatronStrategy.TarifaStrategy;
import com.EcoMove.PatronStrategy.TarifaBicicleta;
import com.EcoMove.PatronStrategy.TarifaScooter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tarifas")
public class TarifaController {

    @GetMapping("/{tipo}")
    public ResponseEntity<?> obtenerRate(@PathVariable String tipo) {
        TarifaStrategy strategy = selectStrategy(tipo);
        return ResponseEntity.ok(Map.of("tipo", tipo, "rate", strategy.calcular(1) ));
    }

    @PostMapping("/calc")
    public ResponseEntity<?> calcular(@RequestBody TarifaCalcRequest req) {
        if (req == null || req.getMinutos() == null || req.getMinutos() < 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "minutos inválidos"));
        }
        TarifaStrategy strategy = selectStrategy(req.getTipo());
        double amount = strategy.calcular(req.getMinutos().intValue());
        return ResponseEntity.ok(Map.of("amount", amount));
    }

    private TarifaStrategy selectStrategy(String tipo) {
        if (tipo == null) return new TarifaBicicleta();
        String t = tipo.trim().toLowerCase();
        if (t.contains("scooter")) return new TarifaScooter();
        return new TarifaBicicleta();
    }
}
