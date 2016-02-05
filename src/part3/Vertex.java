package part3;

import java.util.ArrayList;

/**
 * Represents a vertex of a graph.
 *
 * @author melika barzegaran hosseini
 */
public class Vertex
{
    private int name;
    private ArrayList<Edge> incomingEdges;
    private ArrayList<Edge> outgoingEdges;

    enum Status{UNVISITED, IN_PROGRESS, DONE}
    private Status status;

    public Vertex(int name)
    {
        this.name = name;

        incomingEdges = new ArrayList<>();
        outgoingEdges = new ArrayList<>();

        this.status = Status.UNVISITED;
    }

    public int getName()
    {
        return name;
    }

    public ArrayList<Edge> getIncomingEdges()
    {
        return incomingEdges;
    }

    public ArrayList<Edge> getOutgoingEdges()
    {
        return outgoingEdges;
    }

    public Status getStatus()
    {
        return status;
    }

    public void addIncomingEdge(Edge edge)
    {
        incomingEdges.add(edge);
    }

    public void addOutgoingEdge(Edge edge)
    {
        outgoingEdges.add(edge);
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public ArrayList<Vertex> getNeighbors()
    {
        ArrayList<Vertex> neighbors = new ArrayList<>();
        for(Edge edge : this.getOutgoingEdges())
        {
            neighbors.add(edge.getEndingVertex());
        }
        return neighbors;
    }

    @Override
    public String toString()
    {
        ArrayList<Integer> from = new ArrayList<>();
        for(Edge edge : incomingEdges)
        {
            from.add(edge.getStartingVertex().getName());
        }

        ArrayList<Integer> to = new ArrayList<>();
        for(Edge edge : outgoingEdges)
        {
            to.add(edge.getEndingVertex().getName());
        }

        return "(name = " + name + ", incoming edges from = " + from + ", outgoing edges to = " + to + ")";
    }
}