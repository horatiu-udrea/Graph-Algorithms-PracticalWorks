package undirectedGraph;

import graphs.algorithms.Traversal;
import graphs.exceptions.EdgeAlreadyExistsException;
import graphs.exceptions.EdgeDoesNotExistException;
import graphs.exceptions.VertexAlreadyExistsException;
import graphs.exceptions.VertexDoesNotExistException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class ConsoleUI
{
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);
    private Map<Integer, Option> options;

    public ConsoleUI(Controller controller)
    {
        this.controller = controller;
        this.createOptionList();
    }

    private void createOptionList()
    {
        options = new HashMap<>(17);
        options.put(1, new Option("Number of vertices", this::numberOfVertices));
        options.put(2, new Option("List vertices", this::listVertices));
        options.put(3, new Option("Check if edge exists", this::edgeExists));
        options.put(4, new Option("Get degree", this::getDegree));
        options.put(6, new Option("Get adjacent edges", this::adjacentEdges));
        options.put(8, new Option("Get edge cost", this::getEdgeCost));
        options.put(9, new Option("Set edge cost", this::setEdgeCost));
        options.put(10, new Option("Add edge", this::addEdge));
        options.put(11, new Option("Remove edge", this::removeEdge));
        options.put(12, new Option("Add vertex", this::addVertex));
        options.put(13, new Option("Remove vertex", this::removeVertex));
        options.put(15, new Option("Get edges", this::getEdges));
        options.put(16, new Option("Get connected components", this::getConnectedComponents));
        options.put(17, new Option("Find a Hamiltonian cycle of low cost", this::lowHamiltonian));

    }

    private void lowHamiltonian()
    {
        Controller.DTOHamiltonian dtoHamiltonian = controller.lowHamiltonian();
        if (dtoHamiltonian == null)
        {
            print("The heuristic did not find any Hamiltonian cycle of low cost");
            return;
        }
        List<Integer> vertices = dtoHamiltonian.getCycle();
        int cost = dtoHamiltonian.getCost();
        String cycle = vertices.stream().map(String::valueOf).collect(Collectors.joining(" - "));
        print("The Hamiltonian cycle of low cost is: " + cycle);
        print("The cost of the cycle is " + cost);
    }

    private void getConnectedComponents()
    {
        print(controller.showConnectedComponents());
    }

//    private void writeGraphToFile()
////    {
////        print("Choose file name: ");
////        String fileName = scanner.next();
////        print(controller.writeGraphToFile(fileName));
////    }


    public void run()
    {
        if(true)
        {
            lowHamiltonian();
            return;
        }
        while (true)
        {
            clearConsole();
            System.out.println("\nMenu");

            options.forEach((number, option) ->
                    System.out.println(String.format("%d. %s", number, option.getName())));

            System.out.println("0. Exit");
            System.out.println("Your choice:");

            int option = scanner.nextInt();
            if (option == 0)
            {
                break;
            }
            try
            {
                executeOption(option);
            } catch (EdgeAlreadyExistsException e)
            {
                System.out.println("The specified edge already exists!\n");
            } catch (EdgeDoesNotExistException e)
            {
                System.out.println("The specified edge does not exist!\n");
            } catch (VertexAlreadyExistsException e)
            {
                System.out.println("The specified vertex already exists!\n");
            } catch (VertexDoesNotExistException e)
            {
                System.out.println("The specified vertex does not exist!\n");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void clearConsole()
    {
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException ignored)
        {

        }
    }

    private void executeOption(int option)
    {
        options.get(option).executeCommand();
    }

    private void getEdges()
    {
        print(controller.getEdges());
    }

//    private void generateRandomGraph()
//    {
//        int vertexNumber = getInt("Choose the vertex number: ");
//        int edgeNumber = getInt("Choose the edge number: ");
//        controller.generateRandomGraph(vertexNumber, edgeNumber);
//    }

    private void removeVertex()
    {
        int vertex = getInt("Choose vertex: ");
        controller.removeVertex(vertex);
    }

    private void addVertex()
    {
        int vertex = getInt("Choose vertex: ");
        controller.addVertex(vertex);
    }

    private void removeEdge()
    {
        int vertex1 = getInt("Choose vertex 1: ");
        int vertex2 = getInt("Choose vertex 2: ");
        controller.removeEdge(vertex1, vertex2);
    }

    private void addEdge()
    {
        int vertex1 = getInt("Choose vertex 1: ");
        int vertex2 = getInt("Choose vertex 2: ");
        int cost = getInt("Choose new cost: ");
        controller.addEdge(vertex1, vertex2, cost);
    }

    private void setEdgeCost()
    {
        int vertex1 = getInt("Choose vertex 1: ");
        int vertex2 = getInt("Choose vertex 2: ");
        int cost = getInt("Choose new cost: ");
        controller.setCost(vertex1, vertex2, cost);
    }

    private void getEdgeCost()
    {
        int vertex1 = getInt("Choose vertex 1: ");
        int vertex2 = getInt("Choose vertex 2: ");
        print(controller.getCost(vertex1, vertex2));
    }

    private void adjacentEdges()
    {
        int vertex = getInt("Choose vertex: ");
        print(controller.getAdjacentEdges(vertex));
    }

    private void getDegree()
    {
        int vertex = getInt("Choose vertex: ");
        print(controller.getDegree(vertex));
    }

    private void edgeExists()
    {
        int vertex1 = getInt("Choose vertex 1: ");
        int vertex2 = getInt("Choose vertex 2: ");
        print(controller.existsEdge(vertex1, vertex2) ? "The edge exists\n" : "The edge does not exist\n");
    }

    private void listVertices()
    {
        print(controller.getVertices());
    }

    private void numberOfVertices()
    {
        print(controller.getNumberOfVertices());
    }

    private int getInt(String message)
    {
        print(message);
        return scanner.nextInt();
    }

    private void print(String message)
    {
        System.out.println(message);
    }

    private static class Option
    {
        private final String name;
        private final Command command;

        Option(String name, Command command)
        {
            this.name = name;
            this.command = command;
        }

        String getName()
        {
            return name;
        }

        Command getCommand()
        {
            return command;
        }

        void executeCommand()
        {
            command.executeCommand();
        }

        @FunctionalInterface
        private interface Command
        {
            void executeCommand();
        }
    }
}
