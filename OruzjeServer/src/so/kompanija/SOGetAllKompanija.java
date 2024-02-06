/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kompanija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kompanija;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllKompanija extends AbstractSO {

    private ArrayList<Kompanija> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kompanija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kompanija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kompanije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Kompanija>) (ArrayList<?>) kompanije;
    }

    public ArrayList<Kompanija> getLista() {
        return lista;
    }

}
