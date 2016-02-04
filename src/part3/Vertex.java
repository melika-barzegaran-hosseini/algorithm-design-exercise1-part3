package part3;

import java.util.ArrayList;

public class Vertex
{
    private int name;
    private ArrayList<Edge> incomingEdges;
    private ArrayList<Edge> outgoingEdges;
    private boolean visited;

    public Vertex(int name)
    {
        this.name = name;

        incomingEdges = new ArrayList<>();
        outgoingEdges = new ArrayList<>();

        this.visited = false;
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

    public boolean isVisited()
    {
        return visited;
    }

    public void addIncomingEdge(Edge edge)
    {
        incomingEdges.add(edge);
    }

    public void addOutgoingEdge(Edge edge)
    {
        outgoingEdges.add(edge);
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

        return "(name = " + name + ", incoming edges from = " + from + ", outgoing edges to = " + to + ", visited = "
                + visited + ")";
    }
}