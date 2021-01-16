package com.htn.legitrack;

public class Bill {
    public String id; // Open states id, never display!

    public String time; // Open States time for "latest action date"

    public String action; // What did the legislators actually do on the bill?

    public String title;

    public String summary; // abstract, java doesn't like the word 'abstract'

    public String[] subjects; // could be empty

    public String legislature; // legislature where bill is presented

    public String publicID; // The actual government ID of the bill

    public String[] sources; // URLs for source docs

    public Bill() {
        
    }
}
