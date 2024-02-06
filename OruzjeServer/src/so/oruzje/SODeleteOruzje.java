/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.oruzje;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Oruzje;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SODeleteOruzje extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Oruzje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oruzje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
