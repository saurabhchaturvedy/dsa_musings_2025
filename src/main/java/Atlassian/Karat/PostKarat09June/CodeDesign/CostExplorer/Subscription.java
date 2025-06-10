package PostKarat09June.CodeDesign.CostExplorer;

import java.time.LocalDate;

public class Subscription {


    PricingPlan planId;

    LocalDate startDate;
    LocalDate endDate;

    PricingPlan planAfterTrialEnds;


    Subscription(PricingPlan planId, LocalDate startDate, LocalDate endDate, PricingPlan planAfterTrialEnds) {

        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planAfterTrialEnds = planAfterTrialEnds;
    }


    public PricingPlan getPlanAfterTrialEnds() {
        return planAfterTrialEnds;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public PricingPlan getPlanId() {
        return planId;
    }
}
