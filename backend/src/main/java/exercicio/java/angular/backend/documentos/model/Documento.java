package exercicio.java.angular.backend.documentos.model;

import exercicio.java.angular.backend.pastas.model.Pasta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estados_documento_id")
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "pastas_id")
    private Pasta pasta;

    private String titulo;

    @Override
    public  boolean equals(Object obj) {

        if (obj == null) {
            return  false;
        }

        if (obj== this) {
            return true;
        }

        if(!(obj instanceof Documento)) return false;

        Documento documento = (Documento)obj;

        if (this.pasta == null && documento.pasta != null) {return  false;}

        return this.getId() == documento.getId() && this.getTitulo() == documento.getTitulo() && this.pasta.equals(documento.pasta);

    }
}
