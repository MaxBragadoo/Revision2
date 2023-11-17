package com.project.controlcitas.DAO;

import com.project.controlcitas.DTO.Rol;
import com.project.controlcitas.config.Conexion;
import com.project.controlcitas.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean save(Rol objeto) {
        try {
            String query = "INSERT INTO Rol "
                    + "(ID_ROL,CODIGOROL,NOMBRE,DESCRIPCION,ESTATUS,FECHAALTA) VALUES"
                    + "(SEQ_Rol.NEXTVAL,?,?,?,?,?)";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoRol());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getDescripcion());
            ps.setString(4, objeto.getEstatus());
            ps.setString(5, Utils.SDF.format(objeto.getFechaAlta()));

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo registrar el Rol.\n" + e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.MESSAGE = "No se pudo cerrar la conexión";
            }
        }
        return false;
    }

    public static boolean update(Rol objeto) {
        try {
            String query = "UPDATE Rol SET "
                    + " CODIGOROL = ?,NOMBRE = ?,DESCRIPCION = ?,ESTATUS = ?,FECHAALTA = ?"
                    + " WHERE ID_ROL = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoRol());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getDescripcion());
            ps.setString(4, objeto.getEstatus());
            ps.setString(5, Utils.SDF.format(objeto.getFechaAlta()));
            ps.setInt(6, objeto.getIdRol());

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo actualizar el Rol.\n" + e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.MESSAGE = "No se pudo cerrar la conexión";
            }
        }
        return false;
    }

    public static boolean delete(int id) {
        try {
            String query = "DELETE FROM Rol"
                    + " WHERE ID_ROL = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo eliminar el Rol.\n" + e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.MESSAGE = "No se pudo cerrar la conexión";
            }
        }
        return false;
    }

    public static List<Rol> getData(ResultSet rs) {
        List<Rol> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Rol objeto = new Rol();
                objeto.setIdRol(rs.getInt("ID_ROL"));
                objeto.setCodigoRol(rs.getString("CODIGOROL"));
                objeto.setNombre(rs.getString("NOMBRE"));
                objeto.setDescripcion(rs.getString("DESCRIPCION"));
                objeto.setEstatus(rs.getString("ESTATUS"));
                objeto.setFechaAlta(rs.getDate("FECHAALTA"));
                
                data.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Excepcion getData Rol: " + e.getMessage());
        }
        return data;
    }

    public static Rol find(int id) {
        Rol objeto = null;
        try {
            String query = "SELECT * FROM Rol"
                    + " WHERE ID_ROL = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            List<Rol> data = getData(rs);
            objeto = !data.isEmpty() ? data.get(0) : null;

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción find Rol. " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión");
            }
        }
        return objeto;
    }

    public static List<Rol> list(String filter) {
        List<Rol> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM Rol"
                    + " WHERE codigoRol LIKE '?%' OR nombre LIKE '?%'";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, filter);
            ps.setString(1, filter);
            rs = ps.executeQuery();

            data = getData(rs);

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción list Rol. " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión");
            }
        }
        return data;
    }
}
