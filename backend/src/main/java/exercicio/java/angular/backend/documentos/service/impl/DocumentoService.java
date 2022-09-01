package exercicio.java.angular.backend.documentos.service.impl;

import exercicio.java.angular.backend.documentos.model.Documento;
import exercicio.java.angular.backend.documentos.model.Situacao;
import exercicio.java.angular.backend.documentos.repository.DocumentoRepository;
import exercicio.java.angular.backend.historicos.model.Historico;
import exercicio.java.angular.backend.historicos.repository.HistoricoRepository;
import exercicio.java.angular.backend.documentos.repository.SituacaoRepository;
import exercicio.java.angular.backend.documentos.service.IDocumentoService;
import exercicio.java.angular.backend.pastas.model.Pasta;
import exercicio.java.angular.backend.pastas.repository.PastaRepository;
import exercicio.java.angular.backend.setores.model.Setor;
import exercicio.java.angular.backend.setores.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService implements IDocumentoService {

    @Autowired
    private DocumentoRepository repository;

    @Autowired
    private PastaRepository pastaRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private SituacaoRepository situacaoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Override
    public List<Documento> listAll(Long setorId, Long pastaId, String q) {
        return repository.listAll(setorId, pastaId, q);
    }

    @Override
    public Optional<Documento> findById(Long setorId, Long pastaId, Long id) {
        return repository.findById(setorId, pastaId, id);
    }

    @Override
    @Transactional
    public Documento insert(Long setorId, Long pastaId, Documento documento) {

        Pasta pasta = validacoes(setorId, pastaId);
        documento.setPasta(pasta);
        Situacao novo = situacaoRepository.findById(Situacao.NOVO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "situação inválida"));
        documento.setSituacao(novo);
        Documento doc = repository.save(documento);
        setHistorico(documento, null);
        return doc;
    }

    @Override
    @Transactional
    public Documento update(Long setorId, Long pastaId, Documento documento) {
        Pasta pasta = validacoes(setorId, pastaId);
        Documento existente = repository.findById(documento.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "documento não existe"));
        setHistorico(existente, documento);
        if (!existente.getPasta().equals(pasta)) {
            Situacao transferido = situacaoRepository.findById(Situacao.TRANSFERIDO)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "situação inválida"));
            existente.setSituacao(transferido);
            existente.setPasta(pasta);
        }
        existente.setTitulo(documento.getTitulo());
        return repository.save(existente);
    }

    private  void setHistorico(Documento atual, Documento editado) {
        Historico historico = new Historico();
        historico.setDocumentos_id(atual.getId());
        historico.setData(new Date());
        if (editado == null) {
            historico.setMudanca("Inserido.");
            historicoRepository.save(historico);
        } else if (atual.equals(editado)) {
            historico.setMudanca("Salvo sem alteracao.");
            historicoRepository.save(historico);
        } else {
            setMudanca(historico, "Situacao",atual.getSituacao().getNome(), editado.getSituacao().getNome());
            setMudanca(historico, "Pasta",atual.getPasta().getNome(), editado.getPasta().getNome());
            setMudanca(historico, "Titulo",atual.getTitulo(), editado.getTitulo());
        }
    }

    private void setMudanca(Historico historico, String propriedade, String value1, String value2) {
        if (!value1.equals(value2)) {
            historico.setMudanca(propriedade + " alterado(a) - de: " + value1 + " para: " + value2);
            historicoRepository.save(historico);
        }
    }

    private Pasta validacoes(Long setorId, Long pastaId) {
        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "setor inválido"));
        Pasta pasta = pastaRepository.findById(pastaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "pasta inválida"));
        if (!pasta.getSetor().equals(setor))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pasta de setor diferente");
        return pasta;
    }

}
