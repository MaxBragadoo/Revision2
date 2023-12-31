package com.project.controlcitas.DAO;

import com.project.controlcitas.DTO.Usuario;
import com.project.controlcitas.config.Conexion;
import com.project.controlcitas.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean save(Usuario objeto) {
        try {
            String query = "INSERT INTO Usuario "
                    + "(ID_USUARIO,CODIGOUSUARIO,NOMBRE,APELLIDOP,APELLIDOM,TELEFONO,USERNAME,PASSWORD,ID_ROL,ESTATUS,FECHAALTA) VALUES"
                    + "(SEQ_Usuario.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoUsuario());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidoP());
            ps.setString(4, objeto.getApellidoM());
            ps.setString(5, objeto.getTelefono());
            ps.setString(6, objeto.getUsername());
            ps.setString(7, objeto.getPassword());
            ps.setInt(8, objeto.getRol().getIdRol());
            ps.setString(9, objeto.getEstatus());            
            ps.setString(10, Utils.SDF.format(objeto.getFechaAlta()));

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo registrar el Usuario.\n" + e.getMessage();
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

    public static boolean update(Usuario objeto) {
        try {
            String query = "UPDATE Usuario SET "
                    + " CODIGOUSUARIO=?,NOMBRE=?,APELLIDOP=?,APELLIDOM=?,CORREO=?,TELEFONO=?,DESCRIPCION=?,ESTATUS=?,FECHAALTA=?"
                    + " WHERE ID_USUARIO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, objeto.getCodigoUsuario());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidoP());
            ps.setString(4, objeto.getApellidoM());
            ps.setString(5, objeto.getTelefono());
            ps.setString(6, objeto.getUsername());
            ps.setString(7, objeto.getPassword());
            ps.setInt(8, objeto.getRol().getIdRol());
            ps.setString(9, objeto.getEstatus());            
            ps.setString(10, Utils.SDF.format(objeto.getFechaAlta()));
            ps.setInt(11, objeto.getIdUsuario());

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo actualizar el Usuario.\n" + e.getMessage();
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
            String query = "DELETE FROM Usuario"
                    + " WHERE ID_USUARIO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            Utils.MESSAGE = "No se pudo eliminar el Usuario.\n" + e.getMessage();
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

    public static List<Usuario> getData(ResultSet rs) {
        List<Usuario> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Usuario objeto = new Usuario();
                objeto.setIdUsuario(rs.getInt("ID_USUARIO"));
                objeto.setCodigoUsuario(rs.getString("CODIGOUSUARIO"));
                objeto.setNombre(rs.getString("NOMBRE"));
                objeto.setApellidoP(rs.getString("APELLIDOP"));
                objeto.setApellidoM(rs.getString("APELLIDOM"));
                objeto.setTelefono(rs.getString("TELEFONO"));
                objeto.setUsername(rs.getString("USERNAME"));
                objeto.setPassword(rs.getString("PASSWORD"));
                objeto.setRol(RolDAO.find(rs.getInt("ID_ROL")));                
                objeto.setEstatus(rs.getString("ESTATUS"));
                objeto.setFechaAlta(rs.getDate("FECHAALTA"));
                
                data.add(objeto);
            }
        } catch (SQLException e) {
            System.out.println("Excepcion getData Usuario: " + e.getMessage());
        }
        return data;
    }

    public static Usuario find(int id) {
        Usuario objeto = null;
        try {
            String query = "SELECT * FROM Usuario"
                    + " WHERE ID_USUARIO = ?";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            List<Usuario> data = getData(rs);
            objeto = !data.isEmpty() ? data.get(0) : null;

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción find Usuario. " + e.getMessage());
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

    public static List<Usuario> list(String filter) {
        List<Usuario> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM Usuario"
                    + " WHERE codigoUsuario LIKE '?%' OR username LIKE '?%'";

            connection = Conexion.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, filter);
            ps.setString(2, filter);
            rs = ps.executeQuery();

            data = getData(rs);

            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Excepción list Usuario. " + e.getMessage());
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
