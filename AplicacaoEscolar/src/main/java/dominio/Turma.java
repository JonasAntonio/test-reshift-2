/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.BatchSize;
import util.ValidacaoException;

/**
 *
 * @author juan
 */
@Entity
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 10, nullable = false)
    private String nome;
    
    @Column//limitar entre Fundamental 1, Fundamental e Médio
    private String ensino;
    
    @Column
    private Long ano;
    //Removendo a turma também apagam-se os alunos
    @OneToMany(mappedBy = "turma", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @BatchSize(size=40) //Limitamos a turma para somente 40 alunos
    private Collection<Aluno> alunos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnsino() {
        return ensino;
    }

    public void setEnsino(String ensino) {
        this.ensino = ensino;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public Collection<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Collection<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Turma[ id=" + id + " ]";
    }
    
    public int getTamanhoTurma() {
        return alunos.size();
    }

    public void validar() throws ValidacaoException{
        //Validação do nome se a validação por algum motivo não funcionar na tela
        if (this.nome == null || this.nome.equals("")) {
            throw new ValidacaoException("Campo nome precisa ser preenchido");
        } else if(this.nome.length() > 10) {
            throw new ValidacaoException("Campo nome precisa ter até 10 caracteres");
        }
        //validação do campo ano se o da tela não funcionar
        if(this.ano.toString().length() != 4 || this.ano.toString().length() < 4) {
            throw new ValidacaoException("Campo ano precisa ter até 4 caracteres");
        }
       
    }
}
