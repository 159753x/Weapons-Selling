/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Narudzbina;
import domain.Oruzje;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_NARUDZBINA:
                    ServerController.getInstance().addNarudzbina((Narudzbina) request.getData());
                    break;
                case Operation.ADD_ORUZJE:
                    ServerController.getInstance().addOruzje((Oruzje) request.getData());
                    break;
                case Operation.DELETE_NARUDZBINA:
                    ServerController.getInstance().deleteNarudzbina((Narudzbina) request.getData());
                    break;
                case Operation.DELETE_ORUZJE:
                    ServerController.getInstance().deleteOruzje((Oruzje) request.getData());
                    break;
                case Operation.UPDATE_NARUDZBINA:
                    ServerController.getInstance().updateNarudzbina((Narudzbina) request.getData());
                    break;
                case Operation.UPDATE_ORUZJE:
                    ServerController.getInstance().updateOruzje((Oruzje) request.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    response.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_KOMPANIJA:
                    response.setData(ServerController.getInstance().getAllKompanija());
                    break;
                case Operation.GET_ALL_NARUDZBINA:
                    response.setData(ServerController.getInstance().getAllNarudzbina());
                    break;
                case Operation.GET_ALL_ORUZJE:
                    response.setData(ServerController.getInstance().getAllOruzje());
                    break;
                case Operation.GET_ALL_STAVKA_NARUDZBINE:
                    response.setData(ServerController.getInstance().getAllStavkaNarudzbine((Narudzbina) request.getData()));
                    break;
                case Operation.GET_ALL_TIP_ORUZJA:
                    response.setData(ServerController.getInstance().getAllTipOruzja());
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = ServerController.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }

}
