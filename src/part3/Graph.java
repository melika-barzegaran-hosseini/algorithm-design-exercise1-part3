package part3;

import java.io.*;
import java.util.ArrayList;

public class Graph
{
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    public Graph(String path)
    {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();

        fillVerticesAndEdges(read(path));
    }

    private int[][] read(String path)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

            String line;

            if((line = reader.readLine()) == null)
            {
                System.err.println("error: testcase file format is violated. first line is null.");
                System.exit(1);
            }

            int numberOfVertices = Integer.parseInt(line);
            int[][] connections = new int[numberOfVertices][numberOfVertices];

            for(int i = 0; i < numberOfVertices; i++)
            {
                if((line = reader.readLine()) == null)
                {
                    System.err.println("testcase file format is violated. line " + i + "th is null.");
                    System.exit(1);
                }

                String[] tokens = line.split(" ");

                if(tokens.length != numberOfVertices)
                {
                    System.err.println("testcase file format is violated. line " + i + "th does not contain all " +
                            "connections.");
                    System.exit(1);
                }

                for(int j = 0; j < numberOfVertices; j++)
                {
                    connections[i][j] = Integer.parseInt(tokens[j]);
                }
            }

            return connections;
        }
        catch (FileNotFoundException e)
        {
            System.err.println("error: testcase file was not found.");
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("error: an error occurred while reading from the testcase file.");
            System.exit(1);
        }
        catch (NumberFormatException e)
        {
            System.err.println("error: testcase file format is violated.\n first line should represent the number of " +
                    "vertices.\n other lines should represent the connections.");
            System.exit(1);
        }

        return null;
    }

    private void fillVerticesAndEdges(int[][] connections)
    {
        for(int i = 0; i < connections.length; i++)
        {
            vertices.add(new Vertex(i));
        }
        for(int i = 0; i < connections.length; i++)
        {
            for(int j = 0; j < connections[i].length; j++)
            {
                if(i == j)
                {
                    continue;
                }

                if(connections[i][j] == 1)
                {
                    Vertex from = vertices.get(i);
                    Vertex to = vertices.get(j);
                    Edge edge = new Edge(from, to);

                    edges.add(edge);
                    from.addOutgoingEdge(edge);
                    to.addIncomingEdge(edge);
                }
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder message = new StringBuilder();
        message.append("vertices: \n");
        for(Vertex vertex : vertices)
        {
            message.append(vertex).append("\n");
        }
        message.append("\nedges: \n");
        for(Edge edge : edges)
        {
            message.append(edge).append("\n");
        }
        return message.toString();
    }

    public static void main(String args[])
    {
        Graph graph = new Graph("testcases/test.txt");
        System.out.println(graph.toString());
    }
}