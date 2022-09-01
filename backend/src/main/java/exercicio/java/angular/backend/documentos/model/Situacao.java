package exercicio.java.angular.backend.documentos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="estados_documento")
public class Situacao {

    public static final Long NOVO = 1L;
    public static final Long TRANSFERIDO = 2L;

    public static final Long ENCERRADO = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Override
    public  boolean equals(Object obj) {

        if (obj == null) {
            return  false;
        }

        if (obj== this) {
            return true;
        }

        if(!(obj instanceof Situacao)) return false;

        Situacao situacao = (Situacao)obj;

        return this.getId() == situacao.getId() && this.getNome() == situacao.getNome();

    }
}
