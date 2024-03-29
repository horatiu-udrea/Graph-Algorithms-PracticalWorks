package undirectedGraph;

import graphs.UndirectedGraph;
import graphs.utils.FileUtils;

import java.io.FileNotFoundException;

class Main
{
    private static final String fileName = "data/hamiltonian.txt";

    public static void main(String[] args)
    {
        UndirectedGraph undirectedGraph;
        try
        {
            undirectedGraph = FileUtils.createUndirectedGraphFromFile(fileName);
        } catch (FileNotFoundException e)
        {
            System.out.println(String.format("File \"%s\" was not found", fileName));
            undirectedGraph = new UndirectedGraph();
        }
        Controller controller = new Controller(undirectedGraph);
        ConsoleUI consoleUI = new ConsoleUI(controller);
        consoleUI.run();
    }
}
