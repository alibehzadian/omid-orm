package info.behzadian.repository.entity;

import info.behzadian.repository.annotation.Column;
import info.behzadian.repository.annotation.Table;

@Table(tableName = "BRANCH")
public class Branch {

    @Column(columnName = "ID")
    private Long id;

    @Column(columnName = "BRANCH_NAME")
    private String branchName;

    @Column(columnName = "STATUS")
    private Boolean status;

    @Column(columnName = "ADDRESS")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Branch [address=" + address + ", branchName=" + branchName + ", id=" + id + ", status=" + status + "]";
    }
}
