import classes.CittadiniServer;
import classes.Operators;
import classes.PostgressConnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args){
        try {

            PostgressConnection dbConnection = new PostgressConnection();

            Registry registroOperatori = LocateRegistry.createRegistry(1099);
            registroOperatori.bind("operatoriCV", new Operators(dbConnection));

            CittadiniServer.exec(dbConnection);

        }catch(Exception e){

        }
    }
}