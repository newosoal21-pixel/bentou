package model;

public class DepartmentRegist {
    private int departmentId;
    private String departmentName;
    
    public DepartmentRegist() {}
    
    public DepartmentRegist(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public DepartmentRegist(String departmentid2, String departmentname2) {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
