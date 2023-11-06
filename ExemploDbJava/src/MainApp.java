import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import DAO.UsuarioDAO;
import entity.Usuario;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {

    private JTable table;
    private UsuarioDAO usuarioDAO;
    private JButton excluirButton;
    private JButton editarButton;

    public MainApp() {
        usuarioDAO = new UsuarioDAO();
        setTitle("Usuários Cadastrados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        excluirButton = new JButton("Excluir");
        excluirButton.setBounds(480, 320, 100, 25); // Definindo a posição e o tamanho do botão
        excluirButton.addActionListener(e -> excluirUsuario());

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuario = JOptionPane.showInputDialog(null, "Digite o ID do usuário a ser excluído:");
                if (idUsuario != null && !idUsuario.isEmpty()) {
                    int id = Integer.parseInt(idUsuario);
                    usuarioDAO.excluirUsuario(id);
                    atualizarTabela(); // Método para atualizar a tabela após a exclusão
                }
            }
        });

        add(excluirButton);

        editarButton = new JButton("Editar");
        editarButton.setBounds(360, 320, 100, 25);
        editarButton.addActionListener(e -> editarUsuario());
        add(editarButton);

        // Criar modelo para a tabela
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);

        // Definir os títulos das colunas
        String[] columns = {"Id_Usuario","Nome", "Login", "Senha", "Email", "Idade"};
        model.setColumnIdentifiers(columns);

        // Adicionar a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        exibirDadosNaTabela();
    }

    void exibirDadosNaTabela() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Usuario> usuarios = usuarioDAO.retornaUsuarios();

        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getIdade()
            });
        }
    }

    private void excluirUsuario() {
        String id = JOptionPane.showInputDialog("Informe o ID do usuário a ser excluído:");

        if (id != null && !id.isEmpty()) {
            try {
                int idUsuario = Integer.parseInt(id);
                usuarioDAO.excluirUsuario(idUsuario);
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID de usuário inválido!");
            }
        }
    }

     // Método para atualizar a tabela após a exclusão de um usuário
     private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa os dados atuais na tabela
        exibirDadosNaTabela(); // Reexibe os dados atualizados
    }

    // ... (código anterior)

private void editarUsuario() {
    String idUsuario = JOptionPane.showInputDialog("Digite o ID do usuário a ser editado:");
    if (idUsuario != null && !idUsuario.isEmpty()) {
        int id = Integer.parseInt(idUsuario);
        Usuario usuario = usuarioDAO.buscarUsuario(id);

        if (usuario != null) {
            JFrame editarFrame = new JFrame();
            editarFrame.setTitle("Editar Usuário");
            editarFrame.setSize(300, 300);
            editarFrame.setLayout(null);

            JTextField nomeField = new JTextField(usuario.getNome());
            nomeField.setBounds(20, 20, 200, 25);
            editarFrame.add(nomeField);

            JTextField loginField = new JTextField(usuario.getLogin());
            loginField.setBounds(20, 50, 200, 25);
            editarFrame.add(loginField);

            JTextField senhaField = new JTextField(usuario.getSenha());
            senhaField.setBounds(20, 80, 200, 25);
            editarFrame.add(senhaField);

            JTextField emailField = new JTextField(usuario.getEmail());
            emailField.setBounds(20, 110, 200, 25);
            editarFrame.add(emailField);

            JTextField idadeField = new JTextField(usuario.getIdade());
            idadeField.setBounds(20, 140, 200, 25);
            editarFrame.add(idadeField);

            JButton salvarButton = new JButton("Salvar");
            salvarButton.setBounds(100, 180, 100, 25);
            salvarButton.addActionListener(e -> {
                // Atualiza o objeto usuário com os dados dos campos
                usuario.setNome(nomeField.getText());
                usuario.setLogin(loginField.getText());
                usuario.setSenha(senhaField.getText());
                usuario.setEmail(emailField.getText());
                usuario.setIdade(idadeField.getText());

                // Atualiza o usuário no banco de dados
                boolean atualizado = usuarioDAO.atualizarUsuario(usuario);

                if (atualizado) {
                    JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                    editarFrame.dispose(); // Fecha o frame de edição
                    atualizarTabela(); // Atualiza a tabela após edição
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário.");
                }
            });
            editarFrame.add(salvarButton);

            editarFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
        }
    }
}

// ... (código posterior)

private void editarUsuario( String nome, String login, String senha, String email, String idade) {
    Usuario usuario = new Usuario();
    
    usuario.setNome(nome);
    usuario.setLogin(login);
    usuario.setSenha(senha);
    usuario.setEmail(email);
    usuario.setIdade(idade);

    boolean atualizado = usuarioDAO.atualizarUsuario(usuario);

    if (atualizado) {
        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
    } else {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário.");
    }
}



    

   
}
