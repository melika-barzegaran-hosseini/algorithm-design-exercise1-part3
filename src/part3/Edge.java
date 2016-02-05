package part3;

/**
 * Represents an edge of a graph.
 *
 * @author melika barzegaran hosseini
 */
public class Edge
{
    private Vertex startingVertex;
    private Vertex endingVertex;

    public Edge(Vertex startingVertex, Vertex endingVertex)
    {
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
    }

    public Vertex getStartingVertex()
    {
        return startingVertex;
    }

    public Vertex getEndingVertex()
    {
        return endingVertex;
    }

    @Override
    public String toString()
    {
        return "(starting vertex = " + startingVertex.getName() + ", ending vertex = " + endingVertex.getName() + ")";
    }
}