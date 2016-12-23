package org.lk.util.http;

/**
 * Created by lk on 2016/12/23.
 */
public class MoneyMath {
    /**
     * 投资利息 ，每月复利方式 50000×（1+3%）^30
     *
     * @param monthCount投资月数
     * @param InterestRate年利率
     * @param money投资金额
     * @return 最后资产
     */
    public static double interestOnOnvestment(int monthCount, double InterestRate, double money) {
        return money * Math.pow(1 + InterestRate / 12, monthCount);
    }

    /**
     * 贷款投资计算
     *
     * @param monthCount投资月数
     * @param InterestRate年利率
     * @param money贷款金额
     * @param loanRate贷款利率
     * @return 剩余金额
     */
    public static double loanInvestment(int monthCount, double InterestRate, double money, double loanRate) {
        return InterestRate;
    }

    /**
     * 贷款每月 支付 ，等额本息 计算公式：
     * 计划月还款额=〔贷款本金×月利率×(1+月利率)^还款月数〕÷〔(1+月利率)^还款月数-1〕------还款月数=贷款年限×12
     * 月利率=年利率/12
     *
     * @param monthCount贷款月数
     * @param money贷款金额
     * @param loanRate贷款利率
     * @return 每月支付
     */
    public static double loanEveMonth1(int monthCount, double money, double loanRate) {
        double monthRate = loanRate / 12;
        return money * monthRate * Math.pow(1 + monthRate, monthCount) / Math.pow((1 + monthRate), monthCount - 1);
    }

    /**
     * 等额本金还款
     * 等额本金还款，即每月等额偿还本金，贷款利息随本金减少逐月递减直至结清贷款。也就是说，每月归还本金的数额相等，利息=当期剩余本金×日利率×
     * 当期日历天数，每月的还款额并不固定，而是随着每月本金的减少而递减，随着还款次数的增多，利息由多逐渐减少。 计算公式：
     * 计划月还款额=(贷款本金÷还款月数)+(贷款本金-累计已还本金)×月利率 累计已还本金=已经归还贷款的月数×贷款本金/还款月数
     *
     * @param monthCount贷款月数
     * @param money贷款金额
     * @param loanRate贷款利率
     * @return 每月支付
     */
    public static double loanEveMonth2(int monthCount, double money, double loanRate) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(loanEveMonth1(12, 10000, 0.1));
    }
}
