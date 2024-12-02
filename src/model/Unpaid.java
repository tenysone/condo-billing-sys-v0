package model;

public class Unpaid extends Unit{
    private double MonthlyInstallment;

    public Unpaid(String UnitNo, String Floor, String UnitArea, String Status, double UnitPrice, double MonthlyInstallment){
        super(UnitNo,Floor,UnitArea,Status,UnitPrice);
        this.MonthlyInstallment= MonthlyInstallment;
    }
    public void setMonthlyInstallment(double MonthlyInstallment) {
        this.MonthlyInstallment = MonthlyInstallment;
    }
    public double getMonthlyInstallment() {
        return MonthlyInstallment;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("%20s",MonthlyInstallment);
    }
}
