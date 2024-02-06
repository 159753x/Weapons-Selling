/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class TipOruzja extends AbstractDomainObject {
    
    private Long tipOruzjaID;
    private String nazivTipaOruzja;

    @Override
    public String toString() {
        return nazivTipaOruzja;
    }

    public TipOruzja(Long tipOruzjaID, String nazivTipaOruzja) {
        this.tipOruzjaID = tipOruzjaID;
        this.nazivTipaOruzja = nazivTipaOruzja;
    }

    public TipOruzja() {
    }
    
    @Override
    public String nazivTabele() {
        return " TipOruzja ";
    }

    @Override
    public String alijas() {
        return " TOR ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TipOruzja to = new TipOruzja(rs.getLong("TipOruzjaID"),
                    rs.getString("NazivTipaOruzja"));

            lista.add(to);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " TipOruzjaID = " + tipOruzjaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getTipOruzjaID() {
        return tipOruzjaID;
    }

    public void setTipOruzjaID(Long tipOruzjaID) {
        this.tipOruzjaID = tipOruzjaID;
    }

    public String getNazivTipaOruzja() {
        return nazivTipaOruzja;
    }

    public void setNazivTipaOruzja(String nazivTipaOruzja) {
        this.nazivTipaOruzja = nazivTipaOruzja;
    }
}
