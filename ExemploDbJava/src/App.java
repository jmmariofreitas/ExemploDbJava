
import DAO.UsuarioDAO;
import entity.Usuario;


public class App  {
    public static void main(String[] args) throws Exception {

      //  UsuarioDAO usuarioDAO = new UsuarioDAO();
       
        

     java.awt.EventQueue.invokeLater(() -> {
        MainApp app = new MainApp();
        app.setVisible(false);
    });
       
    java.awt.EventQueue.invokeLater(() -> {
        CadastroUsuario app = new CadastroUsuario();
        app.setVisible(true);
    }); 



    }
}
