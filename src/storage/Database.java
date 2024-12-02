package storage;

import model.Due;
import model.Owner;
import model.Payment;
import model.Unit;
import java.util.HashMap;


public class Database {
    protected static HashMap<String,Owner> owners= new HashMap<>();
    protected static HashMap<String,Unit> units= new HashMap<>();
    protected static HashMap<String, Due> dues = new HashMap<>();
    protected static HashMap<String, Payment> payments= new HashMap<>();
    protected Owner owner; protected Unit unit; protected Due due; protected Payment payment;
}
