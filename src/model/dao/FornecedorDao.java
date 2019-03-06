/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.domain.Fornecedor;

/**
 *
 * @author ADMIN
 */
public class FornecedorDao {
    
    public void salvarAtualizar(Fornecedor fornecedor){
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        if(fornecedor.getCodigo() != null){
            fornecedor = em.merge(fornecedor);//ele ja tem codigo, entao atualiza
        }
        em.persist(fornecedor); //ele nao tem codigo entao faz um insert no BD
        em.getTransaction().commit();
        em.close();        
    }
    
    public void excluir(Fornecedor fornecedor){
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        fornecedor = em.merge(fornecedor);//ele ja tem codigo, entao atualiza
        
        em.remove(fornecedor); //remove aquele fornecedor
        em.getTransaction().commit();
        em.close();        
    }
    
    public List<Fornecedor> pesquisar(Fornecedor fornecedor){
        EntityManager em = Conexao.getEntityManager();
        StringBuilder sql = new StringBuilder("from Fornecedor f Where 1 = 1");//1 = 1 pra nao ficar verificando se colocou where ou nao
        
        if(fornecedor.getCodigo() != null){ //ele passou codigo como parametro pra fazer uma consulta
            sql.append("and f.codigo = :codigo ");
        }
        
        if(fornecedor.getNome() != null && !fornecedor.getNome().equals("")){//se o fornecedor nao tiver nome ou nome com nada
            sql.append("and f.nome like :nome ");
        }
        
        Query query = em.createQuery(sql.toString());
        if(fornecedor.getCodigo() != null){
            query.setParameter("codigo", fornecedor.getCodigo());
        }
        if(fornecedor.getNome() != null && !fornecedor.getNome().equals("")){//se o fornecedor nao tiver nome ou nome com nada
            query.setParameter("nome","%"+fornecedor.getNome()+"%");
        }
        return query.getResultList();
    }
}
