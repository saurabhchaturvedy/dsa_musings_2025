package Post14June.Design.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {



        Subscription subscription = new Subscription(PricingPlan.BASIC, LocalDate.parse("2023-01-01"), null, null);

        Customer customer = new Customer("c1", "Jira", subscription);

        ICostExplorer costExplorer = new CostExplorer(customer);

        BigDecimal[] monthlyCostList = costExplorer.monthlyCosts();

        System.out.println(" monthly cost list : " + Arrays.toString(monthlyCostList));

        BigDecimal annualCost = costExplorer.annualCosts();

        System.out.println(" Annual cost : " + annualCost);

        System.out.println(" TRIAL PLAN :");

        Subscription subscription2 = new Subscription(PricingPlan.TRIAL, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-02-28"), PricingPlan.BASIC);

        Customer customer2 = new Customer("c1", "Jira", subscription2);

        ICostExplorer costExplorer2 = new CostExplorer(customer2);

        BigDecimal[] monthlyCostList2 = costExplorer2.monthlyCosts();

        System.out.println(" monthly cost list : " + Arrays.toString(monthlyCostList2));

        BigDecimal annualCost2 = costExplorer2.annualCosts();

        System.out.println(" Annual cost : " + annualCost2);

    }
}
