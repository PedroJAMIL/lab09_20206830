package com.example.lab09.dao;
import com.example.lab09.beans.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {

    public List<Transaccion> TransaccionesPorUsuarios(int usuarioId) {
        List<Transaccion> transacciones = new ArrayList<>();
        String sql = "SELECT idtransacciones, titulo, monto, tipo, descripcion, fecha FROM transacciones WHERE usuarios_idusuarios = ?"; //
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion();
                    transaccion.setIdtransacciones(rs.getInt("idtransacciones"));
                    transaccion.setTitulo(rs.getString("titulo"));
                    transaccion.setMonto(rs.getDouble("monto"));
                    transaccion.setTipo(rs.getString("tipo"));
                    transaccion.setDescripcion(rs.getString("descripcion"));
                    transaccion.setFecha(rs.getDate("fecha"));
                    transacciones.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    public boolean createTransaccion(Transaccion transaccion) {
        String sql = "INSERT INTO transacciones (monto, descripcion, titulo, fecha, usuarios_idusuarios, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, transaccion.getMonto());
            pstmt.setString(2, transaccion.getDescripcion());
            pstmt.setString(3, transaccion.getTitulo());
            pstmt.setDate(4, new Date(System.currentTimeMillis()));
            pstmt.setInt(5, transaccion.getUsuarios_idusuarios());
            pstmt.setString(6, transaccion.getTipo());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean BORRARtransaccion(int idtransaccion, int usuarioId) {
        String sql = "DELETE FROM transacciones WHERE idtransacciones = ? AND usuarios_idusuarios = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idtransaccion);
            pstmt.setInt(2, usuarioId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Transaccion TransaccionPorID(int idtransaccion) {
        Transaccion transaccion = null;
        String sql = "SELECT * FROM transacciones WHERE idtransacciones = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idtransaccion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                transaccion = new Transaccion();
                transaccion.setIdtransacciones(rs.getInt("idtransacciones"));
                transaccion.setMonto(rs.getDouble("monto"));
                transaccion.setDescripcion(rs.getString("descripcion"));
                transaccion.setTitulo(rs.getString("titulo"));
                transaccion.setFecha(rs.getDate("fecha"));
                transaccion.setUsuarios_idusuarios(rs.getInt("usuarios_idusuarios"));
                transaccion.setTipo(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaccion;
    }
}