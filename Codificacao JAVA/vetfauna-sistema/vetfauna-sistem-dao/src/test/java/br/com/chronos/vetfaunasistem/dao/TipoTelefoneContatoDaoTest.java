package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.tipotelefonecontato.TipoTelefoneContatoDao;
import br.com.chronos.vetfaunasistem.dao.tipotelefonecontato.TipoTelefoneContatoDaoImpl;
import br.com.chronos.vetfaunasistema.domain.TipoTelefoneContato;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TipoTelefoneContatoDaoTest {

    private TipoTelefoneContatoDao<TipoTelefoneContato> tipoTelefoneContatoDao;

    @Before
    public void instanceEntiteDao() {
        tipoTelefoneContatoDao = new TipoTelefoneContatoDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        TipoTelefoneContato tipoTelefoneContato = new TipoTelefoneContato();
        List<TipoTelefoneContato> l = tipoTelefoneContatoDao.select(tipoTelefoneContato);
        Assert.assertEquals(4, l.size());
    }
   
}
