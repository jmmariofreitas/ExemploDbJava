package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import entity.Usuario;
import conexao.Conexao;

public class UsuarioDAO {

    public void cadastrarUsuario(Usuario usuario){
        String sql = "INSERT INTO USUARIO (nome, login, senha, email, idade) VALUES (?,?,?,?,?)";

        try (Connection connection = Conexao.gConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getIdade());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exibirUsuarios() {
        String sql = "SELECT nome, login, senha, email, idade FROM USUARIO";

        try (Connection connection = Conexao.gConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setIdade(resultSet.getString("idade"));

                System.out.println("Nome: " + usuario.getNome());
                System.out.println("Login: " + usuario.getLogin());
                System.out.println("Senha: " + usuario.getSenha());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("Idade: " + usuario.getIdade());
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> retornaUsuarios() {
    List<Usuario> listaUsuarios = new ArrayList<>();

    String sql = "SELECT idusuario,nome, login, senha, email, idade FROM USUARIO";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(resultSet.getString("idusuario"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setIdade(resultSet.getString("idade"));

            listaUsuarios.add(usuario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaUsuarios;
 }

 public void excluirUsuario(int idUsuario) {
    String sql = "DELETE FROM USUARIO WHERE idusuario = ?";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idUsuario);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum usuário foi excluído.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao excluir usuário.");
    }
}
public Usuario buscarUsuario(int id) {
    Usuario usuario = null;
    String sql = "SELECT * FROM USUARIO WHERE idusuario = ?";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, id); // Identificar o parâmetro na consulta SQL
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(resultSet.getString("idusuario"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setIdade(resultSet.getString("idade"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return usuario;
}


public boolean atualizarUsuario(Usuario usuario) {
    String sql = "UPDATE USUARIO SET nome = ?, login = ?, senha = ?, email = ?, idade = ? WHERE idusuario = ?";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getLogin());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getEmail());
        ps.setString(5, usuario.getIdade());
        ps.setString(6, usuario.getIdUsuario());

        int rowsUpdated = ps.executeUpdate();

        return rowsUpdated > 0; // Retorna verdadeiro se a atualização foi bem-sucedida
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Retorna falso em caso de falha na atualização
}





}
