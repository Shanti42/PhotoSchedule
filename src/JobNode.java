import java.util.Objects;

//Class JobNode {
//is comparable by endtime
public class JobNode implements Comparable {

    // Attributes for:
    // name
    String name;
    // start
    int start;
    // end
    int end;
    // priority
    int priority;
    //JobNode predecessor
    JobNode predecessor = null;

    //assign name, start, end and priority on creation
    private JobNode(String name, int start, int end, int priority) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.priority = priority;
    }

    public static JobNode of(String name, int start, int end, int priority) {
        Objects.requireNonNull(name, "JobNode, of() -> name is null");
        //start time must be before end time
        assert (start < end);
        //priority must be between 1 and 100 inclusive
        assert (1 <= priority && priority <= 100);
        return new JobNode(name, start, end, priority);
    }


    //public getters for all attributes

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getPriority() {
        return priority;
    }

    public JobNode getPredecessor() {
        return predecessor;
    }

    //public setter for predecessor
    public void setPredecessor(JobNode predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public int compareTo(Object other) {
        Objects.requireNonNull(other, "JobNode, compareTo -> input parameter to compare is null");
        if (other instanceof JobNode) {
            JobNode otherNode = (JobNode) other;
            return Integer.compare(this.end, otherNode.end);
        } else {
            throw new IllegalArgumentException("JobNode, compareTo -> Object of wrong type given, expected type JobNode");
        }
    }

    @Override
    public boolean equals(Object other) {
        Objects.requireNonNull(other, "JobNode, equals -> input parameter is null");
        if (getClass() != other.getClass()) {
            return false;
        } else {
            JobNode otherNode = (JobNode) other;
            Objects.requireNonNull(otherNode.name, "JobNode, equals -> name value is null");
            return start == otherNode.start &&
                    end == otherNode.end &&
                    name.equals(otherNode.name);
        }
    }
}
