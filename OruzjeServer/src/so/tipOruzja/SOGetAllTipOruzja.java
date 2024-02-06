/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tipOruzja;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.TipOruzja;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllTipOruzja extends AbstractSO {

    private ArrayList<TipOruzja> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TipOruzja)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TipOruzja!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> tipoviOruzja = DBBroker.getInstance().select(ado);
        lista = (ArrayList<TipOruzja>) (ArrayList<?>) tipoviOruzja;
    }

    public ArrayList<TipOruzja> getLista() {
        return lista;
    }

}
