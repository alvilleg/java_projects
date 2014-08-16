/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demandsimulator;

import demandsimulator.ProductionPlant.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 *
 * @author Aldemar
 * @version $Id$
 */
public class DemandSimulator {

    private List<CityDemand> cityDemands;
    private Date initialDate;
    private Date finalDate;
    private int daysForPlanning;
    private List<ServiceRequest> serviceRequest;
    private double MODULE_CAPACITY = 1500.0 * .95;
    List<ServiceRequest> serviceRequests;
    List<Vehicle> vehicles;
    ProductionPlant productionPlant;
    int maxHourFunctioning;
    int vehicleNumber;
    
    public DemandSimulator() {
        cityDemands = new ArrayList<CityDemand>();
        serviceRequests = new ArrayList<ServiceRequest>();
        vehicles = new ArrayList<Vehicle>();
    }

    public List<CityDemand> getCityDemands() {
        return cityDemands;
    }

    public void setCityDemands(List<CityDemand> cityDemands) {
        this.cityDemands = cityDemands;
    }

    public int getDaysForPlanning() {
        return daysForPlanning;
    }

    public void setDaysForPlanning(int daysForPlanning) {
        this.daysForPlanning = daysForPlanning;
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

    public void simulate() {
        // current time in minutes        
        Calendar currentTimeCal = new GregorianCalendar();
        currentTimeCal.setTime(initialDate);
        int sequence = 0;

        for (int i = 0;; i++) {
            boolean allCitiesSupplied = true;
            // produce one day           
            Product p = null;
            for (int j = 0; j < (maxHourFunctioning * 60); j++) {
                if ((j % productionPlant.getProductionTimeByProd()) == 0) {
                    productionPlant.produceOneProduct();
                }
                currentTimeCal.set(currentTimeCal.MINUTE, currentTimeCal.get(currentTimeCal.MINUTE) + 1);
                
                for (CityDemand cd : cityDemands) {
                    // if not all days are supplied
                    Calendar currentCdCalendar = new GregorianCalendar();
                    currentCdCalendar.setTimeInMillis(currentTimeCal.getTimeInMillis());                    

                    if (cd.getSuppliedDays() < getDaysForPlanning()) {
                        allCitiesSupplied = false;
                        //increment one minute                                                
                        
                        if ((productionPlant.getProducts().size() == 0)||(productionPlant.getProducts().get(0).getProductionDate().getTime() > currentCdCalendar.getTimeInMillis())) {
                            continue;
                        }
                        
                        cd.consumeOneMinute(maxHourFunctioning * 60);
                        while (cd.getConsumedM3() >= MODULE_CAPACITY) {
                            // extract a module from the plant                            
                            p = productionPlant.extractProduct();
                            if (p == null) {
                                //FIXME:
                                System.out.println(cd.getCityName() + " DON't Inventory  suppliedDays ");
                            } else {
                                
                                if (cd.getCurrentFinalDate(currentCdCalendar.getTime()).getTime() < currentCdCalendar.getTimeInMillis() ) {
                                    System.out.println("No se alcanza a entregar ");
                                    Date d = new Date();
                                    d.setTime(currentCdCalendar.getTimeInMillis());
                                    cd.setFinalSuppliedDate(d);
                                }                                 
                                if (p.getProductionDate().getTime() <= currentCdCalendar.getTimeInMillis()) {
                                    
//                                    System.out.println("Create service request I et"+currentCdCalendar.getTime()+" lt"+cd.getCurrentFinalDate(currentCdCalendar.getTime())+" cd.getConsumedM3() "+cd.getConsumedM3());
                                    Calendar finalTimeCal = new GregorianCalendar();
                                    finalTimeCal.setTimeInMillis(cd.getCurrentFinalDate(currentCdCalendar.getTime()).getTime());
                                    createServiceRequest(currentCdCalendar, 0, cd, finalTimeCal, sequence++, p);
                                    cd.setSendedModules(cd.getSendedModules() + 1);
                                }
                            }
                            // se disminuye el consumo                            
                            cd.setConsumedM3(cd.getConsumedM3() - MODULE_CAPACITY);
                        }
                        //System.out.println(cd.getCityName()+" Current level "+cd.getCurrentLevel());
                    }
                }//
            }
            //increment one day, is the next day to 06:00 AM            
            currentTimeCal.set(currentTimeCal.HOUR, 0);
            currentTimeCal.set(currentTimeCal.MINUTE, 0);
            currentTimeCal.set(currentTimeCal.SECOND, 0);

            currentTimeCal.set(currentTimeCal.DAY_OF_YEAR, (currentTimeCal.get(currentTimeCal.DAY_OF_YEAR) + 1));
            currentTimeCal.set(currentTimeCal.HOUR, 6);
            currentTimeCal.set(currentTimeCal.MINUTE, 0);
            currentTimeCal.set(currentTimeCal.SECOND, 0);

//            System.out.println("After  "+currentTimeCal.getTime());
            if (allCitiesSupplied) {
                System.out.println("allCitiesSupplied ");
                for (CityDemand cd : cityDemands) {
                    System.out.println(cd.getCityName() + " supplied days " + cd.getSuppliedDays() + " modules " + cd.getSendedModules() + "Initial date" + cd.getInitialSuppliedDate() + " final date " + cd.getFinalSuppliedDate());
                }
                break;
            }
        }
    }

    public void createServiceRequest(Calendar currentTimeCal, int numDays, CityDemand cd, Calendar finalTimeCal, int sequence, Product p) {
        currentTimeCal.set(currentTimeCal.DAY_OF_MONTH, currentTimeCal.get(currentTimeCal.DAY_OF_MONTH) + numDays);

        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setCityDemand(cd);
        serviceRequest.setInitialDate(currentTimeCal.getTime());
        serviceRequest.setFinalDate(finalTimeCal.getTime());
        serviceRequest.setDemand(MODULE_CAPACITY);
        serviceRequest.setModQuantity(1);
        serviceRequest.setSequence(sequence);
        serviceRequest.setProduct(p);

        serviceRequests.add(serviceRequest);

    }

    public String generateXML() {
        StringBuilder sb = new StringBuilder();
        sb.append(getXMLHeader());

        sb.append("<perseus:CustomerData>");
        sb.append("<perseus:Sites>");

        //sites
        for (CityDemand cd : cityDemands) {
            sb.append(cd.print() + "\n");
        }

        sb.append("<perseus:Customers><perseus:Customer documentId=\"830.032.160-9\" documentType=\"NIT\" name=\"EPM Gas\"/></perseus:Customers>\n");

        sb.append("</perseus:Sites>");
        sb.append("</perseus:CustomerData>");


        String products = "<perseus:ProductData>" +
                "        <perseus:ProductTypes>" +
                "            <perseus:ProductType productTypeExternId=\"CONT01\" name=\"TIPO CONTENEDOR GAS\"></perseus:ProductType>" +
                "        </perseus:ProductTypes>" +
                "        <perseus:Products>" +
                "            <perseus:Product productExternId=\"MAT01\" name=\"MODULO MAT DE GAS\" productTypeCode=\"CONT01\"></perseus:Product>" +
                "        </perseus:Products>" +
                "        <perseus:IncompatibilityConstraints>" +
                "        </perseus:IncompatibilityConstraints>" +
                "    </perseus:ProductData>";

        // vehículos
        sb.append(products);
        sb.append("<perseus:VehicleData>\n");
        sb.append("<perseus:VehicleTypes>\n");
        sb.append("    <perseus:VehicleType vehicleTypeExternId=\"ST4\" maxWeigthCapacity=\"3000\" maxVolumeCapacity=\"3000\" wareHouseHeight=\"1\" " +
                "wareHouseWide=\"1\" wareHouseDepth=\"1\" axesNumber=\"1\" costByKm=\"1\" hasBackDoor=\"N\" hasLateralLeftDoor=\"N\" " +
                "hasLateralRightDoor=\"N\" description=\"TRAILER ST-4\"></perseus:VehicleType>\n");
        sb.append("</perseus:VehicleTypes>\n");
        sb.append("<perseus:Vehicles>\n");

        for (Vehicle v : vehicles) {
            sb.append("    <perseus:Vehicle plate=" + toQuotedString(v.getPlate()) + " vehicleTypeId=" + toQuotedString(v.getVehicleType()) + "></perseus:Vehicle>\n");
        }

        sb.append("</perseus:Vehicles>\n");
        sb.append("<perseus:AvailabilityVehicles>\n");

        // para cada día de disponibilidad de los vehículos
        int counter = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Vehicle v : vehicles) {
            Calendar currentDate = new GregorianCalendar();
            currentDate.setTime(initialDate);
            System.out.println("initialDate = "+currentDate.getTime()+" finalDate " + finalDate);
            while (currentDate.getTimeInMillis() < (finalDate.getTime()+((86400000*7)))) {
                

                String avDate = toQuotedString(dateFormat.format(currentDate.getTime()) + " 00:00");
                String avName = currentDate.getDisplayName(currentDate.DAY_OF_WEEK, currentDate.LONG, Locale.getDefault()).toUpperCase();

                avName = toQuotedString(avName + "_" + counter);
                sb.append("<perseus:AvailabilityVehicle externalId=" + avName + " vehicleId=" + toQuotedString(v.getPlate()) +
                        " availabilityDate=" + avDate + " initialSiteId=\"COMPRESORA_BARBOSA\" finalSiteId=\"COMPRESORA_BARBOSA\"  initialHour=\"08:00\" finalHour=\"18:00\"/>\n");
                currentDate.set(currentDate.DAY_OF_MONTH, currentDate.get(currentDate.DAY_OF_MONTH) + 1);
                counter++;
            }
        }
        sb.append("</perseus:AvailabilityVehicles>\n");
        sb.append("</perseus:VehicleData>\n");

        sb.append("<perseus:ServiceRequests>\n");
        for (ServiceRequest serviceRequest : serviceRequests) {
            sb.append(serviceRequest.print() + "\n");
        }
        sb.append("</perseus:ServiceRequests>\n");

        return sb.substring(0) + getXMLEnd();
    }

