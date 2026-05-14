/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.tds.ecommerce;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author douglas
 */
public class TelaCadastroUsuarioController implements Initializable {

  
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNomeCompleto;
    @FXML
    private Text lblTelaEditarUsuario;
    @FXML
    private Button btnCadastrar;
    
    private Usuario usuarioEdicao;
    
    
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblSenha;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblCpf;

    /**
     * Initializes the controller class.L
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void abrirTelaLogin() throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/tds/ecommerce/TelaLogin.fxml"));

        Parent root = loader.load();

        TelaLoginController controller = loader.getController();

        // Envia os dados da tela gerenciamento de Usuarios
        //para o controlador de Cadastro de Usuarios
        

        //Trocando de tela
        Stage stage = (Stage) txtNomeCompleto.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    

    @FXML
    private void inserirUsuario() throws IOException {

        String nome = txtNomeCompleto.getText();
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();
        String email = txtEmail.getText();
        String cpf = txtCpf.getText();

       
        lblUsuario.setText("");
        lblSenha.setText("");
        lblEmail.setText("");
        lblCpf.setText("");

        if (usuario.isEmpty() && senha.isEmpty() && email.isEmpty() && cpf.isEmpty()) {
            
            lblUsuario.setText("*Campo Usuário é obrigatório");
            lblSenha.setText("*Campo Usuário é obrigatório");
            lblEmail.setText("*Campo Email é obrigatório");
            lblCpf.setText("*Campo Cpf é obrigatório");
        }
        
        if (usuario.isEmpty()) {
            lblUsuario.setText("*Campo Usuário é obrigatório");
            System.out.println("Campo usuário é obrigatório");
        }
        if (senha.isEmpty()) {
            lblSenha.setText("*Campo Senha é obrigatório");
            System.out.println("Campo senha é obrigatório");
        }
        if (email.isEmpty()) {
            lblEmail.setText("*Campo Email é obrigatório");
            System.out.println("Campo Email é obrigatório");
        }
        if (cpf.isEmpty()) {
            lblCpf.setText("*Campo Cpf é obrigatório");
            System.out.println("Campo Cpf é obrigatório");
        }

        if (!(usuario.isEmpty() && senha.isEmpty() && email.isEmpty() && cpf.isEmpty())) {
            System.out.println("Cadastrando um usuário");
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = new Usuario(nome, usuario, email, senha, cpf);
            dao.cadastrar(u);
            System.out.println("Usuário " + usuario + " foi cadastrado");
            mostrarAlerta(u.getNomeCompleto() + "O cadastro de" + u.getNomeCompleto() + "foi atualizado com sucesso");
            App.setRoot("TelaGerenciamentoUsuarios");

        }

        System.out.println("Nome: " + nome);
        System.out.println("Usuário: " + usuario);
        System.out.println("Senha: " + senha);
        System.out.println("Email: " + email);
        System.out.println("CPF: " + cpf);
    }
    public void setUsuario(Usuario u){
        
        usuarioEdicao = u;
        txtNomeCompleto.setText(u.getNomeCompleto());
        txtUsuario.setText(u.getNomeUsuario());
        txtSenha.setText(u.getSenha());
        txtEmail.setText(u.getEmail());
        txtCpf.setText(u.getCpf());
        
        lblTelaEditarUsuario.setText("Atualizar conta de usuário");
        btnCadastrar.setText("Salvar");


    }
    
    private void mostrarAlerta(String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sistema");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    private void atualizarUsuario() {
        String nomeCompleto = txtNomeCompleto.getText();
        String nomeUsuario = txtUsuario.getText();
        String senha = txtSenha.getText();
        String email = txtEmail.getText();
        String cpf = txtCpf.getText();

        txtNomeCompleto.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0 0 3 0;");
        txtUsuario.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0 0 3 0;");
        txtSenha.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0 0 3 0;");
        txtEmail.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0 0 3 0;");
        txtCpf.setStyle("-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0 0 3 0;");

        if (nomeCompleto.isEmpty()) {
            txtNomeCompleto.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0 0 3 0;");
        }
        if (nomeUsuario.isEmpty()) {
            txtUsuario.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0 0 3 0;");
        }
        if (senha.isEmpty()) {
            txtSenha.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0 0 3 0;");
        }
        if (email.isEmpty()) {
            txtEmail.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0 0 3 0;");
        }
        if (cpf.isEmpty()) {
            txtCpf.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 0 0 3 0;");
        }

        if (!(nomeCompleto.isEmpty() || nomeUsuario.isEmpty() || senha.isEmpty() || email.isEmpty() || cpf.isEmpty())) {
            //Cadastrando um usuário no BD
            System.out.println("Entrei no método salvaré");
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = new Usuario(nomeCompleto, nomeUsuario, email, senha, cpf);
            dao.atualizar(u);
        }
    }


    
    @FXML
private void cadastrarUsuario() throws IOException {
   if (usuarioEdicao == null) {
       inserirUsuario();
   } else {
       atualizarUsuario();
   }
}


}

