/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Align;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class Funcionario implements GenericDomainWithContact {

    @EntiteValidation(action = Action.D_U, nameField = "codigo do funcionário")
    private Integer idFuncionario;
    @TableView(title = "Nome")
    @EntiteValidation(action = Action.I_U, nameField = "nome do funcionário")
    private String nome;
    @EntiteValidation(action = Action.I_D_U, nameField = "data de nascimento")
    private Date dataNascimento;
    @TableView(title = "Cargo",
    name = "descricao")
    @EntiteValidation(action = Action.I_U,
    inField = "idCargo",
    nameField = "cargo")
    private Cargo cargo;
    private String pis;
    private String carteiraTrabalho;
    private String rg;
    private String cpf;
    @EntiteValidation(action = Action.I_U, nameField = "data de contratação")
    private Date dataContratacao;
    @TableView(title = "Dia Pgto", align = Align.RIGHT)
    @EntiteValidation(action = Action.I_U,
    minValue = 1,
    maxValue = 30,
    nameField = "dia do pagamento")
    private int diaPagamento;
    @TableView(title = "Salário", typeMask = TypeMask.MONETARIO, align = Align.RIGHT)
    @EntiteValidation(action = Action.I_U, nameField = "salário")
    private BigDecimal salario;
    
    private List<TelefoneContato> telefoneContato;

    public List<TelefoneContato> getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(List<TelefoneContato> telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(String carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public int getDiaPagamento() {
        return diaPagamento;
    }

    public void setDiaPagamento(int diaPagamento) {
        this.diaPagamento = diaPagamento;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public void setIdGenericDomain(Object o) {
        idFuncionario = Integer.parseInt(o.toString());
    }

    @Override
    public Object getIdGenericDomain() {
        return idFuncionario;
    }
}
