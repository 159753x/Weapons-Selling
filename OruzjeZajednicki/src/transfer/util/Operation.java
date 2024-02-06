/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_ADMINISTRATOR = 1;

    public static final int ADD_ORUZJE = 2;
    public static final int DELETE_ORUZJE = 3;
    public static final int UPDATE_ORUZJE = 4;
    public static final int GET_ALL_ORUZJE = 5;

    public static final int ADD_NARUDZBINA = 6;
    public static final int DELETE_NARUDZBINA = 7;
    public static final int UPDATE_NARUDZBINA = 8;
    public static final int GET_ALL_NARUDZBINA = 9;

    public static final int GET_ALL_STAVKA_NARUDZBINE = 10;

    public static final int GET_ALL_TIP_ORUZJA = 11;
    
    public static final int GET_ALL_KOMPANIJA = 12;

}
