namespace KnapsackProblem.Console
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using MainLogic;

    public class EntryPoint
    {
        
        public static void Main()
        {
            Console.Write("Enter knapsack capacity: ");
            int capacity = int.Parse(Console.ReadLine());

            // using predefined products
            var allProducts = GetProductsArray();

            // use your own products
            //GetProductsFromConsole();

            IKnapsackSolver solver = new KnapsackSolver(capacity, allProducts);

            var optimalSolution = solver.SolveWithRecursion();
            PrintResults(optimalSolution);

            var usedProducts = solver.SolveWithDinamicProgramming();
            PrintResults(usedProducts);
        }

        private static void PrintResults(IEnumerable<Product> usedProducts)
        {
            var usedSpace = 0;
            var totalCost = 0;

            foreach (var item in usedProducts)
            {
                Console.WriteLine(item);
                usedSpace += item.Weight;
                totalCost += item.Cost;
            }

            Console.WriteLine("Total weight: {0}", usedSpace);
            Console.WriteLine("Total cost: {0}\n", totalCost);
        }

        private static Product[] GetProductsArray()
        {
            return new Product[]
            {
                new Product("beer",3,2),
                new Product("vodka",8,12),
                new Product("cheese",4,5),
                new Product("nuts",1,4),
                new Product("ham",2,3),
                new Product("whiskey",8,13),
            };
        }

        private static Product[] GetProductsFromConsole()
        {
            var productsToReturn = new List<Product>();

            Console.WriteLine("Write each product in the format N/W/C, where N is name, W is weight, C is cost - example : nuts/1/20");
            Console.WriteLine("Press return to stop");

            string currLine = Console.ReadLine();

            while (currLine != null)
            {
                var tokens = currLine.Split('/');

                productsToReturn.Add(new Product(tokens[0], int.Parse(tokens[1]), int.Parse(tokens[2])));

                currLine = Console.ReadLine();
            }

            return productsToReturn.ToArray();
        }
    }

}
