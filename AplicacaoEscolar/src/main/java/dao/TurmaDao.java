/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import dominio.Aluno;
import dominio.Turma;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author juan
 */
public class TurmaDao {
    
    private final EntityManager em;

    public TurmaDao() {
        this.em = Conexao.getEntityManager();
    }
    
    public void salvarAtualizarTurma(Turma turma) { //deve informar a turma
        em.getTransaction().begin();
        if(turma.getId()!= null){
            turma = em.merge(turma);
        }
        em.persist(turma);
        em.getTransaction().commit();
    }
    
    public void removerTurma(Turma turma) {
        em.getTransaction().begin();
        turma = em.merge(turma);
        em.remove(turma);
        em.getTransaction().commit();
    }
    
    public Turma buscarTurma(Turma turma) {
        return em.find(Turma.class, turma.getId());
    }

    public List<Turma> listaTurmas() {
        return em.createQuery("select t from Turma t order by id").getResultList();
    }
    
}
