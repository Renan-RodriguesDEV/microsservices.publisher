package com.micoservice.publisher.controllers;
import com.micoservice.publisher.domain.dto.request.FraudAnalysisDTO;
import com.micoservice.publisher.domain.model.FraudAnalysis;
import com.micoservice.publisher.domain.services.FraudAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final FraudAnalysisService fraudAnalysisService;

    public PedidoController(FraudAnalysisService fraudAnalysisService) {
        this.fraudAnalysisService = fraudAnalysisService;
    }

    @GetMapping("/{id}")
    public FraudAnalysis get(@PathVariable Long id) {
        return fraudAnalysisService.findById(id);
    }

    @GetMapping
    public List<FraudAnalysis> get() {
        return fraudAnalysisService.findAll();
    }

    @PutMapping("/{id}")
    public FraudAnalysis put(@PathVariable Long id, @RequestBody FraudAnalysisDTO pedido) {
        return fraudAnalysisService.update(id, pedido);
    }

    @PostMapping
    public FraudAnalysis post(@RequestBody FraudAnalysisDTO pedido) {
        return fraudAnalysisService.create(pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fraudAnalysisService.deleteById(id);
    }
}

