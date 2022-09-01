package exercicio.java.angular.backend.historicos.model;

import exercicio.java.angular.backend.setores.model.Setor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "historico_documentos")
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long documentos_id;

    private Date data;

    private String mudanca;

}
