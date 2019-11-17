/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import util.ValidacaoException;

/**
 *
 * @author juan
 */
@Entity
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length= 255, nullable=false)
    private String nome;
    
    @Column(nullable=false)
    private Long anoNasc;
    
    @Column(nullable = false)
    private int PCD;
    
    @ManyToOne
    @JoinColumn(name="turma_id")
    private Turma turma;

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

    public Long getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(Long anoNasc) {
        this.anoNasc = anoNasc;
    }

    public int getPCD() {
        return PCD;
    }

    public void setPCD(int PCD) {
        this.PCD = PCD;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
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
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String dadosAluno() {
        return "Dados do Aluno:" 
                + "\nMatrícula: " + id 
                + "\nNome: " + nome 
                + "\nAno de Nascimento: " + anoNasc 
                + "\nPCD: " + ((PCD == 1) ? "Sim" : "Não") 
                + "\nTurma: " + turma.getNome();
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + '}';
    }
    
    public void validar() throws ValidacaoException{
        //Validação do nome se a validação por algum motivo não funcionar na tela
        if (this.nome == null || this.nome.equals("")) {
            throw new ValidacaoException("Campo nome precisa ser preenchido");
        }
        //validação do campo ano se o da tela não funcionar
        if(this.anoNasc == null) {
            throw new ValidacaoException("Campo ano de nascimento precisa ser preenchido");
        }
        else if(this.anoNasc.toString().length() != 4 || this.anoNasc.toString().length() < 4) {
            throw new ValidacaoException("Campo ano de nascimento precisa ter até 4 caracteres");
        }
        if (this.turma == null) {
            throw new ValidacaoException("Uma Turma deve ser selecionada");
        }
    }
}
