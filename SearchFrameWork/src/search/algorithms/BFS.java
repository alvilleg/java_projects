package search.algorithms;

import search.Explorable;
import search.Status;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by alde on 5/28/18.
 */
public class BFS implements ISearch{

    @Override
    public Status search(Explorable initial) {
        Queue<Explorable> nodes = new LinkedList<Explorable>();
        nodes.add(initial);
        Explorable currentSol = initial;
        Map<String, String> statuses = new HashMap<String, String>();
        statuses.put(initial.getKey(),initial.getKey());
        while (!nodes.isEmpty()){
            Explorable currentNode = nodes.poll();

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
            System.err.println("Nodes ::: "+ nodes.size());
        }
        return currentSol;
    }
}
