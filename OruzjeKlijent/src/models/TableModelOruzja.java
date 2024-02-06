/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Oruzje;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelOruzja extends AbstractTableModel implements Runnable {

    private ArrayList<Oruzje> lista;
    private String[] kolone = {"ID", "Tip oruzja", "Naziv", "Kapacitet metkova", "Cena"};
    private String parametar = "";

    public TableModelOruzja() {
        try {
            lista = ClientController.getInstance().getAllOruzje();
        } catch (Exception ex) {
            Logger.getLogger(TableModelOruzja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Oruzje o = lista.get(row);

        switch (column) {
            case 0:
                return o.getOruzjeID();
            case 1:
                return o.getTipOruzja();
            case 2:
                return o.getNazivOruzja();
            case 3:
                return o.getKapacitetMetkova();
            case 4:
                return o.getCena() + "€";

            default:
                return null;
        }
    }

    public Oruzje getSelectedOruzje(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelOruzja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllOruzje();
            if (!parametar.equals("")) {
                ArrayList<Oruzje> novaLista = new ArrayList<>();
                for (Oruzje o : lista) {
                    if (o.getNazivOruzja().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(o);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
