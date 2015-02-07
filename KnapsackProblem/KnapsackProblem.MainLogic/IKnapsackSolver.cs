namespace KnapsackProblem.MainLogic
{
    using System.Collections.Generic;

    public interface IKnapsackSolver
    {
        IEnumerable<Product> SolveWithRecursion();

        IEnumerable<Product> SolveWithDinamicProgramming();
    }
}
