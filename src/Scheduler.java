import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Scheduler {

    // Pre-condition: There is a list of nodes with names, priorities, start and end times, priority of nodes must be between 1 and 100
    // Post-condition: There will be a schedule of nodes that maximize the total priority

    // Input:  List of nodes
    // Output: A schedule of nodes that maximizes the benefit

    // findOptimalSchedule( List of JobNodes ) {
    public static List<JobNode> findOptimalSchedule(ArrayList<JobNode> nodeList) {


        // List sortedNodes <- Sort input list by endTime (early to late) using merge sort
        ArrayList<JobNode> sortedNodes = new ArrayList<>(nodeList);
        Collections.sort(sortedNodes);
        // Assign predecessor nodes for each node
        assignPredecessors(sortedNodes);


        // benefits <- array with length equal to the number of nodes, represents total benefits at each Node
        int[] totalBenefits = new int[sortedNodes.size()];

        // set first element of benefit array to 0
        totalBenefits[0] = 0;

        /**will require specificly an array list **/

        // for all nodes starting at the first node in the sorted list
        for (int i = 1; i < nodeList.size(); i++) {
            // Total benefit in array corresponding to current node <- max(benefit at previous job node, benefit at predecessor node + benefit of current node)
            /**TO DO**/
        }

        // return createSchedule(benefits, sortedNodes)
        return createSchedule(totalBenefits, sortedNodes);

    }


    // void assignPredecessors(sorted list of nodes) -> returns the predecessor node which does not overlap
    private static void assignPredecessors(List<JobNode> nodeList) {
        List<JobNode> reverseList = new ArrayList<JobNode>(nodeList);
        Collections.reverse(reverseList);

        // For each JobNode in list:
        for (JobNode currentNode : nodeList) {
            // st <- start time of this JobNode
            int st = currentNode.getStart();
            // for each node in sorted list, going in reverse order, until condition is met:
            foundPredecessor:
            for (JobNode checkNode : reverseList) {
                // If end time of checked node < st
                if (checkNode.getEnd() < st) {
                    // assign checked node as predecessor of this JobNode and continue main loop
                    currentNode.setPredecessor(checkNode);
                    break foundPredecessor;
                }
            }
        }
    }


    // schedule <- empty list, will contain the optimal schedule of nodes
    private static List<JobNode> createSchedule(int[] totalBenefits, ArrayList<JobNode> sortedNodes) {

        int size = sortedNodes.size();

        /** get last element*/
        JobNode currentNode = sortedNodes.get(size - 1);

        List<JobNode> optimalSchedule = new ArrayList<>();
        int index = size;

        // loop backwards over list of total benefits for each node until the first node is reached:
        while (true) {
            int benefitsAtNode = totalBenefits[index];
            int predecessorIndex;

            if (currentNode.getPredecessor() == null) {
                predecessorIndex = 0;
            } else {
                predecessorIndex = sortedNodes.indexOf(currentNode.getPredecessor());
            }

            // If the total benefit of the node is equal to the benefit of the node plus the benefit of the predecessor, then that node is included.
            if (benefitsAtNode == benefitsAtNode + totalBenefits[predecessorIndex]) {
                //Add that node to the schedule.
                optimalSchedule.add(currentNode);
                if (predecessorIndex == 0) {
                    return optimalSchedule;
                } else {
                    index -= predecessorIndex;
                }

            }
            //If the total benefit at a node “A”  is equal to the total benefit at the previous node “B”, then node “A” is not included in the schedule.
            else {
                index -= 1;
            }

        }
        // If the total benefit at a node “A”  is equal to the total benefit at the previous node “B”, then node “A” is not included in the schedule.
        //
        // If the total benefit of the node is equal to the benefit of the node plus the benefit of the predecessor, then that node is included. Add that node to the schedule.

        //return schedule
    }
}




