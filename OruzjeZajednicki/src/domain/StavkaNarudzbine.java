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
public class StavkaNarudzbine extends AbstractDomainObject {

    private Narudzbina narudzbina;
    private int rbStavke;
    private int kolicina;
    private double cenaStavke;
    private Oruzje oruzje;

    public StavkaNarudzbine(Narudzbina narudzbina, int rbStavke, int kolicina, double cenaStavke, Oruzje oruzje) {
        this.narudzbina = narudzbina;
        this.rbStavke = rbStavke;
        this.kolicina = kolicina;
        this.cenaStavke = cenaStavke;
        this.oruzje = oruzje;
    }

    public StavkaNarudzbine() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaNarudzbine ";
    }

    @Override
    public String alijas() {
        return " sn ";
    }

    @Override
    public String join() {
        return " JOIN NARUDZBINA N ON (N.NARUDZBINAID = SN.NARUDZBINAID) "
                + "JOIN KOMPANIJA K ON (K.KOMPANIJAID = N.KOMPANIJAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = N.ADMINISTRATORID) "
                + "JOIN ORUZJE O ON (O.ORUZJEID = SN.ORUZJEID) "
                + "JOIN TIPORUZJA TOR ON (TOR.TIPORUZJAID = O.TIPORUZJAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Kompanija k = new Kompanija(rs.getLong("KompanijaID"),
                    rs.getString("PIB"), rs.getString("nazivKompanije"),
                    rs.getString("adresa"), rs.getString("email"));

            Narudzbina narudzbina = new Narudzbina(rs.getLong("narudzbinaID"),
                    rs.getTimestamp("datumVreme"), rs.getDate("datumIsporuke"),
                    rs.getDouble("ukupnaCena"), k, a, null);

            TipOruzja to = new TipOruzja(rs.getLong("TipOruzjaID"),
                    rs.getString("NazivTipaOruzja"));

            Oruzje o = new Oruzje(rs.getLong("oruzjeID"), rs.getString("nazivOruzja"),
                    rs.getString("opis"), rs.getInt("kapacitetMetkova"),
                    rs.getDouble("cena"), to);

            StavkaNarudzbine sn = new StavkaNarudzbine(narudzbina,
                    rs.getInt("rbStavke"), rs.getInt("kolicina"),
                    rs.getDouble("cenaStavke"), o);

            lista.add(sn);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (narudzbinaID, rbStavke, kolicina, cenaStavke, oruzjeID) ";
    }

    @Override
    public String uslov() {
        return " narudzbinaID = " + narudzbina.getNarudzbinaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + narudzbina.getNarudzbinaID() + ", " + rbStavke + ", "
                + " " + kolicina + ", " + cenaStavke + ", " + oruzje.getOruzjeID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE N.NARUDZBINAID = " + narudzbina.getNarudzbinaID();
    }

    public Narudzbina getNarudzbina() {
        return narudzbina;
    }

    public void setNarudzbina(Narudzbina narudzbina) {
        this.narudzbina = narudzbina;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Oruzje getOruzje() {
        return oruzje;
    }

    public void setOruzje(Oruzje oruzje) {
        this.oruzje = oruzje;
    }

}
