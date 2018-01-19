package companyStructure;

import java.util.ArrayList;
import java.util.List;

public class BusinessLead extends BusinessEmployee implements Manager {
    private int headCount;
    private List<Accountant> reports;

    public BusinessLead(String name) {
        super(name);
        setBaseSalary(getBaseSalary() * 2);
        this.headCount = 10;
        reports = new ArrayList<Accountant>();
    }

    public List<Accountant> getReports(){
        return reports;
    }

    public boolean hasHeadCount() {
        if (reports.size() < this.headCount)
            return true;
        return false;
    }

    @Override
    public int setHeadCount(int headCount) {
        this.headCount = headCount;
        return this.headCount;
    }

    public boolean approveBonus(Employee e, double bonus) {
        for (Accountant a : this.reports) {
            if (a.getTeamSupported()==e.getManager()) {
                if(a.getBonusBudget()>bonus){
                    System.out.println("Bonus approved");
                    return true;
                }
            }
            else return false;
        }
        return true;
    }

    public void setBusinessLead(TechnicalLead lead){
        lead.businessLead=this;
    }

    /*
    If the BusinessLead has head count left, adds employee to his/hers list of direct reports.
    When report is added, BusinessLead's bonus budget is increased by 1.1 times that new employee's base salary.
    The employee team is updated to reflect the reference to the TechnicalLead given.
    */
    public boolean addReport(Accountant e, TechnicalLead supportTeam) {
        if (hasHeadCount()) {
            reports.add(e);
            setBonusBudget(getBonusBudget() + e.getBaseSalary() + e.getBaseSalary() * 0.1);  //if multiply by 1.1 precision is .0000001
            e.supportTeam(supportTeam);
            e.manager = this;
            setBusinessLead(supportTeam);
            return true;
        } else return false;
    }

    /*
    If the bonus amount requested would fit in current BusinessLead's budget, Employee gets the bonus,
    and the bonus budget of this Business Lead is deducted.
     */
    public boolean requestBonus(Employee e, double bonus) {
        if (getBonusBudget() >= bonus) {
            setBonusBudget(getBonusBudget() - bonus);
            e.setCurrentBonus(e.getCurrentBonus() + bonus);
            return true;
        }
        else return false;
    }
}