    public String getXMLHeader() {
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n" +
                "<!--" + "\n" +
                "Document   : ImportValidate2.xml" + "\n" +
                "Created on : 2 de mayo de 2008, 07:15 PM" + "\n" +
                "Author     : Aldemar" + "\n" +
                "Description: Purpose of the document follows." + "\n" +
                "-->" + "\n" +
                "<perseus:ImportData  xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'" + "\n" +
                "xmlns:perseus='http://xml.strategicdecision.net/schema/perseus/import'" + "\n" +
                "xsi:schemaLocation='http://xml.strategicdecision.net/schema/perseus/import ImportValidate.xsd'>" +
                "";
        return header;
    }

    public String getXMLEnd() {
        return "</perseus:ImportData>";
    }

    public String toQuotedString(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("\'", "&apos;");
        str = str.replaceAll("Á", "A");
        str = str.replaceAll("É", "E");
        str = str.replaceAll("Í", "I");
        str = str.replaceAll("Ó", "O");
        str = str.replaceAll("Ú", "U");

        return "\"" + str + "\"";
    }

    public void createVehicles() {
        for (int i = 0; i < vehicleNumber; i++) {
            Vehicle v = new Vehicle();
            v.setPlate("VZI-04" + i);
            v.setVehicleType("ST4");

            vehicles.add(v);
        }
    }

