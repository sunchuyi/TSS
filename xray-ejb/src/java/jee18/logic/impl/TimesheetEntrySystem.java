/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jee18.logic.impl;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import jee18.dao.TimesheetEntryAccess;
import jee18.dto.Timesheet;
import jee18.dto.TimesheetEntry;
import jee18.entities.TimesheetEntryEntity;
import jee18.entities.enums.ContractStatus;
import jee18.entities.enums.TimesheetStatus;
import jee18.logic.AbstractTimesheetSystem;
import jee18.logic.ITimesheetEntrySystem;
import jee18.logic.ITimesheetSystem;

/**
 *
 * @author okaracalik
 */
@Stateless(name = "TimesheetEntrySystem")
public class TimesheetEntrySystem extends AbstractTimesheetSystem<TimesheetEntry, TimesheetEntryEntity> implements ITimesheetEntrySystem {

    @EJB
    private TimesheetEntryAccess timesheetEntryAccess;

    @EJB
    private ITimesheetSystem timesheetSystem;

    public TimesheetEntrySystem() throws NamingException {
        super("TimesheetEntryAccess");
    }

    @Override
    protected TimesheetEntryEntity convertToEntity(TimesheetEntry te) {
        return TimesheetEntry.toEntity(te);
    }

    @Override
    protected TimesheetEntry convertToObject(TimesheetEntryEntity tee) {
        return TimesheetEntry.toDTO(tee);
    }

    @RolesAllowed({"SECRETARY", "SUPERVISOR", "ASSISTANT", "EMPLOYEE"})
    @Override
    public List<TimesheetEntry> list() {
        return super.getList();
    }
    
    @RolesAllowed("EMPLOYEE")
    @Override
    public List<TimesheetEntry> listMyTimesheetEntries(String employeeUuid) {
        return super.getList();
    }

    @RolesAllowed("EMPLOYEE")
    @Override
    public TimesheetEntry add(TimesheetEntry te, String timesheetUuid) {
        Timesheet t = timesheetSystem.get(timesheetUuid);
        if (t.getStatus() == TimesheetStatus.IN_PROGRESS && t.getContract().getStatus() == ContractStatus.STARTED) {
            TimesheetEntryEntity tee = timesheetEntryAccess.createWithTimesheet(TimesheetEntry.toEntity(te), timesheetUuid);
            return TimesheetEntry.toDTO(tee);
        }
        else {
            throw new EJBException("Timesheet Entry can only be added when timesheet is in progress and contract was started.");
        }
    }

    @RolesAllowed({"SECRETARY", "SUPERVISOR", "ASSISTANT", "EMPLOYEE"})
    @Override
    public TimesheetEntry get(String uuid) {
        return super.getByUuid(uuid);
    }

    @RolesAllowed("EMPLOYEE")
    @Override
    public Integer update(String uuid, TimesheetEntry te) {
        if (te.getTimesheet().getStatus() == TimesheetStatus.IN_PROGRESS && te.getTimesheet().getContract().getStatus() == ContractStatus.STARTED) {
            return super.updateByUuid(uuid, te);
        }
        else {
            throw new EJBException("Timesheet Entry can only be added when timesheet is in progress and contract was started.");
        }
    }

    @RolesAllowed("EMPLOYEE")
    @Override
    public Integer delete(String uuid) {
        TimesheetEntry te = super.getByUuid(uuid);
        if (te.getTimesheet().getStatus() == TimesheetStatus.IN_PROGRESS && te.getTimesheet().getContract().getStatus() == ContractStatus.STARTED) {
            return super.deleteByUuid(uuid);
        }
        else {
            throw new EJBException("Timesheet Entry can only be added when timesheet is in progress and contract was started.");
        }
    }

}
