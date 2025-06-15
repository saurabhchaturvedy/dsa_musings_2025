package Post14June.Design.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;

public class CostExplorer implements ICostExplorer {


    Customer customer;

    CostExplorer(Customer customer) {

        this.customer = customer;
    }


    @Override
    public BigDecimal[] monthlyCosts() {

        BigDecimal[] monthlyCosts = new BigDecimal[12];
        Arrays.fill(monthlyCosts, BigDecimal.ZERO);

        Subscription subscription = customer.getSubscription();
        PricingPlan planId = subscription.getPlanId();
        BigDecimal monthlyCost = planId != null ? planId.monthlyCost : BigDecimal.ZERO;
        LocalDate startDate = subscription.getStartDate();

        if (PricingPlan.TRIAL.equals(planId)) {

            LocalDate endDate = subscription.getEndDate();

            Period trialPeriod = Period.between(startDate, endDate);

            Integer totalMonths = trialPeriod.getMonths() + 1;

            for (int i = 0; i < totalMonths; i++) {

                int index = startDate.getMonthValue() - 1;
                monthlyCosts[index] = monthlyCost;
                startDate = startDate.plusMonths(1);
            }


            PricingPlan planAfterTrialEnds = subscription.getPlanAfterTrialEnds();

            monthlyCost = planAfterTrialEnds != null ? planAfterTrialEnds.monthlyCost : BigDecimal.ZERO;

            for (int i = startDate.getMonthValue(); i < 12; i++) {
                int index = startDate.getMonthValue() - 1;
                monthlyCosts[index] = monthlyCost;
            }


        } else {


            LocalDate endDate = LocalDate.of(startDate.getYear(), Month.DECEMBER, 31);

            Period subscriptionPeriod = Period.between(startDate, endDate);


            Integer totalMonths = subscriptionPeriod.getMonths() + 1;

            for (int i = 0; i < 12; i++) {

                int index = startDate.getMonthValue() - 1;
                monthlyCosts[index] = monthlyCost;
                startDate = startDate.plusMonths(1);

            }
        }


        return monthlyCosts;
    }

    @Override
    public BigDecimal annualCosts() {
        return Arrays.stream(monthlyCosts()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
