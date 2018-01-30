package sk.akademiasovy.tipos.main.main;

import sk.akademiasovy.tipos.main.Tipos;
import sk.akademiasovy.tipos.main.database.MySQLDatabase;

public class Main {
    public static void main (String[] args){
        Tipos tipos=new Tipos();
        tipos.generate();
        tipos.print();

        MySQLDatabase mySQLDatabase=new MySQLDatabase();
        mySQLDatabase.insertValuesIntoDrawHistory(tipos.getArr());

    }
}