    public void createSites(int scenarioId) {

        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File("F:\\Perseus\\trunk\\PerseusTms\\testdata\\EPM\\sitios.txt")));
            String currentLine = bf.readLine();
            while (currentLine != null) {
                StringTokenizer st = new StringTokenizer(currentLine, "\t");
                int count = 0;
                String currentSite = "";
                double currentDemand = 0.0, currentLevel = 0.0;
                while (st.hasMoreTokens()) {
                    if (count == scenarioId) {
                        currentDemand = Double.parseDouble(st.nextToken().trim());

                        CityDemand cd = new CityDemand();
                        cd.setCityName(currentSite);
                        cd.setDiaryDemand(currentDemand);
                        cd.setCurrentLevel(currentLevel);
                        cd.setConsumedM3(0.0);
                        Calendar cal = new GregorianCalendar();
                        cal.setTime(initialDate);

                        Calendar cal2 = new GregorianCalendar();
                        cal2.setTime(initialDate);

                        cd.setInitialSuppliedDate(cal.getTime());
                        cd.setFinalSuppliedDate(cal2.getTime());
                        cd.calcDaysByModule(MODULE_CAPACITY);
                        cd.calcMaxDurationDays(MODULE_CAPACITY);

                        int initialSendedModules = (int) (cd.getCurrentLevel() / MODULE_CAPACITY);
                        cd.setSendedModules(initialSendedModules);
                        cd.setExternId(currentSite.replaceAll(" ", "").toUpperCase());

                        cityDemands.add(cd);
                    } else if (count == 0) {
                        currentSite = st.nextToken();
                    } else if (count == 1) {
                        currentLevel = Double.parseDouble(st.nextToken());
                    } else {
                        st.nextToken();
                    }
                    count++;
                }
                currentLine = bf.readLine();                
            }

            CityDemand cd = new CityDemand();
            cd.setExternId("COMPRESORA_BARBOSA");
            cd.setSuppliedDays(getDaysForPlanning());
            cd.setCityName("Compresora Barbosa");

            cityDemands.add(cd);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public ProductionPlant getProductionPlant() {
        return productionPlant;
    }

    public void setProductionPlant(ProductionPlant productionPlant) {
        this.productionPlant = productionPlant;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getMaxHourFunctioning() {
        return maxHourFunctioning;
    }

    public void setMaxHourFunctioning(int maxHourFunctioning) {
        this.maxHourFunctioning = maxHourFunctioning;
    }

    

    public static void main(String a[]) {
        DemandSimulator ds = new DemandSimulator();

        // assing the dates
        Calendar initialPlanningDate = new GregorianCalendar();
        initialPlanningDate.set(initialPlanningDate.YEAR, 2009);
        initialPlanningDate.set(initialPlanningDate.MONTH, 2);
        initialPlanningDate.set(initialPlanningDate.DAY_OF_MONTH, 1);
        initialPlanningDate.set(initialPlanningDate.HOUR_OF_DAY, 6);
        initialPlanningDate.set(initialPlanningDate.MINUTE, 0);

        ds.setInitialDate(initialPlanningDate.getTime());
        Calendar cal = new GregorianCalendar();
        cal.setTime(initialPlanningDate.getTime());
        int daysForPlanning = 14;
        cal.set(cal.DAY_OF_MONTH, (cal.get(cal.DAY_OF_MONTH) + daysForPlanning));
        ds.setFinalDate(cal.getTime());
        ds.setDaysForPlanning(daysForPlanning);
        ds.createSites(6);
        ds.setVehicleNumber(2);
        ds.createVehicles();
        ds.setMaxHourFunctioning(16);

        ProductionPlant productionPlant = new ProductionPlant();
        int currentInventory = ds.getVehicleNumber()*2 +2;
        productionPlant.setCurrentInventory(currentInventory);
        productionPlant.setInitialProdTime(initialPlanningDate.getTime());

        productionPlant.setLimitProductByDay(20);
        productionPlant.setMaxMinutesProduction(1000);
        productionPlant.setProductionTimeByProd(75);
        productionPlant.initialize();
        ds.setProductionPlant(productionPlant);

        ds.simulate();

        System.out.println(ds.generateXML());

        System.exit(0);
    }
}
