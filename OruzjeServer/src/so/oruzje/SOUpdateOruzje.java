/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.oruzje;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Oruzje;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateOruzje extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Oruzje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oruzje!");
        }

        Oruzje o = (Oruzje) ado;

        if (o.getCena() < 50 || o.getCena() > 10000) {
            throw new Exception("Cena mora biti izmedju 50€ i 10000€!");
        }

        ArrayList<Oruzje> oruzja = (ArrayList<Oruzje>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Oruzje oruzje : oruzja) {
            if (!oruzje.getOruzjeID().equals(o.getOruzjeID())) {
                if (oruzje.getNazivOruzja().equals(o.getNazivOruzja())) {
                    throw new Exception("Naziv oruzja mora biti jedinstven!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
