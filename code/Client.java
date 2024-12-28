import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Region> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Region> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    // Allocates relief funds to regions based on a given budget and list of regions.
    // Explores different combinations of regions to maximize the number of people helped
    // within the given budget, but if need be, picks the allocation
    // with the most people helped at the lowest cost.
    // Parameters:
    //      double budget - the available funds for relief distributions
    //      List<Region> sites - list of sites in need of relief distributions
    // Return:
    //      Allocation - The most optimal use of the funds
    public static Allocation allocateRelief(double budget, List<Region> sites) {
        return allocateRelief(budget, sites, new Allocation());
    }

    // Helper method for allocateRelief. Recursively explores different combinations of regions.
    // Stays within the budget, maximizes number of people helped.
    // Parameters:
    //      double budget - the available funds for relief distributions
    //      List<Region> sites - list of sites in need of relief distributions
    //      Allocation currAllo - the current combination of regions
    // Return:
    //      Allocation - The most optimal use of the funds 
    private static Allocation allocateRelief(double budget, List<Region> sites, 
                                            Allocation currAllo) {
        if (sites.isEmpty() || budget <= 0) { // base
            return currAllo;
        } 

        Allocation bestAllo = currAllo; // keep track
        int mostHelped = currAllo.totalPeople();

        for (Region region : sites) {
            double cost = region.getCost(currAllo.size()); // index-based

            if (cost <= budget && !currAllo.getRegions().contains(region)) {
                Allocation newAllo = currAllo.withRegion(region);
                Allocation result = allocateRelief(budget - cost, sites, newAllo);
                
                int peopleHelped = result.totalPeople();
                // checking greater pop and tiebreaker, less cost
                if (peopleHelped > mostHelped ||
                    (peopleHelped == mostHelped && 
                                                result.totalCost() < bestAllo.totalCost())) {
                    bestAllo = result;
                    mostHelped = peopleHelped;
                }
            }
        }
        return bestAllo;
    }

    ///////////////////////////////////////////////////////////////////////////
    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ///////////////////////////////////////////////////////////////////////////
    
    public static void printAllocations(Set<Allocation> allocations) {
        System.out.println("All Allocations:");
        for (Allocation a : allocations) {
            System.out.println("  " + a);
        }
    }

    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

    public static List<Region> createRandomScenario(int numLocs, int minPop, int maxPop,
                                                    double minCostPer, double maxCostPer) {
        List<Region> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Region("Region #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Region> createSimpleScenario() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 50, 500));
        result.add(new Region("Region #2", 100, 700));
        result.add(new Region("Region #3", 60, 1000));
        result.add(new Region("Region #4", 20, 1000));
        result.add(new Region("Region #5", 200, 900));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
