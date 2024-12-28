import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("ALLOCATE RELIEF TEST - Budget Range")
    public void firstCaseTest() {
        List<Region> regions = new ArrayList<>();
        regions.add(new Region("inBudgetRange", 50, 1000.0));
        regions.add(new Region("outOfBudgetRange", 100, 1200.0));

        Allocation result = Client.allocateRelief(1000.0, regions);

        // Checks to see whether allocations go to the region with costs within the budget.
        assertTrue(result.toString().contains("inBudgetRange"));
        assertFalse(result.toString().contains("outOfBudgetRange"));
    }

    @Test
    @DisplayName("ALLOCATE RELIEF TEST - Population")
    public void secondCaseTest() {
        List<Region> regions = new ArrayList<>();
        Region lessPop = new Region("Region 1", 100, 400.0);
        Region morePop = new Region("Region 2", 150, 600.0);

        regions.add(lessPop);
        Allocation firstResult = Client.allocateRelief(700.0, regions);

        // Checks to see that the 1 region added is correctly allocated the funds
        assertTrue(firstResult.toString().contains("Region 1"));


        regions.add(morePop);
        Allocation secondResult = Client.allocateRelief(700.0, regions);

        // Checks to see if the update to regions correctly 
        // allocates the funds regarding the new region added
        assertFalse(secondResult.toString().contains("Region 1"));
        assertTrue(secondResult.toString().contains("Region 2"));
    }

    @Test
    @DisplayName("ALLOCATE RELIEF TEST - Population Tiebreaker")
    public void thirdCaseTest() {
        List<Region> regions = new ArrayList<>();
        regions.add(new Region("lessCost", 150, 1000.0));
        regions.add(new Region("moreCost", 150, 1200.0));

        Allocation result = Client.allocateRelief(1400.0, regions);

        // Checks to see if the allocation goes to the region with the 
        // same population as another region, but has a lesser cost
        assertTrue(result.toString().contains("lessCost"));
        assertFalse(result.toString().contains("moreCost"));
    }
}
