package search.algorithms;

import search.Explorable;
import search.Status;

import java.util.*;

/**
 * Created by alde on 5/28/18.
 */
public class DFS implements ISearch {
    @Override
    public Status search(Explorable initial) {
        Stack<Explorable> nodes = new Stack<Explorable>();
        nodes.add(initial);
        Explorable currentSol = initial;

        Map<String, String> statuses = new HashMap<String, String>();
        statuses.put(initial.getKey(),initial.getKey());
        while (!nodes.isEmpty()) {
            Explorable currentNode = nodes.pop();

            if(currentNode.isFinal()){
                currentSol = currentNode;
                break;
            }
            for(Explorable newNode : currentNode.expand()){
                if(statuses.get(newNode.getKey()) != null){
                    continue;
                }
                statuses.put(newNode.getKey(), newNode.getKey());
                nodes.add(newNode);
            }
        }
        return currentSol;
    }
}
