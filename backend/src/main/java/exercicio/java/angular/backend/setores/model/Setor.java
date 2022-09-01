package exercicio.java.angular.backend.setores.model;

import exercicio.java.angular.backend.documentos.model.Situacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "setores")
public class Setor {

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

        if(!(obj instanceof Setor)) return false;

        Setor setor = (Setor)obj;

        return this.getId() == setor.getId() && this.getNome() == setor.getNome();

    }

}
