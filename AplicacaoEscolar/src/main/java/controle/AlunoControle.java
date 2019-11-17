/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AlunoDao;
import dominio.Aluno;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javassist.tools.rmi.RemoteException;
import org.jdesktop.observablecollections.ObservableCollections;
import util.ValidacaoException;

/**
 *
 * @author juan
 */
public class AlunoControle {
    
    private final PropertyChangeSupport propertyChangeSupport = 
            new PropertyChangeSupport(this);  
    
    private Aluno alunoDigitado;
    private Aluno alunoSelecionado;
    private List<Aluno> alunosTabelas;
    private final AlunoDao alunoDao;

    public Aluno getAlunoDigitado() {
        return alunoDigitado;
    }

    public void setAlunoDigitado(Aluno alunoDigitado) {
        Aluno antigoAlunoDigitado = this.alunoDigitado;
        this.alunoDigitado = alunoDigitado;
        propertyChangeSupport.firePropertyChange
                ("alunoDigitado", antigoAlunoDigitado, alunoDigitado); 
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        if(this.alunoSelecionado != null){
            setAlunoDigitado(alunoSelecionado);
        }
        this.alunoSelecionado = alunoSelecionado;
    }

    public List<Aluno> getAlunosTabelas() {
        return alunosTabelas;
    }

    public void setAlunosTabelas(List<Aluno> alunosTabelas) {
        this.alunosTabelas = alunosTabelas;
    }
    
    public AlunoControle() {
        alunoDao = new AlunoDao();
        alunosTabelas = ObservableCollections.observableList(new ArrayList<>());
        novo();
        pesquisar();
    }
    
    public void salvar() throws ValidacaoException, RemoteException {
        alunoDigitado.validar();
        alunoDao.salvarAtualizarAluno(alunoDigitado); 
        novo();
        pesquisar();
    }
    
    public void remover() {
        alunoDao.removerAluno(alunoDigitado);
        novo();
        pesquisar();
    }
    
    public final void novo() {
        setAlunoDigitado(new Aluno());
    }

    public final void pesquisar() {
       alunosTabelas.clear();
       alunosTabelas.addAll(alunoDao.listaAlunos());
    }
    
    public void addPropertyChangeListener(PropertyChangeListener e) {
        propertyChangeSupport.addPropertyChangeListener(e);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener e) {
        propertyChangeSupport.removePropertyChangeListener(e);
    }
}
