/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.narudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Narudzbina;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllNarudzbina extends AbstractSO {

    private ArrayList<Narudzbina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Narudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Narudzbina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> narudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Narudzbina>) (ArrayList<?>) narudzbine;
    }

    public ArrayList<Narudzbina> getLista() {
        return lista;
    }

}
