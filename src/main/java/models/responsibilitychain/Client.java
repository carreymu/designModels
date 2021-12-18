package models.responsibilitychain;

public class Client {
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 32000, 1);

        // 创建审批人
        DepartmentApprover zhang = new DepartmentApprover("老张");
        CollegeApprover li = new CollegeApprover("李");
        ViceSchoolMasterApprover wong = new ViceSchoolMasterApprover("Wong");
        SchoolMasterApprover dan = new SchoolMasterApprover("Dan");

        // 指定下一个审批人(环型设置)
        zhang.setApprover(li);
        li.setApprover(wong);
        wong.setApprover(dan);
        dan.setApprover(zhang);

        // 处理
        zhang.processRequest(purchaseRequest);

        dan.processRequest(purchaseRequest);

    }
}
