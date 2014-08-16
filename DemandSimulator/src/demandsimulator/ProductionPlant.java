/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demandsimulator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Aldemar
 * @version $Id$
 */
public class ProductionPlant {
    //
    int currentInventory;
    // time of production between products
    int productionTimeByProd;

    Date initialProdTime;

    int limitProductByDay;

    int maxMinutesProduction;

    public Date getInitialProdTime() {
        return initialProdTime;
    }

    public void setInitialProdTime(Date initialProdTime) {
        this.initialProdTime = initialProdTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    List<Product> products;

    public ProductionPlant() {
        products = new ArrayList<Product>();
    }
    
    public int getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(int currentInventory) {
        this.currentInventory = currentInventory;
    }

    public int getProductionTimeByProd() {
        return productionTimeByProd;
    }

    public void setProductionTimeByProd(int productionTimeByProd) {
        this.productionTimeByProd = productionTimeByProd;
    }

    public void produceOneDay(){
        int currentProduced=0;
        int currentMinutes=0;
        Calendar cal = new GregorianCalendar();
        while((currentProduced < limitProductByDay) && (currentMinutes < maxMinutesProduction)){
            // generate a new product
            cal.set(cal.MINUTE, (cal.get(cal.MINUTE))+productionTimeByProd);
            Product p = new Product();
            p.setProductionDate(cal.getTime());
            products.add(products.size(), p);
            currentMinutes+= productionTimeByProd;
            currentProduced++;
        }
        initialProdTime = cal.getTime();
    }
    public void produceOneProduct(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(initialProdTime);
        cal.set(cal.MINUTE, (cal.get(cal.MINUTE))+productionTimeByProd);
        Product p = new Product();
        p.setProductionDate(cal.getTime());
        products.add(products.size(), p);
        initialProdTime = cal.getTime();
    }

    public void initialize(){
        for(int i=0;i<currentInventory;i++){
            Product p = new Product();
            p.setProductionDate(initialProdTime);
            products.add(products.size(), p);
        }
    }
    
    public Product extractProduct(){
        if(products.size() == 0){
            System.out.println("Inventory agoted ... ");
            return null;
        }
        return products.remove(0);        
    }

    public int getLimitProductByDay() {
        return limitProductByDay;
    }

    public void setLimitProductByDay(int limitProductByDay) {
        this.limitProductByDay = limitProductByDay;
    }

    public int getMaxMinutesProduction() {
        return maxMinutesProduction;
    }

    public void setMaxMinutesProduction(int maxMinutesProduction) {
        this.maxMinutesProduction = maxMinutesProduction;
    }    


    public class Product{
        String name;
        Date productionDate;
        String productId;

        public Product() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getProductionDate() {
            return productionDate;
        }

        public void setProductionDate(Date productionDate) {
            this.productionDate = productionDate;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
        
    }
}
