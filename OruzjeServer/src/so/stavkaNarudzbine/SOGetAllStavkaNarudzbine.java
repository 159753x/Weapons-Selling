/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkaNarudzbine;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaNarudzbine;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllStavkaNarudzbine extends AbstractSO {

    private ArrayList<StavkaNarudzbine> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaNarudzbine)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaNarudzbine!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkeNarudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaNarudzbine>) (ArrayList<?>) stavkeNarudzbine;
    }

    public ArrayList<StavkaNarudzbine> getLista() {
        return lista;
    }

}
