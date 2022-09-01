package exercicio.java.angular.backend.historicos.repository;

import exercicio.java.angular.backend.historicos.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Query("select h from Historico h where h.documentos_id = :documentoId")
    List<Historico> listAll(Long documentoId);

}
