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
public class Oruzje extends AbstractDomainObject {

    private Long oruzjeID;
    private String nazivOruzja;
    private String opis;
    private int kapacitetMetkova;
    private double cena;
    private TipOruzja tipOruzja;

    @Override
    public String toString() {
        return nazivOruzja + " (Tip: " + tipOruzja + ", Kapacitet metkova: " + kapacitetMetkova
                + ", Cena: " + cena + "€)";
    }

    public Oruzje(Long oruzjeID, String nazivOruzja, String opis, int kapacitetMetkova, double cena, TipOruzja tipOruzja) {
        this.oruzjeID = oruzjeID;
        this.nazivOruzja = nazivOruzja;
        this.opis = opis;
        this.kapacitetMetkova = kapacitetMetkova;
        this.cena = cena;
        this.tipOruzja = tipOruzja;
    }

    public Oruzje() {
    }

    @Override
    public String nazivTabele() {
        return " Oruzje ";
    }

    @Override
    public String alijas() {
        return " o ";
    }

    @Override
    public String join() {
        return " JOIN TIPORUZJA TOR ON (TOR.TIPORUZJAID = O.TIPORUZJAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TipOruzja to = new TipOruzja(rs.getLong("TipOruzjaID"),
                    rs.getString("NazivTipaOruzja"));

            Oruzje o = new Oruzje(rs.getLong("oruzjeID"), rs.getString("nazivOruzja"),
                    rs.getString("opis"), rs.getInt("kapacitetMetkova"),
                    rs.getDouble("cena"), to);

            lista.add(o);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (nazivOruzja, opis, kapacitetMetkova, cena, tipOruzjaID) ";
    }

    @Override
    public String uslov() {
        return " oruzjeID = " + oruzjeID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivOruzja + "', '" + opis + "', "
                + " " + kapacitetMetkova + ", " + cena + ", " + tipOruzja.getTipOruzjaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " nazivOruzja = '" + nazivOruzja + "', opis = '" + opis + "', "
                + "cena = " + cena + " ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getOruzjeID() {
        return oruzjeID;
    }

    public void setOruzjeID(Long oruzjeID) {
        this.oruzjeID = oruzjeID;
    }

    public String getNazivOruzja() {
        return nazivOruzja;
    }

    public void setNazivOruzja(String nazivOruzja) {
        this.nazivOruzja = nazivOruzja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKapacitetMetkova() {
        return kapacitetMetkova;
    }

    public void setKapacitetMetkova(int kapacitetMetkova) {
        this.kapacitetMetkova = kapacitetMetkova;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public TipOruzja getTipOruzja() {
        return tipOruzja;
    }

    public void setTipOruzja(TipOruzja tipOruzja) {
        this.tipOruzja = tipOruzja;
    }

}
