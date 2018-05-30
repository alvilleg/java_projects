package search.countdown;

import search.Explorable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alde on 5/29/18.
 */
public class CountDownNode implements Explorable <CountDownValue>{

    long operand1;
    long operand2;
    char operator;
    long current_result;
    long target ;
    List<Long> missing;
    CountDownNode parent;

    public CountDownNode(long currentResult_, long target_, List<Long> missing_){
        this.current_result = currentResult_;
        this.target = target_;
        this.missing = missing_;
    }

    public CountDownNode(long currentResult_, long target_, List<Long> missing_, long operand1_, long operand2_, char operator_, CountDownNode parent_){
        this.current_result = currentResult_;
        this.target = target_;
        this.missing = missing_;
        this.operand1 = operand1_;
        this.operand2 = operand2_;
        this.operator = operator_;
        this.parent = parent_;
    }

    @Override public List<Explorable<CountDownValue>> expand() {

        List<Explorable<CountDownValue>> childs = new LinkedList<Explorable<CountDownValue>>();
        if(missing.size() ==1){
            return childs;
        }
        // Consider all the values as nodes as well, not expandible
        for(int i=0;i<missing.size();i++) {
            childs.add(new CountDownNode(missing.get(i), target, new ArrayList<Long>()));
        }
        for(int i=0;i<missing.size();i++) {
            for(int j=0;j<missing.size();j++) {
                if(i != j){
                    addSumNodes(childs,i, j);
                    addDivNodes(childs, i, j);
                    addDivNodes(childs, j, i);
                    addSubsNodes(childs, i, j);
                    addMultNodes(childs, i, j);
                }
            }
        }
        return childs;
    }

    @Override
    public boolean isBetter(Explorable<CountDownValue> other) {
        long currentDiff = Math.abs(current_result-target);
        long otherDiff =  Math.abs(other.getValue().getValue() - target);

        return currentDiff < otherDiff;
    }

    @Override
    public CountDownValue getValue(){
        return new CountDownValue(current_result);
    }

    private void addSubsNodes(List<Explorable<CountDownValue>> childs, int i, int j) {
        List<Long> newMissing = new ArrayList<Long>();
        for (int ii = 0; ii < missing.size(); ii++) {
            if (ii != i && ii != j) {
                newMissing.add(missing.get(ii));
            }
        }
        if (missing.get(j) <= missing.get(i)) {
            long newValue = missing.get(i) - missing.get(j);
            newMissing.add(newValue);
            childs.add(new CountDownNode(newValue, target, newMissing, missing.get(i), missing.get(j),'-', this));
        }
    }

    private void addDivNodes(List<Explorable<CountDownValue>> childs, int i, int j) {
        List<Long> newMissing = new ArrayList<Long>();
        for (int ii = 0; ii < missing.size(); ii++) {
            if (ii != i && ii != j) {
                newMissing.add(missing.get(ii));
            }
        }
        if (missing.get(j) != 0 && (missing.get(i) % missing.get(j)) == 0) {
            long newValue = missing.get(i) / missing.get(j);
            newMissing.add(newValue);
            childs.add(new CountDownNode(newValue, target, newMissing, missing.get(i), missing.get(j),'/', this));
        }
    }

    private void addMultNodes(List<Explorable<CountDownValue>> childs, int i, int j) {
        List<Long> newMissing = new ArrayList<Long>();
        for(int ii=0;ii<missing.size();ii++){
            if(ii != i && ii!=j){
                newMissing.add(missing.get(ii));
            }
        }
        long newValue = missing.get(i) * missing.get(j);
        newMissing.add(newValue);
        childs.add(new CountDownNode(newValue, target, newMissing, missing.get(i), missing.get(j),'*', this));
    }

    private void addSumNodes(List<Explorable<CountDownValue>> childs, int i, int j) {
        List<Long> newMissing = new ArrayList<Long>();
        for(int ii=0;ii<missing.size();ii++){
            if(ii != i && ii!=j){
                newMissing.add(missing.get(ii));
            }
        }
        long newValue = missing.get(i) + missing.get(j);
        newMissing.add(newValue);
        childs.add(new CountDownNode(newValue, target, newMissing, missing.get(i), missing.get(j),'+', this));
    }

    @Override public boolean isFinal() {
        return current_result == target;
    }

    @Override public String print() {
        StringBuilder sb = new StringBuilder();
        if(parent != null){
            sb.append(parent.print());
        }
        if(parent != null) {
            sb.append(String.format("%s %s %s = %s\n", operand1, operator, operand2, current_result));
        } else {
            sb.append(String.format("%s\n", current_result));
        }
        return sb.toString();
    }

    @Override public boolean isValid() {
        return true;
    }

    @Override public String getKey() {
        String key = current_result+":";
        for(Long l:missing){
            key+=":"+l;
        }
        return key;
    }
}
