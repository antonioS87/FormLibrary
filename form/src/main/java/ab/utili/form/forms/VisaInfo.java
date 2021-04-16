package ab.utili.form.forms;

import java.util.Date;

public class VisaInfo {
    private String type;

    private Integer serialNumber;

    private Integer visaNumber;

    private Date issueDate, expirationDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(Integer visaNumber) {
        this.visaNumber = visaNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
