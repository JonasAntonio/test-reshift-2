/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import dominio.Aluno;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author juan
 */
public class AlunoDao {
    
    private final EntityManager em;

    public AlunoDao() {
        this.em = Conexao.getEntityManager();
    }
    
    public void salvarAtualizarAluno(Aluno aluno) { //deve informar a turma
        em.getTransaction().begin();
        if(aluno.getId()!= null){
            aluno = em.merge(aluno);
        }
        em.persist(aluno);
        em.getTransaction().commit();
    }
    
    public void removerAluno(Aluno aluno) {
        em.getTransaction().begin();
        aluno = em.merge(aluno);
        em.remove(aluno);
        em.getTransaction().commit();
    }
    
    public Aluno buscarAluno(Aluno aluno) {
        return em.find(Aluno.class, aluno.getId());
    }

    public List<Aluno> listaAlunos() {
        return em.createQuery("select a from Aluno a order by nome").getResultList();
    }
    
}
