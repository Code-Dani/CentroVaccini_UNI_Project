package classes;

import java.sql.*;

/**
 * classe per la connessione al database e l'esecuzione delle query
 * @author Claudio
 */
public class PostgressConnection {

    private static final String url = "jdbc:postgresql://localhost:5432/ProgettoLabB";//per la selezione del database, la porta è quella inserita nell'installazione
    private static final String user = "postgres";//generalmente è postgres di default, per essere sicuri fare tasto destro proprietà sul database su pgAdmin
    private static final String password = "admin";//password inserita nell'installazione
    private Connection conn;

    /**
     * costruttore
     * @author Claudio
     */
    public PostgressConnection(){
        connect();
    }

    /**
     * metodo per la connessione al db tramite le credenziali
     * @author Claudio
     */
    public void connect() {

        try {

            //mi connettto
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * metodo per l'esecuzione della quesry
     * @param sql la query da eseguire
     * @return ResultSet i dati restituiti dalla query presenti nel database
     */
    public synchronized ResultSet SQLquery(String sql){
        try {

            System.out.println(sql);

            PreparedStatement statement = conn.prepareStatement(sql);

            return statement.executeQuery();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
