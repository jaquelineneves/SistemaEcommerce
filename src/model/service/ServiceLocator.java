/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.dao.FornecedorDao;
import model.dao.FornecedorDaoImpl;

/**
 *
 * @author ADMIN
 */
public class ServiceLocator {

    public static FornecedorDao getFornecedorDao() {
        return new FornecedorDaoImpl();
    }
}
