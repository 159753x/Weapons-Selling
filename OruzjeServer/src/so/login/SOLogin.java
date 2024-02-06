/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Administrator;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOLogin extends AbstractSO {
    
    Administrator ulogovani;
    
    //provera da li je admin vec ulogovan
    private void validateLoggedAdmin(AbstractDomainObject ado) throws Exception{
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
        Administrator a = (Administrator) ado;
        
        for (Administrator admin : ServerController.getInstance().listaUlogovanihAdmina) {
            if(admin.getUsername().equals(a.getUsername())){
                throw new Exception("Admin sa ovim kredencijalima je vec ulogovan!");
            }
        }
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
        validateLoggedAdmin(ado);
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                
                //ubacivanje proverenog admina u listu trenutno ulogovanih
//                ServerController.getInstance().listaUlogovanihAdmina.add(ulogovani);
                
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
        
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }
    
    

}
