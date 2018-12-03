/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.domain;

import static org.h2.util.StringUtils.isNullOrEmpty;

/**
 *
 * @author tkarkine
 */
public class Row {
    private int id;
    private int jobId;
    private String resurs;
    private int budgetSum;
    private int usedSum;
    private boolean approved;
    private boolean exceeded;
    private boolean request;
    private int requestSum;
    private String reason;
    
    public Row(int jobId, String resurs, int budgetSum, String reason) {
        //new to be added to database
        this.jobId = jobId;
        this.resurs = resurs;
        this.budgetSum = budgetSum;
        this.usedSum = 0;
        this.approved = false;
        this.exceeded = false;
        this.request = true;
        this.requestSum = budgetSum;
        this.reason = reason;
    }
    
    public Row(int id, int jobId, String resurs, int budgetSum, int usedSum, 
            boolean approved, boolean exceeded, boolean request, int requestSum, String reason) {
        this.id = id;
        this.jobId = jobId;
        this.resurs = resurs;
        this.budgetSum = budgetSum;
        this.usedSum = usedSum;
        this.approved = approved;
        this.exceeded = exceeded;
        this.request = request;
        this.requestSum = budgetSum;
        this.reason = reason;
    }
    
    public int getBudgetSum() {
        return budgetSum;
    }
    
    public boolean setRequests(int requestSum, String reason) {
        if (requestSum < budgetSum || isNullOrEmpty(reason)) {
            return false;
        }
        this.request = true;
        this.requestSum = requestSum;
        this.reason = reason;
        return true;
    }
    
}
