package teste.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "curso_disciplina", 
        joinColumns = @JoinColumn(name = "curso_id"), 
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;


     @Transient
    public String getDisciplinasFormatadas() {
        if (disciplinas == null || disciplinas.isEmpty()) return "";
        return disciplinas.stream()
                          .map(Disciplina::getNome)
                          .collect(Collectors.joining(", "));
    }
}
