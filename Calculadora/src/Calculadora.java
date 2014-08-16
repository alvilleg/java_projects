/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aldemar
 */
public class Calculadora {
    /// Atributos de la clase
    private int operando1;
    private int operando2;
    private int resultado;
    
    /// Métodos
    public void asignarOperando1(int nOper1){
        operando1 = nOper1;
    }
    public void asignarOperando2(int nOper2){
        operando2 = nOper2;
    }
    public void sumar(){
        resultado= operando1+operando2;
    }
    public void restar(){
        resultado= operando1-operando2;
    }
    public void multiplicar(){
        resultado= operando1*operando2;
    }
    public void dividir(){
        resultado= operando1/operando2;
    }
    public int obtenerResultado(){
        return resultado;
    }    
    public double valorAbsoluto(double x){
        double absValue = 0.0;
        if(x > 0){
            absValue = x;
        }
        else{
            absValue = -x;
        }
        return absValue;
    }
}
