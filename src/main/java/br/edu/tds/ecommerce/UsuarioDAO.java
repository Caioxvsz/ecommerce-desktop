/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.tds.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aluno
 */
public class UsuarioDAO {

    // CADASTRAR USUÁRIO
    public void cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, usuario, email, cpf, senha) VALUES (?,?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getSenha());
            stmt.executeUpdate();
            System.out.println("Usuário" + usuario.getNomeUsuario() + "foi cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    // VALIDAR LOGIN
    public boolean login(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareCall(sql)) {

        stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // se encontrou, login válido

        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
            return false;
        }
        
    }
    //Autaliza usuários
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, usuario=?, email=?, cpf=?, senha=? WHERE usuario=?";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getNomeUsuario());
            stmt.executeUpdate();
            System.out.println("Usuário " + usuario.getNomeCompleto() + " foi atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

}

