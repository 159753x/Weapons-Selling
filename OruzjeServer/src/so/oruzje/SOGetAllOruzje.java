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
public class SOGetAllOruzje extends AbstractSO {

    private ArrayList<Oruzje> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Oruzje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oruzje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaOruzja = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Oruzje>) (ArrayList<?>) listaOruzja;
    }

    public ArrayList<Oruzje> getLista() {
        return lista;
    }

}
