/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.narudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Narudzbina;
import domain.StavkaNarudzbine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddNarudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Narudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Narudzbina!");
        }

        Narudzbina narudzbina = (Narudzbina) ado;

        if (!narudzbina.getDatumIsporuke().after(new Date())) {
            throw new Exception("Datum isporuke mora biti u buducnosti!");
        }

        if (narudzbina.getStavkeNarudzbine().isEmpty()) {
            throw new Exception("Narudzbina mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        //uzimanje kljuca iz baze
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long narudzbinaID = tableKeys.getLong(1);
        
        
        Narudzbina narudzbina = (Narudzbina) ado;
        narudzbina.setNarudzbinaID(narudzbinaID);
        
        
        for (StavkaNarudzbine stavkaNarudzbine : narudzbina.getStavkeNarudzbine()) {
            stavkaNarudzbine.setNarudzbina(narudzbina);
            DBBroker.getInstance().insert(stavkaNarudzbine);
        }
        
    }

}
