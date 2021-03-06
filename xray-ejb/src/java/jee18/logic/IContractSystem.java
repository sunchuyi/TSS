/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jee18.logic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import jee18.dto.Contract;
import jee18.dto.Holiday;

/**
 *
 * @author okaracalik
 */
public interface IContractSystem {

    // -SECRETARY
    // -SUPERVISOR
    // -ASSISTANT
    // -EMPLOYEE ***
    public List<Contract> listMyContracts(String emailAddress);

    // -SUPERVISOR
    // -ASSISTANT
    public Contract add(Contract c, List<String> secretartUuids, String employeeUuid, String supervisorUuid, List<String> assistantUuids);

    // -SECRETARY
    // -SUPERVISOR
    // -ASSISTANT
    // -EMPLOYEE ***
    // TASK: stats
    public Contract getMyContract(String uuid, String emailAddress);

    // -SUPERVISOR
    // -ASSISTANT
    // RULE: if prepared
    public Integer update(String uuid, Contract c, String emailAddress);

    // -SUPERVISOR
    // -ASSISTANT
    // RULE: if prepared
    // TASK: delete timesheets in progress
    public Integer delete(String uuid, String emailAddress);

    // -SUPERVISOR
    // -ASSISTANT
    // TASK: create timesheets
    public Integer setStatusToStarted(String uuid, String emailAddress);

    // -SUPERVISOR
    // -ASSISTANT
    // TASK: set termination date
    public Integer setStatusToTerminated(String uuid, String emailAddress);

    public Integer setStatusToArchived(String uuid, String emailAddress);

    // -SECRETARY
    public void print();

    public HashMap<String, Double> calculateStatistics(String uuid, String emailAddress);

}
