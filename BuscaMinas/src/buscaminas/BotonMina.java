/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas;

import javax.swing.JButton;

/**
 *
 * @author Aldemar
 */
public class BotonMina extends JButton{
    boolean tieneMina;
    boolean tieneBandera;
    boolean yaFueExplorado;
    int minasAlrededor;
    int fila;
    int columna;
    
    public BotonMina() {
        setText("");
    }

    public int obtenerMinasAlrededor() {
        return minasAlrededor;
    }

    public void asignarMinasAlrededor(int minasAlrededor) {
        this.minasAlrededor = minasAlrededor;
    }

    public boolean tieneBandera() {
        return tieneBandera;
    }

    public void asignarTieneBandera(boolean tieneBandera) {
        this.tieneBandera = tieneBandera;
    }

    public boolean tieneMina() {
        return tieneMina;
    }

    public void tieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }

    public boolean yaFueExplorado() {
        return yaFueExplorado;
    }

    public void asignarYaFueExplorado(boolean yaFueExplorado) {
        this.yaFueExplorado = yaFueExplorado;
    }

    public int obtenerColumna() {
        return columna;
    }

    public void asignarColumna(int columna) {
        this.columna = columna;
    }

    public int obtenerFila() {
        return fila;
    }

    public void asignarFila(int fila) {
        this.fila = fila;
    }    
}
