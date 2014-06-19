package projetoMultiAgentes;
import jade.core.Agent;
//Imports
///////////////
import jade.core.AID;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Profile;

import jade.wrapper.PlatformController;
import jade.wrapper.AgentController;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.DFService;
import jade.domain.FIPAException;

import javax.swing.*;
import java.util.*;
import java.text.NumberFormat;

public class Bot extends Agent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8607914159051816978L;

	private int life = 100;
	
	
	protected void setup(){
		
		        
        try {

    		System.out.println( getLocalName() + " setting up");
    		System.out.println("Entrando no jogo");

            // create the agent descrption of itself
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName( getAID() );

			DFService.register( this, dfd );
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


			}
	
	
	
}


