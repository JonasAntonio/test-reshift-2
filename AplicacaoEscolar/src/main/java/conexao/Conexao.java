/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.ProviderUtil;

/**
 *
 * @author juan
 */
public class Conexao {
    
    public static EntityManagerFactory emf;
    private static Conexao conexao;
    
    public Conexao() {
        emf = Persistence.createEntityManagerFactory("ESCOLAPU");
    }
    
    public synchronized static EntityManager getEntityManager() {
        if(conexao == null) {
            conexao = new Conexao();
        }
        
        return emf.createEntityManager();
    }
}
