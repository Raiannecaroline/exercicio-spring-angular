package exercicio.java.angular.backend.historicos.service.impl;

import exercicio.java.angular.backend.historicos.model.Historico;
import exercicio.java.angular.backend.historicos.repository.HistoricoRepository;
import exercicio.java.angular.backend.historicos.service.IHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService implements IHistoricoService {
    @Autowired
    private HistoricoRepository repository;

    @Override
    public List<Historico> listAll(Long documento_id) {
        return repository.listAll(documento_id);
    }
}
