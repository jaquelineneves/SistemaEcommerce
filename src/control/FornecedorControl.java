/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.dao.FornecedorDao;
import model.domain.Fornecedor;
import org.jdesktop.observablecollections.ObservableCollections;
import sun.awt.AWTAccessor;
import util.ValidacaoException;

/**
 *
 * @author ADMIN
 */
public final class FornecedorControl {
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Fornecedor fornecedorDigitado;
    private Fornecedor fornecedorSelecionado;
    private List<Fornecedor> fornecedoresTabela;
    private final FornecedorDao fornecedorDao;
    
    public FornecedorControl(){
        fornecedorDao = new FornecedorDao();
        fornecedoresTabela = ObservableCollections.observableList(new ArrayList<Fornecedor>());
        novo();
        pesquisar();
    }

    public void novo() {
        setFornecedorDigitado(new Fornecedor());
    }

    public void pesquisar() {
        fornecedoresTabela.clear();
        fornecedoresTabela.addAll(fornecedorDao.pesquisar(fornecedorDigitado));        
    }
    
    public void salvar() throws ValidacaoException{
        fornecedorDigitado.validar();
        fornecedorDao.salvarAtualizar(fornecedorDigitado);
        novo();
        pesquisar();
    }
    
    public void excluir(){
        fornecedorDao.excluir(fornecedorDigitado);
        novo();
        pesquisar();
    }

    public Fornecedor getFornecedorDigitado() {
        return fornecedorDigitado;
    }

    public void setFornecedorDigitado(Fornecedor fornecedorDigitado) {
        Fornecedor oldFornecedorDigitado = this.fornecedorDigitado;
        this.fornecedorDigitado = fornecedorDigitado;
        propertyChangeSupport.firePropertyChange("fornecedorDigitado", oldFornecedorDigitado,fornecedorDigitado);
    }

    public Fornecedor getFornecedorSelecionado() {
        return fornecedorSelecionado;
    }

    public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
        this.fornecedorSelecionado = fornecedorSelecionado;
        if (this.fornecedorSelecionado != null){
            setFornecedorDigitado(fornecedorSelecionado);
        }
    }

    public List<Fornecedor> getFornecedoresTabela() {
        return fornecedoresTabela;
    }

    public void setFornecedoresTabela(List<Fornecedor> fornecedoresTabela) {
        this.fornecedoresTabela = fornecedoresTabela;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener p){
        propertyChangeSupport.addPropertyChangeListener(p);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener p){
        propertyChangeSupport.removePropertyChangeListener(p);
    }
    
}
