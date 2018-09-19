/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jee18.logic;

import java.util.List;
import jee18.dto.Timesheet;

/**
 *
 * @author okaracalik
 */
public interface ITimesheetSystem {

    // -SECRETARY
    // -SUPERVISOR
    // -ASSISTANT
    public List<Timesheet> list();
    
    // FIXME: employee, may be supervisor, assistant
    // -EMPLOYEE ***
    public List<Timesheet> listMyTimesheets();

    // -SECRETARY
    // -SUPERVISOR
    // -ASSISTANT
    public Timesheet get(String uuid);
    
    // FIXME: employee, may be supervisor, assistant
    // -EMPLOYEE ***
    // public Timesheet getMyTimesheet(String uuid);

    // FIXME: it may not be called
    // RULE: if not signed
    // RULE: if not archived
    public Integer delete(String uuid);

    // FIXME: owner
    // -EMPLOYEE ***
    // RULE: if not archived
    public Integer signAsEmployee(String uuid);

    // FIXME: owner
    // -EMPLOYEE ***
    // TASK: setStatusToInProgress
    // RULE: if not archived
    public Integer revokeEmployeeSignature(String uuid);

    // FIXME: owner
    // -SUPERVISOR ***
    // RULE: if signed by employee
    // RULE: if not archived
    public Integer signAsSupervisor(String uuid);

    // FIXME: owner
    // -SUPERVISOR ***
    // TASK: setStatusToInProgress
    // RULE: if not archived
    public Integer requestChanges(String uuid);

    // -SECRETARY
    // RULE: if signed by supervisor
    public Integer setStatusToArchived(String uuid);

    // -SECRETARY
    public void print();

}
