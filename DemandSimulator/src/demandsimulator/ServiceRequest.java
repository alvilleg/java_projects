/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demandsimulator;

import demandsimulator.ProductionPlant.Product;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Aldemar
 * @version $Id$
 */
public class ServiceRequest {

    private CityDemand cityDemand;
    private Date initialDate;
    private Date finalDate;
    private double demand;
    private int modQuantity;
    private int sequence;
    private ProductionPlant.Product product;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public ServiceRequest() {
    }

    public CityDemand getCityDemand() {
        return cityDemand;
    }

    public void setCityDemand(CityDemand cityDemand) {
        this.cityDemand = cityDemand;
    }

    public double getDemand() {
        return demand;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public int getModQuantity() {
        return modQuantity;
    }

    public void setModQuantity(int modQuantity) {
        this.modQuantity = modQuantity;
    }

    public String print() {
        String externId = toQuotedString(cityDemand.getCityName().replaceAll(" ", "").toUpperCase() + sequence);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateHourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strInitialPckDate = toQuotedString(dateHourFormat.format(product.getProductionDate()));;
        String strInitialDate = toQuotedString(dateHourFormat.format(initialDate));
        String strFinalDate  = toQuotedString(dateHourFormat.format(finalDate));
        
        String siteId = toQuotedString(cityDemand.getExternId());
        String strAttentionTimeDel = toQuotedString("10");
        String strAttentionTimePck = toQuotedString("75");
        String res = "<perseus:ServiceRequest serviceRequestExternId=" + externId +
                " description= " + externId +
                " documentType=\"NIT\" customerId=\"830.032.160-9\">" +
                " <perseus:Order orderExternId=" + externId + "  " +
                " code=" + externId + " originCityCode=\"05001\" priority=\"-1\">" +
                " <perseus:OrderDetail orderDetailExternId=" + externId + "  amount=\"" + modQuantity + "\" " +
                " productId=\"MAT01\" weight=\"" + demand + "\" volume=\"" + demand + "\"></perseus:OrderDetail>" +
                " <perseus:OrderPickup " +
                " orderPickupExternId=" + externId + "  siteId=\"COMPRESORA_BARBOSA\" earliestPickup=" + strInitialPckDate + " latestPickup=" + strFinalDate + " estimatedAttentionTime=" + strAttentionTimePck + "></perseus:OrderPickup>" +
                " <perseus:OrderDelivery orderDeliveryExternId=" + externId + "  siteId=" + siteId +
                " earliestDelivery=" + strInitialDate + " latestDelivery=" + strFinalDate + " estimatedAttentionTime=" + strAttentionTimeDel + "></perseus:OrderDelivery>" +
                " </perseus:Order>" +
                " </perseus:ServiceRequest>";

        return res;

    }

    public String toQuotedString(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("\'", "&apos;");

        return "\"" + str + "\"";
    }

    public static void main(String a[]) {
        ServiceRequest serviceRequest = new ServiceRequest();
        CityDemand cityDemand = new CityDemand();
        cityDemand.setCityName("Yarumal");
        cityDemand.setExternId("YARUMAL");
        cityDemand.setDiaryDemand(2000.0);
        cityDemand.setCurrentLevel(100.0);

        serviceRequest.setCityDemand(cityDemand);
        serviceRequest.setInitialDate(new Date());
        serviceRequest.setFinalDate(new Date());
        serviceRequest.setDemand(3000);
        serviceRequest.setModQuantity(2);
        serviceRequest.setSequence(1);

        System.out.println(serviceRequest.print());

        System.exit(0);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
