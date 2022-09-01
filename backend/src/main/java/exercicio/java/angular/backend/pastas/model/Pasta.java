package exercicio.java.angular.backend.pastas.model;

import exercicio.java.angular.backend.setores.model.Setor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="pastas")
public class Pasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="setores_id")
    private Setor setor;

    private String nome;

    @Override
    public  boolean equals(Object obj) {

        if (obj == null) {
            return  false;
        }

        if (obj== this) {
            return true;
        }

        if(!(obj instanceof Pasta)) return false;

        Pasta pasta = (Pasta)obj;

        if (this.setor == null && pasta.setor != null) {return  false;}

        return this.getId() == pasta.getId() && this.getNome() == pasta.getNome() && this.setor.equals(pasta.setor);

    }
}
