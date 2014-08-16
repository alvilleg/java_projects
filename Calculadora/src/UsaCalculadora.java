
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aldemar
 */
public class UsaCalculadora {

    public static void main(String a[]) {
        
        double c= -2.5;
        double b= 3.0;
        while(c<b){
            if(Math.abs(c-(-2.0))<0.000000001){
                System.out.println("Indeterminado..."+c);
            }
            if(c<-2){
                System.out.println("Determinado "+c);
            }
            else if(c>-2 && c<1){
                System.out.println("Determinado "+c);
            }
            else if(c>1){
                System.out.println("Determinado "+c);
            }                     
            c+= 0.1;            
        }        
        
//        Calculadora miCalculadora = new Calculadora();
//        int opcion = Integer.parseInt(JOptionPane.showInputDialog("Digite el código de la operación deseada\n" +
//                "1. Suma" +
//                "2. Resta" +
//                "3. Multiplicación" +
//                "4. División" +
//                "5. Valor absoluto"));
//
//        int operando1;
//        int operando2;
//        switch (opcion) {
//            case 1:
//                operando1 = Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 1"));
//                operando2= Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 2"));
//
//                miCalculadora.asignarOperando1(operando1);
//                miCalculadora.asignarOperando2(operando2);
//
//                miCalculadora.sumar();
//                int valorSuma = miCalculadora.obtenerResultado();
//                JOptionPane.showMessageDialog(null,"Operación suma "+operando1+" + "+operando2+
//                        " = "+valorSuma);
//                break;
//            case 2:
//                operando1 =  Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 1"));
//                operando2= Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 2"));
//
//                miCalculadora.asignarOperando1(operando1);
//                miCalculadora.asignarOperando2(operando2);
//
//                miCalculadora.restar();
//                int valorResta = miCalculadora.obtenerResultado();
//                JOptionPane.showMessageDialog(null,"Operación Resta "+operando1+" + "+operando2+
//                        " = "+valorResta);        
//                        
//              break;
//            case 3:
//                operando1 = Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 1"));
//                operando2= Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 2"));
//
//                miCalculadora.asignarOperando1(operando1);
//                miCalculadora.asignarOperando2(operando2);
//                miCalculadora.multiplicar();
//                int valorMult = miCalculadora.obtenerResultado();
//                
//                JOptionPane.showMessageDialog(null,"Operación Multiplicación "+operando1+" + "+operando2+
//                        " = "+valorMult);        
//                break;
//            case 4:
//                operando1 = Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 1"));
//                operando2= Integer.parseInt(JOptionPane.showInputDialog("Digite el operando 2"));
//
//                miCalculadora.asignarOperando1(operando1);
//                miCalculadora.asignarOperando2(operando2);
//                miCalculadora.dividir();                                
//                int valorDiv = miCalculadora.obtenerResultado();
//                JOptionPane.showMessageDialog(null,"Operación División "+operando1+" + "+operando2+
//                        " = "+valorDiv);        
//                break;
//            case 5:
//                double operando1_ = Double.parseDouble(JOptionPane.showInputDialog("Digite el operando 1"));
//                
//                double valorAbsoluto = miCalculadora.valorAbsoluto(operando1_);
//                
//                JOptionPane.showMessageDialog(null,"Operación Valor absoluto "+operando1_+" = "+valorAbsoluto);        
//                break;
//            default:
//                JOptionPane.showMessageDialog(null,"Error Operación no soportada");
//                break;
//        }        
    }
}
