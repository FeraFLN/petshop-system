/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.cargo;

import br.com.chronos.vetfaunasistem.dao.cargo.CargoDao;
import br.com.chronos.vetfaunasistem.dao.cargo.CargoDaoImpl;
import br.com.chronos.vetfaunasistema.business.cargo.exception.CargoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Cargo;

/**
 *
 * @author Fernando
 */
public class CargoBusinessImpl extends GenericBusinessImpl<Cargo, CargoDao<Cargo>, CargoBusinessException>  implements CargoBusiness {

    private CargoDao<Cargo> cargoDao;

    public CargoBusinessImpl() {
        cargoDao = new CargoDaoImpl();
    }

    public CargoBusinessImpl(CargoDao cargoDao) {
        this.cargoDao = cargoDao;
    }

    @Override
    public CargoDao<Cargo> getVetfaunaDao() {
        return cargoDao;
    }
}
