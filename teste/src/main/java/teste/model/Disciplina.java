package teste.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Disciplina implements Serializable{
    

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;    

     @ManyToMany(mappedBy = "disciplinas", fetch = FetchType.LAZY) 
            private List<Curso> cursos = new ArrayList<>();
   
}
