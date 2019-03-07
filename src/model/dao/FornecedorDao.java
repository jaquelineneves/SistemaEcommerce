/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.domain.Fornecedor;

/**
 *
 * @author ADMIN
 */
public interface FornecedorDao {

    void excluir(Fornecedor fornecedor);

    List<Fornecedor> pesquisar(Fornecedor fornecedor);

    void salvarAtualizar(Fornecedor fornecedor);
    
}
