package part3;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

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

            System.out.println("input:");
            for(int i = 0; i < numberOfVertices; i++)
            {
                for(int j = 0; j < numberOfVertices; j++)
                {
                    System.out.print(connections[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

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

    private Stack<Vertex> getCycle(Vertex vertex)
    {
        for(Vertex current : vertices)
        {
            current.setStatus(Vertex.Status.UNVISITED);
        }

        Stack<Vertex> stack = new Stack<>();
        vertex.setStatus(Vertex.Status.IN_PROGRESS);
        stack.push(vertex);

        while(!stack.isEmpty())
        {
            Vertex current = stack.peek();

            boolean flag = false;
            for(Vertex neighbor : current.getNeighbors())
            {
                if(neighbor.getStatus() == Vertex.Status.IN_PROGRESS)
                {
                    return stack;
                }
                else if(neighbor.getStatus() == Vertex.Status.UNVISITED)
                {
                    flag = true;
                    neighbor.setStatus(Vertex.Status.IN_PROGRESS);
                    stack.push(neighbor);
                }
            }

            if(!flag)
            {
                current.setStatus(Vertex.Status.DONE);
                stack.pop();
            }
        }

        return null;
    }

    public Stack<Stack<Vertex>> getCycles()
    {
        Stack<Stack<Vertex>> cycles = new Stack<>();
        for(Vertex vertex : vertices)
        {
            Stack<Vertex> cycle = getCycle(vertex);
            if(cycle != null)
            {
                cycles.push(cycle);
            }
        }
        if(cycles.isEmpty())
        {
            return null;
        }
        else
        {
            return cycles;
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

    public static void test(String path)
    {
        System.out.println("=========================================================================================");
        System.out.println("testing testcase '" + path + "'...\n");

        Graph graph = new Graph(path);
        System.out.println(graph.toString());

        Stack<Stack<Vertex>> cycles = graph.getCycles();
        if(cycles == null)
        {
            System.out.println("the graph is acyclic.");
        }
        else
        {
            System.out.println("the graph is cyclic (the following cycles may be the same)...");
            for(Stack<Vertex> cycle : cycles)
            {
                System.out.print("cycle " + cycles.indexOf(cycle) + "th: ");
                for(Vertex vertex : cycle)
                {
                    System.out.print(vertex.getName() + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        final String TESTCASE_FOLDER = "testcases" + File.separator;
        Graph.test(TESTCASE_FOLDER + "1.txt");
        Graph.test(TESTCASE_FOLDER + "2.txt");
        Graph.test(TESTCASE_FOLDER + "3.txt");
    }
}