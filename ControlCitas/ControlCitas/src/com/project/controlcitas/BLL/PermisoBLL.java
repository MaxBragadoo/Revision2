
package com.project.controlcitas.BLL;

import com.project.controlcitas.DAO.PermisoDAO;
import com.project.controlcitas.DTO.Permiso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PermisoBLL {
    public static List<Permiso> list(String filter) {
        return PermisoDAO.list(filter);
    }

    public static Permiso find(int id) {
        return PermisoDAO.find(id);
    }

    public static boolean add(Permiso objeto) {
        return PermisoDAO.save(objeto);
    }

    public static boolean edit(Permiso objeto) {
        return PermisoDAO.update(objeto);
    }

    public static boolean delete(int id) {
        return PermisoDAO.delete(id);
    }

    public static DefaultTableModel createTable(DefaultTableModel tabla, ArrayList<Permiso> data) {
        tabla = clearTable(tabla);
        for (Permiso item : data) {
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
