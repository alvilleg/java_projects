
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aldemar
 * @version $Id$
 */
public class CalculadoraGUI extends JFrame implements ActionListener {

    JButton registrar;
    JButton comisiones;
    JButton mejorVendedor;
    JButton peorVendedor;
    JTextField producto;
    JTextField cantidad;
    JTextField valorUnitario;
    // Indica la posición del vendeor en el arreglo vendedores
    JTextField codigoVendedor;
    JLabel lProducto;
    JLabel lCantidad;
    JLabel lValorUnitario;
    JLabel lVendedor;
    String vendedores[] = {"Alejandra", "Antonio", "Daniela", "Daniel", "Jessica", "Jose", "Jesús", "Manuel", "María", "Mercedes", "Sandra", "Tatiana", "Vanessa"};
    // guarda las ventas acumuladas de los vendedores
    int ventas[] = new int[13];
    JTextArea area = new JTextArea(20, 30);
    JScrollPane scrollPane = new JScrollPane(area);

    public CalculadoraGUI() {

        registrar = new JButton("registrar");
        comisiones = new JButton("Comisiones");
        mejorVendedor = new JButton("mejor vendedor");
        peorVendedor = new JButton("peor vendedor");

        producto = new JTextField(20);
        cantidad = new JTextField(20);
        valorUnitario = new JTextField(20);
        codigoVendedor = new JTextField(20);

        lProducto = new JLabel("Producto");
        lCantidad = new JLabel("Cantidad");
        lValorUnitario = new JLabel("Valor unitario");
        lVendedor = new JLabel("Vendedor");

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(300,300));
        panel.setLayout(new GridLayout(6, 2));
        getContentPane().setLayout(new GridLayout(2, 1));
        panel.add(lProducto);
        panel.add(producto);
        
        panel.add(lCantidad);
        panel.add(cantidad);

        panel.add(lValorUnitario);
        panel.add(valorUnitario);
        
        panel.add(lVendedor);
        panel.add(codigoVendedor);
        
        registrar.addActionListener(this);
        comisiones.addActionListener(this);
        mejorVendedor.addActionListener(this);
        peorVendedor.addActionListener(this);

        panel.add(registrar);
        panel.add(comisiones);
        
        panel.add(mejorVendedor);
        panel.add(peorVendedor);
        getContentPane().add(panel);
        getContentPane().add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrar) {
            int valorUnitarioN = Integer.parseInt(valorUnitario.getText());
            int cantidadN = Integer.parseInt(cantidad.getText());
            int valorVenta = valorUnitarioN * cantidadN;
            int codigoVendedorN = Integer.parseInt(codigoVendedor.getText());

            ventas[codigoVendedorN] = ventas[codigoVendedorN] + valorVenta;
        }

        if (e.getSource() == comisiones) {
       int ventas[i] = new int ventas[13];
       for (int i=0; i<13; i++){
                        do{
                            if (ventas [i] < 100000){
                    comision = 0
                            else if (ventas[i] >=100000 && ventas[i]<= 500000){
                    comision = ventas[i]*0.02
                            else if (ventas[i] >=500001 && ventas[i]< 1000000){
                    comision = ventas[i]*0.05
                            else if (ventas[i] >=1000001 && ventas[i]< 5000000){
                    comision = ventas[i]*0.07
                            else if (ventas[i] > 5000000){
                    comision = ventas[i]* 0.12

            ventas[i] = ventas[i] + comision;

                    area.append(ventas[0]"/t"ventas[1]"/t"ventas[2]"/t"ventas[3]
                            "/t"ventas[4]"/t"ventas[5]"/t"ventas[6]"/t"ventas[7]
                            "/t"ventas[8]"/t"ventas[9]"/t"ventas[10]"/t"ventas[11]
                            "/t"ventas[12])

        }
                            }
                            }
                            }
                            }
                        }
        if (e.getSource() == mejorVendedor) {

        }
            int ventas[j] = new int ventas[13];
        int aux = 0;
for (int j=i; i< ventas[i]; j++){
    do{
    if (ventas[i]< ventas[j])
    {
        aux= ventas[i];
                ventas[i]=ventas[j];
                ventas[j]=aux;
              if ventas[i]> ventas[j]

                        Ventas[i] = Ventas[i]+ comision

                        }
                        }

                 }
    }
}
        }
        if (e.getSource() == peorVendedor){
             int aux= 0;
             for (int j=i; j>ventas[i]; j++){
                 do{
             if (ventas[i]> ventas[j])
    {
                ventas[i]=ventas[j];
                ventas[j]=aux;
                ventas[i] = Ventas [i]+ comision
              if (ventas[i]=ventas[j]){
           ventas[i] = Ventas [j]+ comision
            }
             }
                 }
             }
        }
}


    public static void main(String a[]) {
        CalculadoraGUI calculadoraGUI = new CalculadoraGUI();
        calculadoraGUI.setSize(300, 400);
        calculadoraGUI.setVisible(true);
    }
}