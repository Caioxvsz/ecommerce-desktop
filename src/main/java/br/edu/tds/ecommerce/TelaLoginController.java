package br.edu.tds.ecommerce;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblSenha;

    @FXML
    private void abrirTelaCadastroUsuario() throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/tds/ecommerce/TelaCadastroUsuarios.fxml"));

            Parent root = loader.load();

            TelaCadastroUsuarioController controller = loader.getController();

            // Envia os dados da tela gerenciamento de Usuarios
            //para o controlador de Cadastro de Usuarios
            

            //Trocando de tela
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
    }

    @FXML
    private void realizarLogin() throws IOException {

        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();

        if (usuario.isEmpty() && senha.isEmpty()) {
            lblUsuario.setText("*Campo Usuário é obrigatório");
            lblSenha.setText("*Campo Usuário é obrigatório");
            System.out.println("Campo usuário e campo senha são obrigatórios");
            return;
        }

        if (usuario.isEmpty()) {
            lblUsuario.setText("*Campo Usuário é obrigatório");
            lblSenha.setText("");
            System.out.println("Campo usuário é obrigatório");
            return;
        }
        if (senha.isEmpty()) {
            lblUsuario.setText("");
            lblSenha.setText("*Campo Senha é obrigatório");
            System.out.println("Campo senha é obrigatório");
            return;
        }

        lblUsuario.setText("");
        lblSenha.setText("");

        UsuarioDAO dao = new UsuarioDAO();
        Boolean login = dao.login(usuario, senha);
        System.out.println(dao.login(usuario, senha));

        if (login) {
            //Login com sucesso
            txtUsuario.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 0 0 3 0;");
            txtSenha.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 0 0 3 0;");
            lblUsuario.setText("");
            lblSenha.setText("");
            System.out.println("Login feito");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/tds/ecommerce/TelaGerenciamentoUsuarios.fxml"));

            Parent root = loader.load();

            TelaGerenciamentoUsuariosController controller = loader.getController();

            // Envia os dados da tela gerenciamento de Usuarios
            //para o controlador de Cadastro de Usuarios
            

            //Trocando de tela
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            
        } else {
            //Falha no login (usuário ou senha inválido)
            lblUsuario.setText("Usuário/Senha incorreto(a)");
            lblSenha.setText("Usuário/Senha incorreto(a)");
            System.out.println("Falha no login");
        }

        System.out.println("Usuário: " + usuario);
        System.out.println("Senha: " + senha);
    }
}

