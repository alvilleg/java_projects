/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aldemar
 */
public class PanelMinas extends JPanel implements MouseListener, ActionListener {
    JTextField textField;
    BotonMina botonMinas[][];
    int N = 16;
    boolean juegoTerminado = false;
    JButton nuevoJuego = new JButton("Nuevo Juego");
    JLabel findMinesCounterLabel;
    int findMinesCounter;
    int minasNoEncontradas;
    int minasAcertadas;
    int fallidos;
    int exploradas;
    boolean hayGanador;

    public PanelMinas() {
        nuevoJuego.addActionListener(this);
        init();
    }

    public void init() {
        removeAll();
        textField = new JTextField(10);
        textField.addActionListener(this);
        
        minasNoEncontradas = 0;
        minasAcertadas = 0;
        fallidos = 0;
        findMinesCounter = 0;
        exploradas = 0;
        hayGanador = false;
        juegoTerminado = false;
        findMinesCounterLabel = new JLabel("0");

        JPanel panelAux = new JPanel();
        JPanel panelAux2 = new JPanel();
        /// para las minas
        panelAux.setPreferredSize(new Dimension(700, 600));
        GridLayout grid = new GridLayout(N, N);
        panelAux.setLayout(grid);

        /// para el boton de nuevo juego
        panelAux2.setLayout(new FlowLayout());
        panelAux2.setPreferredSize(new Dimension(700, 100));
        botonMinas = new BotonMina[N][N];

        FlowLayout flowLayout = new FlowLayout();
        setLayout(flowLayout);
        panelAux2.add(nuevoJuego);
        panelAux2.add(textField);
        panelAux2.add(findMinesCounterLabel);

        setPreferredSize(new Dimension(700, 700));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                botonMinas[i][j] = new BotonMina();
                botonMinas[i][j].asignarTieneBandera(false);
                botonMinas[i][j].tieneMina(false);
                botonMinas[i][j].asignarMinasAlrededor(0);
                botonMinas[i][j].asignarYaFueExplorado(false);
                botonMinas[i][j].asignarFila(i);
                botonMinas[i][j].asignarColumna(j);
                panelAux.add(botonMinas[i][j]);
                botonMinas[i][j].addMouseListener(this);
            }
        }
        /// Se minan los botones
        {
            int cuantosMinados = 0;
            while (cuantosMinados < 40) {
                int columnaAleatoria = (int) (Math.random() * N);
                int filaAleatoria = (int) (Math.random() * N);
                if (!botonMinas[filaAleatoria][columnaAleatoria].tieneMina()) {
                    botonMinas[filaAleatoria][columnaAleatoria].tieneMina(true);
                    cuantosMinados++;
                }
            }
            System.out.println("cuantosMinados " + cuantosMinados);
        }
        {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = (i - 1); k <= (i + 1); k++) {
                        if (k >= 0 && k < N) {
                            for (int h = (j - 1); h <= (j + 1); h++) {
                                if (h >= 0 && h < N) {
                                    if (botonMinas[k][h].tieneMina()) {
                                        botonMinas[i][j].asignarMinasAlrededor(botonMinas[i][j].obtenerMinasAlrededor() + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        add(panelAux2);
        add(panelAux);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevoJuego) {
            System.out.println();
            init();
            updateUI();
        }
        if(e.getSource() == textField){
            JOptionPane.showMessageDialog(null, "Si ve!!!");
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    /// Se ejecuta cuando se suelta el botón del mouse
    public void mouseReleased(MouseEvent e) {
        if (juegoTerminado) {
            /// No hace nada            
            return;
        }

        BotonMina b = ((BotonMina) e.getSource());        
        if (e.getButton() == MouseEvent.BUTTON3) {
            b.asignarTieneBandera(!b.tieneBandera());
            if (b.tieneBandera()) {
                ImageIcon icon = new ImageIcon("bandera.png");
                b.setIcon(icon);
                findMinesCounter++;
                findMinesCounterLabel.setText(findMinesCounter + "");
            } else {
                b.setIcon(null);
                findMinesCounter--;
                findMinesCounterLabel.setText(findMinesCounter + "");
            }
            return;
        }

        if (b.yaFueExplorado()) {
            return;
        }
        if (b.tieneBandera()) {
            return;
        }
        b.asignarYaFueExplorado(true);
        if (((BotonMina) e.getSource()).tieneMina()) {
            /// JOptionPane.showMessageDialog(null, "Perdiste ja ja!!!!!!!!!");
            juegoTerminado = true;
            ImageIcon icon = new ImageIcon("mina.gif");                
            b.setIcon(icon);
            /// explotan todas las minas
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (botonMinas[i][j].tieneMina() && !botonMinas[i][j].tieneBandera()) {
                        botonMinas[i][j].setIcon(icon);
                        minasNoEncontradas++;
                    } else if (botonMinas[i][j].tieneMina() && botonMinas[i][j].tieneBandera()) {
                        minasAcertadas++;
                    } else if (!botonMinas[i][j].tieneMina() && botonMinas[i][j].tieneBandera()) {
                        b.setIcon(null);
                        botonMinas[i][j].setText("F");
                        fallidos++;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, imprimir());
        } else if (b.obtenerMinasAlrededor() == 0) {            
            b.setBackground(Color.CYAN);
            for (int i = b.obtenerFila() - 1; i <= b.obtenerFila() + 1; i++) {
                if (i >= 0 && i < N) {
                    for (int j = b.obtenerColumna() - 1; j <= b.obtenerColumna() + 1; j++) {
                        if (j >= 0 && j < N) {
                            // se genera el evento
                            MouseEvent me = new MouseEvent(botonMinas[i][j], 1, (new Date()).getTime(), e.getModifiers(), 0, 0, 1, false, e.getButton());
                            mouseReleased(me);
                        }
                    }
                }
            }
        } else {
            b.setBackground(Color.BLUE);
            b.setText(b.obtenerMinasAlrededor() + "");
        }
        b.setEnabled(false);
        exploradas++;
        if (exploradas == ((N * N) - 40)) {
            hayGanador = true;
            juegoTerminado = true;
            JOptionPane.showMessageDialog(this, "Felicitaciones !!!!\nGanador ... ");
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public String imprimir() {
        String str = "Resultados \n" +
                "Minas Encontradas \t" + minasAcertadas + "\n" +
                "Banderas Fallidas \t" + fallidos + "\n" +
                "Minas no Encontradas \t" + minasNoEncontradas;
        return str;
    }
}
