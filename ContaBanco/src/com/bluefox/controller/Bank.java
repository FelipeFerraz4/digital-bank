package com.bluefox.controller;

import java.util.HashSet;
import java.util.Set;

import com.bluefox.exception.bank.ElementNotFindException;
import com.bluefox.exception.bank.EmptyCustomerBankException;
import com.bluefox.exception.bank.IncompatiblePasswordException;
import com.bluefox.model.account.Account;
import com.bluefox.model.account.CurrentAccount;
import com.bluefox.model.user.Client;

public class Bank {
    private Set<Client> clients;

    public Bank() {
        this.clients = new HashSet<>();
    }

    public void addClient(String name, String password, String cpf) {
        Client client = new Client(name, password, cpf);

        Account account = new CurrentAccount();
        client.addAccount(account);
        
        clients.add(client);
    }

    public Client checkClient(String cpf, String password) throws EmptyCustomerBankException, ElementNotFindException, IncompatiblePasswordException {
        if (clients.isEmpty()) {
            throw new EmptyCustomerBankException();
        }

        Client clientToCheck = null;
        for (Client client : clients) {
            if (client.equals(clientToCheck)) {
                clientToCheck = client;
            }
        }

        if (clientToCheck == null) {
            throw new ElementNotFindException("Element Not Find in Customer Bank Set");
        }

        if (!clientToCheck.getPassword().equals(password)) {
            throw new IncompatiblePasswordException("Incompatible Client Password");
        }

        return clientToCheck;
    }
    
}
