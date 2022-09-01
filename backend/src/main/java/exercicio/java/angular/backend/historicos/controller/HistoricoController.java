package exercicio.java.angular.backend.historicos.controller;

import exercicio.java.angular.backend.documentos.model.Documento;
import exercicio.java.angular.backend.historicos.model.Historico;
import exercicio.java.angular.backend.historicos.service.IHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("setores/{setorId}/pastas/{pastaId}/documentos/{documentoId}/historicos")
public class HistoricoController {
    @Autowired
    private IHistoricoService service;

    @GetMapping
    public List<Historico> listAll(
            @PathVariable Long documentoId) {
        return service.listAll(documentoId);
    }

}
