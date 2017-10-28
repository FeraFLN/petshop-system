package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.funcionario.FuncionarioDao;
import br.com.chronos.vetfaunasistem.dao.funcionario.FuncionarioDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Cargo;
import br.com.chronos.vetfaunasistema.domain.Funcionario;
import br.com.chronos.vetfaunasistema.domain.TelefoneContato;
import br.com.chronos.vetfaunasistema.domain.TipoTelefoneContato;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class FuncionarioDaoTest {

    private FuncionarioDao<Funcionario> funcionarioDao;

    @Before
    public void instanceEntiteDao() {
        funcionarioDao = new FuncionarioDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        Funcionario funcionario = new Funcionario();
        
        List<Funcionario> l = funcionarioDao.select(funcionario);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessInsert(){
        Funcionario funcionario = getEntidade();
        List<TelefoneContato> list = new ArrayList<>();
        TelefoneContato tc = new TelefoneContato();
        TipoTelefoneContato ttc = new TipoTelefoneContato();
        ttc.setIdTipoContato(1);
        tc.setDdd("85");
        tc.setNumero("98982626");
        tc.setStatusContato(StatusRegistro.NOVO);
        tc.setTipoTelefoneContato(ttc);
        list.add(tc);
        tc = new TelefoneContato();
        tc.setDdd("85");
        tc.setTipoTelefoneContato(ttc);
        tc.setNumero("98982625");
        tc.setStatusContato(StatusRegistro.NOVO);
        list.add(tc);
        funcionario.setTelefoneContato(list);
        funcionarioDao.insert(funcionario);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessUpdate(){
        Funcionario funcionario = getEntidade();
        funcionario.setIdFuncionario(1);
        funcionarioDao.update(funcionario);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessDelete(){
        Funcionario funcionario = getEntidade();
        funcionario.setIdFuncionario(3);
        List<TelefoneContato> list = new ArrayList<>();
        TelefoneContato tc = new TelefoneContato();
        TipoTelefoneContato ttc = new TipoTelefoneContato();
        ttc.setIdTipoContato(1);
        tc.setIdTelefoneContato(4);
        tc.setTipoTelefoneContato(ttc);
        tc.setStatusContato(StatusRegistro.DELETE);
        list.add(tc);
        funcionario.setTelefoneContato(list);
        funcionarioDao.insert(funcionario);
        funcionarioDao.delete(funcionario);
        Assert.assertTrue(true);
    }

    private Funcionario getEntidade() {
        Funcionario f = new Funcionario();
        f.setCargo(new Cargo(3));
        f.setNome("Fernando");
        f.setDataContratacao(new Date());
        f.setDataNascimento(new Date());
        f.setDiaPagamento(5);
        f.setSalario(new BigDecimal("5000.00"));
        return f;
    }
}
