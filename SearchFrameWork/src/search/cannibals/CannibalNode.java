package search.cannibals;

import search.Explorable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alde on 5/28/18.
 */
public class CannibalNode implements Explorable {

    private int persons1 = 0;
    private int cannibals1 = 0;
    private int persons2 = 0;
    private int cannibals2 = 0;
    private int total = 0;
    private int side = 0;
    //
    CannibalNode parent = null;

    CannibalNode(int persons_, int cannibals_, CannibalNode cannibalNode, int total_, int side_) {
        this.total = total_;
        this.persons1 = persons_;
        this.cannibals1 = cannibals_;
        this.persons2 = total - persons_;
        this.cannibals2 = total - cannibals_;
        this.parent = cannibalNode;
        this.side= side_;
    }

    @Override public List<Explorable> expand() {

        System.err.println("************ Expand: " + getKey());
        List<Explorable> childs = new LinkedList<Explorable>();
        //
        System.err.println("Side: "+ side);
        if(side == 0){
            buildCannibalNodesSourceSide(childs);
            System.err.println("source " + childs);
        } else {
            buildCannibalNodesTargetSide(childs);
            System.err.println("target " + childs);
        }
        //
        return childs;
    }

    private void buildCannibalNodesTargetSide(List<Explorable> childs) {

        // Returns a person
        if(persons2 >= 1 ) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1 + 1, cannibals1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }
        // Returns a cannibal
        if(cannibals2>=1) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1, cannibals1 + 1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }
        // Returns one cannibal, one person
        if(persons2>=1 && cannibals2>=1) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1 + 1, cannibals1 + 1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }
        // Returns two cannibals
        if(cannibals2>=2) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1, cannibals1 + 2, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }
        // Returns two persons
        if(persons2>=2) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1 + 2, cannibals1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }
    }

    private void buildCannibalNodesSourceSide(List<Explorable> childs) {

        if (persons1 >= 1) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1 - 1, cannibals1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }

        if (cannibals1 >= 1) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1, cannibals1 - 1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }

        if (persons1 >= 2) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1 - 2, cannibals1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }
        if (persons1 >= 1 && cannibals1 >= 1) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1 - 1, cannibals1 - 1, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }

        if (cannibals1 >= 2) {
            CannibalNode cannibalNode =
                new CannibalNode(persons1, cannibals1 - 2, this, total, (this.side + 1) % 2);
            if (cannibalNode.isValid()) {
                childs.add(cannibalNode);
            }
        }
    }




    @Override public boolean isFinal() {
        return (persons2 == total) && (cannibals2 == total);
    }

    @Override public String print() {
        StringBuilder sb = new StringBuilder();

        if (parent != null) {
            sb.append(parent.print());
        }
        sb.append(String.format(" : [%s, %s]\n", persons1, cannibals1));
        return sb.toString();
    }

    @Override public boolean isValid() {
        if(persons1 == 0){
            return true;
        }
        if(persons2 == 0){
            return true;
        }
        if(cannibals2 > persons2) {
            System.err.println("Invalid:: "+ this);
            return false;
        }
        if(cannibals1 > persons1){
            System.err.println("Invalid:: "+ this);
            return false;
        }

        return true;
    }

    @Override public String toString() {
        return "{{"+getKey() + " ["+persons2+":"+cannibals2+"]}}";
    }

    @Override public String getKey() {
        return side+":"+persons1 + ":" + cannibals1;
    }
}
