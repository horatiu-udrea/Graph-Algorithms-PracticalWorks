package graphs;

import java.util.Iterator;

/**
 * Models an undirected Graph data structure
 */
public class UndirectedGraph implements Graph
{
    public int degree(int vertex)
    {
        return 0;
    }

    public Iterator<Integer> parseEdges(int vertex)
    {
        return null;
    }

    @Override
    public int getNumberOfVertices()
    {
        return 0;
    }

    @Override
    public int getNumberOfEdges()
    {
        return 0;
    }

    @Override
    public Iterable<Integer> parseVertices()
    {
        return null;
    }

    @Override
    public boolean existsEdge(int vertex1, int vertex2)
    {
        return false;
    }

    @Override
    public int getCost(int vertex1, int vertex2)
    {
        return 0;
    }

    @Override
    public void changeCost(int vertex1, int vertex2, int newCost)
    {

    }

    @Override
    public void addVertex(int vertex)
    {

    }

    @Override
    public void removeVertex(int vertex)
    {

    }

    @Override
    public void addEdge(int vertex1, int vertex2, int cost)
    {

    }

    @Override
    public void removeEdge(int vertex1, int vertex2)
    {

    }

    @Override
    public Iterable<VertexPair> parseEdges()
    {
        return null;
    }

    @Override
    public Graph copy()
    {
        return null;
    }
}
