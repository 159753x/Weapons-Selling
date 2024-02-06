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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addOruzje(Oruzje oruzje) throws Exception {
        sendRequest(Operation.ADD_ORUZJE, oruzje);
    }

    public void addNarudzbina(Narudzbina narudzbina) throws Exception {
        sendRequest(Operation.ADD_NARUDZBINA, narudzbina);
    }

    public void deleteOruzje(Oruzje oruzje) throws Exception {
        sendRequest(Operation.DELETE_ORUZJE, oruzje);
    }

    public void deleteNarudzbina(Narudzbina narudzbina) throws Exception {
        sendRequest(Operation.DELETE_NARUDZBINA, narudzbina);
    }

    public void updateOruzje(Oruzje oruzje) throws Exception {
        sendRequest(Operation.UPDATE_ORUZJE, oruzje);
    }

    public void updateNarudzbina(Narudzbina narudzbina) throws Exception {
        sendRequest(Operation.UPDATE_NARUDZBINA, narudzbina);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }

    public ArrayList<Oruzje> getAllOruzje() throws Exception {
        return (ArrayList<Oruzje>) sendRequest(Operation.GET_ALL_ORUZJE, null);
    }

    public ArrayList<Narudzbina> getAllNarudzbina() throws Exception {
        return (ArrayList<Narudzbina>) sendRequest(Operation.GET_ALL_NARUDZBINA, null);
    }

    public ArrayList<TipOruzja> getAllTipOruzja() throws Exception {
        return (ArrayList<TipOruzja>) sendRequest(Operation.GET_ALL_TIP_ORUZJA, null);
    }

    public ArrayList<Kompanija> getAllKompanija() throws Exception {
        return (ArrayList<Kompanija>) sendRequest(Operation.GET_ALL_KOMPANIJA, null);
    }

    public ArrayList<StavkaNarudzbine> getAllStavkaNarudzbine(Narudzbina narudzbina) throws Exception {
        return (ArrayList<StavkaNarudzbine>) sendRequest(Operation.GET_ALL_STAVKA_NARUDZBINE, narudzbina);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }
}
