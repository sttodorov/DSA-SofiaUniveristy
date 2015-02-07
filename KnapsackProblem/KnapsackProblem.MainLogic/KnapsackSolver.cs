namespace KnapsackProblem.MainLogic
{
    using System;
    using System.Linq;
    using System.Collections.Generic;

    public class KnapsackSolver :IKnapsackSolver
    {
        private int capacity;

        private Product[] allProducts;

        private int[,] valueTable;

        public KnapsackSolver(int capacity, Product[] allProducts)
        {
            this.capacity = capacity;
            this.allProducts = allProducts;
            valueTable = new int[allProducts.Length + 1, capacity + 1];
        }

        public IEnumerable<Product> SolveWithRecursion()
        {
            HashSet<Product> usedProducts = new HashSet<Product>();
            int usedCost = 0;

            HashSet<Product> optimalSolution = new HashSet<Product>();
            int bestCost = 0;

            Recursion(0, usedProducts, optimalSolution, ref usedCost, ref bestCost);

            return optimalSolution;
        }

        public IEnumerable<Product> SolveWithDinamicProgramming()
        {
            for (int prod = 1; prod < valueTable.GetLength(0); prod++)
            {
                for (int cap = 1; cap < valueTable.GetLength(1); cap++)
                {
                    int upperValue = valueTable[prod - 1, cap];
                    int currentValue = GetValueForCurrentCapacity(prod, cap);
                    valueTable[prod, cap] = Math.Max(upperValue, currentValue);
                }
            }

            return GetUsedProductsArray();
        }

        #region RecursiveSolution
        private void Recursion(int capacityUsed, HashSet<Product> usedProducts, HashSet<Product> optimalSolution,
            ref int usedCost, ref int bestCost)
        {
            if (capacityUsed > capacity)
            {
                return;
            }

            if (usedCost > bestCost)
            {
                bestCost = usedCost;
                optimalSolution.Clear();

                foreach (var p in usedProducts)
                {
                    optimalSolution.Add(p);
                }
            }

            foreach (var product in allProducts)
            {
                if (capacityUsed + product.Weight <= capacity && !usedProducts.Contains(product))
                {
                    usedProducts.Add(product);
                    usedCost += product.Cost;

                    Recursion(capacityUsed + product.Weight, usedProducts, optimalSolution, ref usedCost, ref bestCost);

                    usedProducts.Remove(product);
                    usedCost -= product.Cost;
                }
            }
        }
        #endregion

        #region DPSolution
        private List<Product> GetUsedProductsArray()
        {
            List<Product> usedProducts = new List<Product>();

            int leftWeight = capacity;
            int itemIndex = allProducts.Length;

            //the 1st row is only 0
            while (itemIndex > 0)
            {
                //if true we actually used this item              //compensating 0based array
                if (valueTable[itemIndex, leftWeight] != valueTable[itemIndex - 1, leftWeight])
                {
                    usedProducts.Add(allProducts[itemIndex - 1]);
                    //we move on a column with the remaining weight
                    leftWeight -= allProducts[itemIndex - 1].Weight;
                }

                itemIndex--;
            }

            return usedProducts;
        }

        private int GetValueForCurrentCapacity(int prod, int cap)
        {
            //our 1st product is actually the 0th in the array
            int productIndex = prod - 1;

            //the product fits
            if (allProducts[productIndex].Weight <= cap)
            {
                return allProducts[productIndex].Cost + valueTable[prod - 1, cap - allProducts[productIndex].Weight];
            }

            return 0;
        }

        #endregion
    }
}
