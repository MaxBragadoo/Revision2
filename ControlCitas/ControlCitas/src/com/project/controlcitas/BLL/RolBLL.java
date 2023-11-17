
package com.project.controlcitas.BLL;

import com.project.controlcitas.DAO.RolDAO;
import com.project.controlcitas.DTO.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RolBLL {
    public static List<Rol> list(String filter) {
        return RolDAO.list(filter);
    }

    public static Rol find(int id) {
        return RolDAO.find(id);
    }

    public static boolean add(Rol objeto) {
        return RolDAO.save(objeto);
    }

    public static boolean edit(Rol objeto) {
        return RolDAO.update(objeto);
    }

    public static boolean delete(int id) {
        return RolDAO.delete(id);
    }

    public static DefaultTableModel createTable(DefaultTableModel tabla, ArrayList<Rol> data) {
        tabla = clearTable(tabla);
        for (Rol item : data) {
            tabla.addRow(item.toObject());
        }
        return tabla;
    }

    public static DefaultTableModel clearTable(DefaultTableModel tabla) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.removeRow(i);
            i--;
        }
        return tabla;
    }
}
