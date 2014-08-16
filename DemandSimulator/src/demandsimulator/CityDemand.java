/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demandsimulator;

import java.util.Date;

/**
 *
 * @author Aldemar
 * @version $Id$
 */
public class CityDemand {
    private String cityName;
    private double diaryDemand;
    private double currentLevel;
    private String externId;
    private int suppliedDays;
    private double daysByMod;
    private double consumedM3;
    private int maxDurationDays ;
    private int sendedModules;
    private Date finalSuppliedDate;
    private Date initialSuppliedDate;

    private double MODULE_CAPACITY = 1500.0 * .95;
    
    public CityDemand() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getDiaryDemand() {        
        return diaryDemand;
    }

    public void setDiaryDemand(double diaryDemand) {
        this.diaryDemand = diaryDemand;
    }

    public double getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(double currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getExternId() {
        return externId;
    }

    public void setExternId(String externId) {
        this.externId = externId;
    }

    public void incrementSuppliedDays(){
        suppliedDays++;
    }

    public double getDaysByMod() {
        return daysByMod;
    }

    public int getSuppliedDays() {
        return suppliedDays;
    }

    public void setSuppliedDays(int suppliedDays) {
        this.suppliedDays = suppliedDays;
    }

    public void calcDaysByModule(double modCapacity){
        daysByMod = (modCapacity / this.getDiaryDemand());
    }
    public void calcMaxDurationDays(double modCapacity){
        maxDurationDays = (int) (this.getCurrentLevel() / this.getDiaryDemand());
        
    }

    public double getConsumedM3() {
        return consumedM3;
    }

    public void setConsumedM3(double consumedM3) {
        this.consumedM3 = consumedM3;
    }

    public int getMaxDurationDays() {
        return maxDurationDays;
    }

    public void setMaxDurationDays(int maxDurationDays) {
        this.maxDurationDays = maxDurationDays;
    }
    
    public void consumeOneMinute(int totalDayMinutes){
        double consumption = ((this.getDiaryDemand()*1.5)/totalDayMinutes);
        if(currentLevel <= 0){
            return ;
        }
        if(currentLevel > consumption){
            this.consumedM3 = this.consumedM3 + ((this.getDiaryDemand()*1.5)/totalDayMinutes);
            this.currentLevel = this.currentLevel - consumption;
        }
        else{
            this.consumedM3 = this.consumedM3 + this.currentLevel;
            this.currentLevel = 0;
        }
    }

    public int getSendedModules() {
        return sendedModules;
    }

    public void setSendedModules(int sendedModules) {
        if(sendedModules > 0){
            this.currentLevel += MODULE_CAPACITY;
        }
        this.sendedModules = sendedModules;
        int newSppliedDays =  (int)(this.getSendedModules()*this.getDaysByMod());
        
        if(this.suppliedDays < newSppliedDays){
            this.suppliedDays = newSppliedDays;
            this.finalSuppliedDate.setTime(this.finalSuppliedDate.getTime() + ((suppliedDays-newSppliedDays)*86400000));
        }        
    }

    public Date getFinalSuppliedDate() {
        return finalSuppliedDate;
    }

    public void setFinalSuppliedDate(Date finalSuppliedDate) {
        this.finalSuppliedDate = finalSuppliedDate;
    }

    public Date getInitialSuppliedDate() {
        return initialSuppliedDate;
    }

    public void setInitialSuppliedDate(Date initialSuppliedDate) {
        this.initialSuppliedDate = initialSuppliedDate;
    }

    public Date getCurrentFinalDate(Date date){
        long time1 = date.getTime();
        
        double durationDays =(currentLevel/diaryDemand);
        time1 += (int)(durationDays*86400000);
        Date d = new Date();
        d.setTime(time1);
        return d;
    }
    
    public String print(){
        String str = "<perseus:Site isZone=\"false\" name=\""+cityName+"\" siteExternId=\""+externId+"\"" +
                     " address=\""+cityName+"\" country=\"Colombia\" cityCode=\"05001\" city=\"Cali\" state=\"Antioquia\"/>";
        
        return str;
    }
    
}
