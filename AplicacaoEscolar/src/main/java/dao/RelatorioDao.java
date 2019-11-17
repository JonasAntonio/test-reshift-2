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
import javax.persistence.Query;

/**
 *
 * @author juan
 */
public class RelatorioDao {
    
    private final EntityManager em;

    public RelatorioDao() {
        this.em = Conexao.getEntityManager();
    }
     
    public Aluno pesquisarAluno(Long id) {
        return (Aluno) em.createQuery("select a from Aluno a where a.id = "+id).getSingleResult();
    }
   
    public List<Aluno> listarAlunosTurma(String nomeTurma, String opcaoOrdem) {
        return em.createQuery("select a from Aluno a join a.turma t where t.nome like"+"'"+nomeTurma+"' order by a."+opcaoOrdem).getResultList();
    }
    
    public List<Turma> listarTurmasOpcaoOrdem(String opcao, String ordem) {
        switch (opcao) {
            case "comPCD": {
                if(ordem.equals("alunos")) {
                    return em.createQuery("select t from Turma t join t.alunos a where a.PCD = "+1+" order by size(t.alunos)").getResultList();
                }
                else {
                    return em.createQuery("select t from Turma t join t.alunos a where a.PCD = "+1+" order by t."+ordem).getResultList();
                }
            }
            default:{
                if(ordem.equals("alunos")) {
                    return em.createQuery("select t from Turma t order by size(t.alunos)").getResultList();
                } else {
                    return em.createQuery("select t from Turma t order by "+ordem).getResultList();
                }
            }
        }
    }
}
