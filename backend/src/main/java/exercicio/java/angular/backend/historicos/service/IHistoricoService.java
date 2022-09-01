package exercicio.java.angular.backend.historicos.service;
import exercicio.java.angular.backend.historicos.model.Historico;
import java.util.List;

public interface IHistoricoService {

    List<Historico> listAll(Long documento_id);
}
