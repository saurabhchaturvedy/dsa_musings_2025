package Post14June.Design.CostExplorer;

import java.math.BigDecimal;

public enum PricingPlan {


    TRIAL("TRIAL", BigDecimal.ZERO),
    BASIC("BASIC", new BigDecimal("9.99")),
    STANDARD("STANDARD", new BigDecimal("49.99")),
    PREMIUM("PREMIUM", new BigDecimal("249.99"));


    String planId;
    BigDecimal monthlyCost;

    PricingPlan(String planId, BigDecimal monthlyCost) {
        this.planId = planId;
        this.monthlyCost = monthlyCost;
    }
}
