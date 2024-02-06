/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Kompanija;
import domain.Narudzbina;
import domain.Oruzje;
import domain.StavkaNarudzbine;
import domain.TipOruzja;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.administrator.SOGetAllAdministrator;
import so.kompanija.SOGetAllKompanija;
import so.login.SOLogin;
import so.narudzbina.SOAddNarudzbina;
import so.narudzbina.SODeleteNarudzbina;
import so.narudzbina.SOGetAllNarudzbina;
import so.narudzbina.SOUpdateNarudzbina;
import so.oruzje.SOAddOruzje;
import so.oruzje.SODeleteOruzje;
import so.oruzje.SOGetAllOruzje;
import so.oruzje.SOUpdateOruzje;
import so.stavkaNarudzbine.SOGetAllStavkaNarudzbine;
import so.tipOruzja.SOGetAllTipOruzja;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;
    
    public ArrayList<Administrator> listaUlogovanihAdmina = new ArrayList<Administrator>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }


    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addOruzje(Oruzje oruzje) throws Exception {
        (new SOAddOruzje()).templateExecute(oruzje);
    }

    public void addNarudzbina(Narudzbina narudzbina) throws Exception {
        (new SOAddNarudzbina()).templateExecute(narudzbina);
    }

    public void deleteOruzje(Oruzje oruzje) throws Exception {
        (new SODeleteOruzje()).templateExecute(oruzje);
    }

    public void deleteNarudzbina(Narudzbina narudzbina) throws Exception {
        (new SODeleteNarudzbina()).templateExecute(narudzbina);
    }

    public void updateOruzje(Oruzje oruzje) throws Exception {
        (new SOUpdateOruzje()).templateExecute(oruzje);
    }

    public void updateNarudzbina(Narudzbina narudzbina) throws Exception {
        (new SOUpdateNarudzbina()).templateExecute(narudzbina);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Oruzje> getAllOruzje() throws Exception {
        SOGetAllOruzje so = new SOGetAllOruzje();
        so.templateExecute(new Oruzje());
        return so.getLista();
    }

    public ArrayList<Narudzbina> getAllNarudzbina() throws Exception {
        SOGetAllNarudzbina so = new SOGetAllNarudzbina();
        so.templateExecute(new Narudzbina());
        return so.getLista();
    }

    public ArrayList<TipOruzja> getAllTipOruzja() throws Exception {
        SOGetAllTipOruzja so = new SOGetAllTipOruzja();
        so.templateExecute(new TipOruzja());
        return so.getLista();
    }

    public ArrayList<Kompanija> getAllKompanija() throws Exception {
        SOGetAllKompanija so = new SOGetAllKompanija();
        so.templateExecute(new Kompanija());
        return so.getLista();
    }

    public ArrayList<StavkaNarudzbine> getAllStavkaNarudzbine(Narudzbina narudzbina) throws Exception {
        SOGetAllStavkaNarudzbine so = new SOGetAllStavkaNarudzbine();
        
        StavkaNarudzbine sn = new StavkaNarudzbine();
        sn.setNarudzbina(narudzbina);
        
        so.templateExecute(sn);
        return so.getLista();
    }

}
