package budgettool.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static org.h2.util.StringUtils.isNullOrEmpty;

/**
 * Model Class for budgetRow
 * 
 * @author tkarkine
 */

public class Row {
    private final IntegerProperty id;
    private final IntegerProperty jobId;
    private final StringProperty resurs;
    private final IntegerProperty budgetSum;
    private final IntegerProperty usedSum;
    private final BooleanProperty approved;
    private final BooleanProperty exceeded;
    private final BooleanProperty request;
    private final IntegerProperty requestSum;
    private final StringProperty reason;
    
    public Row(int jobId, String resurs, int budgetSum, String reason) {
        //new to be added to database
        this.id = new SimpleIntegerProperty(0);
        this.jobId = new SimpleIntegerProperty(jobId);
        this.resurs = new SimpleStringProperty(resurs);
        this.budgetSum = new SimpleIntegerProperty(budgetSum);
        this.usedSum = new SimpleIntegerProperty(0);
        this.approved = new SimpleBooleanProperty(false);
        this.exceeded = new SimpleBooleanProperty(false);
        this.request = new SimpleBooleanProperty(true);
        this.requestSum = new SimpleIntegerProperty(budgetSum);
        this.reason = new SimpleStringProperty(reason);
    }
    
    public Row(int id, int jobId, String resurs, int budgetSum, int usedSum, 
            boolean approved, boolean exceeded, boolean request, int requestSum, String reason) {
        this.id = new SimpleIntegerProperty(id);
        this.jobId = new SimpleIntegerProperty(jobId);
        this.resurs = new SimpleStringProperty(resurs);
        this.budgetSum = new SimpleIntegerProperty(budgetSum);
        this.usedSum = new SimpleIntegerProperty(usedSum);
        this.approved = new SimpleBooleanProperty(approved);
        this.exceeded = new SimpleBooleanProperty(exceeded);
        this.request = new SimpleBooleanProperty(request);
        this.requestSum = new SimpleIntegerProperty(requestSum);
        this.reason = new SimpleStringProperty(reason);
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public void getId() {
        this.id.get();
    }
    
    public IntegerProperty idProperty() {
        return id;
    }
    
    public void setJobId(int jobId) {
        this.jobId.set(jobId);
    }
    
    public void getJobId() {
        this.jobId.get();
    }
     
    public void setResurs(String resurs) {
        this.resurs.set(resurs);
    }
     
    public void getResurs() {
        this.resurs.get();
    }
    
    public StringProperty resursProperty() {
        return resurs;
    }
     
    public IntegerProperty jobIdProperty() {
        return jobId;
    }
    
    public void setBudgetSum(int budgetSum) {
        this.budgetSum.set(budgetSum);
    }
    
    public void getBudgetSum() {
        this.budgetSum.get();
    }
     
    public IntegerProperty budgetSumProperty() {
        return budgetSum;
    }
    
    public void setUsedSum(int usedSum) {
        this.usedSum.set(usedSum);
    }
     
    public void getUsedSum() {
        this.usedSum.get();
    }
    
    public IntegerProperty usedSumProperty() {
        return usedSum;
    }
    
    public void setApproved(Boolean approved) {
        this.approved.set(approved);
    }
    
    public void getApproved() {
        this.approved.get();
    }
    
    public BooleanProperty approvedProperty() {
        return approved;
    }
     
    public void setExceede(Boolean exceeded) {
        this.exceeded.set(exceeded);
    }
     
    public void getExceeded() {
        this.exceeded.get();
    }
    
    public BooleanProperty exceededProperty() {
        return exceeded;
    }
    
    public void setRequest(Boolean request) {
        this.request.set(request);
    }
     
    public void getRequest() {
        this.request.get();
    }
    
    public BooleanProperty requestProperty() {
        return request;
    }
     
    public void setRequestSum(int requestSum) {
        this.requestSum.set(requestSum);
    }
    
    public void getRequestSum() {
        this.requestSum.get();
    }
     
    public IntegerProperty requestSumProperty() {
        return requestSum;
    }
    
    public void setReason(String reason) {
        this.reason.set(reason);
    }
     
    public void getReason() {
        this.reason.get();
    }
    
    public StringProperty reasonProperty() {
        return reason;
    }
    
    public boolean setRequests(int requestSum, String reason) {
        if (requestSum < budgetSum.get() || isNullOrEmpty(reason)) {
            return false;
        }
        this.request.set(true);
        this.requestSum.set(requestSum);
        this.reason.set(reason);
        return true;
    }
    
    public double getUsedPros() {
        return (double) (usedSum.get() * 100 / budgetSum.get());
    }
    
    @Override
    public String toString() {
        String status = jobId + ": " + id + " ";
        if (approved.get()) {
            status += "Approved ";
        }
        if (exceeded.get()) {
            status += "EXCEEDED ";
        }
        if (request.get()) {
            status += "Request more " + ((double) (requestSum.get() - budgetSum.get()) / 100) + " ";
        } 
        return status + resurs.get() + " used: " + ((double) usedSum.get() / 100) + "/" + ((double) budgetSum.get() / 100);
                 
    }
    
}
