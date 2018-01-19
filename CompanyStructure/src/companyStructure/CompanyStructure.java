package companyStructure;

public class CompanyStructure {
    public static void main(String[] args) {


        TechnicalLead CTO = new TechnicalLead("Satya Nadella");
        SoftwareEngineer seA = new SoftwareEngineer("Kasey");
        SoftwareEngineer seB = new SoftwareEngineer("Breana");
        SoftwareEngineer seC = new SoftwareEngineer("Eric");
        CTO.addReport(seA);
        CTO.addReport(seB);
        CTO.addReport(seC);
        System.out.println(CTO.getTeamStatus());
        System.out.println();

        TechnicalLead VPofENG = new TechnicalLead("Bill Gates");
        SoftwareEngineer seD = new SoftwareEngineer("Winter");
        SoftwareEngineer seE = new SoftwareEngineer("Libby");
        SoftwareEngineer seF = new SoftwareEngineer("Gizan");
        SoftwareEngineer seG = new SoftwareEngineer("Zaynah");
        VPofENG.addReport(seD);
        VPofENG.addReport(seE);
        VPofENG.addReport(seF);
        VPofENG.addReport(seG);
        System.out.println(VPofENG.getTeamStatus());

        BusinessLead CFO = new BusinessLead("Amy Hood");
        Accountant actA = new Accountant("Niky");
        Accountant actB = new Accountant("Andrew");

        CFO.addReport(actA, CTO);
        CFO.addReport(actB, VPofENG);

        System.out.println("----------------Testing code----------------");
        System.out.println("Info about CFO: ");
        System.out.println(CFO.employeeStatus());
        System.out.println("Base salary is: "+CFO.getBaseSalary());
        System.out.println();
        System.out.println("Info about CTO: ");
        System.out.println("CTO base salary is: "+CTO.getBaseSalary());
        System.out.println(CTO.employeeStatus());
        System.out.println("Head count: "+CTO.getHeadCount());
        System.out.println();

        System.out.println("----requesting/approving bonus----");
        System.out.println("seA current bonus: "+seA.getCurrentBonus());
        //can not-manager request bonus?
        VPofENG.requestBonus(seA,2000);
        CTO.requestBonus(seA,2000);
        System.out.println("seA bonus after getting bonus: "+seA.getCurrentBonus());
        System.out.println();
        System.out.println("----code access and check-ins-----");
        System.out.println("seB Code access: "+seB.getCodeAccess());
        //seA code access is false
        seB.setCodeAccess(true);
        seC.setCodeAccess(true);
        seA.checkInCode(CTO.approveCheckIn(seA));
        seB.checkInCode(CTO.approveCheckIn(seB));
        seB.checkInCode(CTO.approveCheckIn(seB));
        seC.checkInCode(CTO.approveCheckIn(seC));
        System.out.println(CTO.getTeamStatus());

        System.out.println("SeA manager is: "+seA.getManager());
        System.out.println("ActA manager is: "+actA.getManager());

}}


