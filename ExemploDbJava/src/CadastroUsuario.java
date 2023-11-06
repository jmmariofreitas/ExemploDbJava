import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import DAO.UsuarioDAO;
import entity.Usuario;


public class CadastroUsuario extends JFrame {
    private UsuarioDAO usuarioDAO;
    private JTextField nomeField, loginField, senhaField, emailField, idadeField;
 
    
    

    public CadastroUsuario() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        panel.add(lblNome);

        nomeField = new JTextField(20);
        nomeField.setBounds(120, 20, 200, 25);
        panel.add(nomeField);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(20, 50, 80, 25);
        panel.add(lblLogin);

        loginField = new JTextField(20);
        loginField.setBounds(120, 50, 200, 25);
        panel.add(loginField);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 80, 80, 25);
        panel.add(lblSenha);

        senhaField = new JTextField(20);
        senhaField.setBounds(120, 80, 200, 25);
        panel.add(senhaField);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 110, 80, 25);
        panel.add(lblEmail);

        emailField = new JTextField(20);
        emailField.setBounds(120, 110, 200, 25);
        panel.add(emailField);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(20, 140, 80, 25);
        panel.add(lblIdade);

        idadeField = new JTextField(20);
        idadeField.setBounds(120, 140, 200, 25);
        panel.add(idadeField);

        // Botão Cadastrar
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(120, 170, 100, 25);
        panel.add(cadastrarButton);
        cadastrarButton.addActionListener(e -> cadastrarNovoUsuario());

        // Botão Exibir Dados
        JButton exibirDadosButton = new JButton("Exibir Dados");
        exibirDadosButton.setBounds(240, 170, 120, 25);
        panel.add(exibirDadosButton);
        exibirDadosButton.addActionListener(e -> exibirDados() );

        add(panel);
        setSize(350, 250);

        
        panel.add(exibirDadosButton);
       

        

        add(panel);
        setSize(400, 360);
    }

    private void cadastrarNovoUsuario() {
        String nome = nomeField.getText();
        String login = loginField.getText();
        String senha = senhaField.getText();
        String email = emailField.getText();
        String idade = idadeField.getText();

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setLogin(login);
        novoUsuario.setSenha(senha);
        novoUsuario.setEmail(email);
        novoUsuario.setIdade(idade);

        usuarioDAO.cadastrarUsuario(novoUsuario);
        mostrarMensagemCadastroSucesso();
        limpaCampos();
    }
      public void mostrarMensagemCadastroSucesso() {
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
    }

    private void limpaCampos()
    {
        nomeField.setText("");
        loginField.setText("");
        senhaField.setText("");
        emailField.setText("");
        idadeField.setText("");
    }

    private void exibirDados() {
        MainApp app = new MainApp();
        app.setVisible(true);
    }
   
}
