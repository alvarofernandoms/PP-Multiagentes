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
	
	public final static String HELLO = "HELLO";
    public final static String ANSWER = "ANSWER";
    public final static String THANKS = "THANKS";
    public final static String GOODBYE = "GOODBYE";
    public final static String INTRODUCE = "INTRODUCE";
    public final static String RUMOUR = "RUMOUR";
    
    protected JFrame m_frame = null;
    protected Vector m_guestList = new Vector();    // invitees
    protected int m_guestCount = 0;                 // arrivals
    protected int m_rumourCount = 0;
    protected int m_introductionCount = 0;
    protected boolean m_partyOver = false;
    protected NumberFormat m_avgFormat = NumberFormat.getInstance();
    protected long m_startTime = 0L;
    
	protected void setup(){
		
		        
        try {

    		System.out.println( getLocalName() + " setting up");
    		System.out.println("Entrando no jogo");

            // create the agent description of itself
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName( getAID() );

			DFService.register( this, dfd );
			
			
			addBehaviour( new CyclicBehaviour( this ) {
				
				public void action() {
					ACLMessage msg = receive();
				}
			
			});
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


			}
	
    protected void beginConversation() {
        // start a rumour
        ACLMessage rumour = new ACLMessage( ACLMessage.INFORM );
        rumour.setContent( RUMOUR );
        rumour.addReceiver( randomGuest( null ) );
        send( rumour );

        // introduce two agents to each other
        doIntroduction( randomGuest( null ) );
        setPartyState( "Swinging" );
    }


    protected AID randomGuest( AID aGuest ) {
        AID g = null;

        do {
            int i = (int) Math.round( Math.random() * (m_guestList.size() - 1) );
            g = (AID) m_guestList.get( i );
        } while ((g!=null) && g.equals(aGuest));

        return g;
    }
    
    protected void doIntroduction( AID guest0 ) {
        if (!m_partyOver) {
            AID guest1 = randomGuest( guest0 );

            // introduce two guests to each other
            ACLMessage m = new ACLMessage( ACLMessage.INFORM );
            m.setContent( INTRODUCE + " " + guest0.getName() );
            m.addReceiver( guest1 );
            send( m );

            // update the count of introductions on the UI
            m_introductionCount++;
            SwingUtilities.invokeLater( new Runnable() {
                                            public void run() {
//                                                ((HostUIFrame) m_frame).lbl_numIntroductions.setText( Integer.toString( m_introductionCount ));
                                            }
                                        } );
            updateRumourAvg();
        }
    }
    
    protected void setPartyState( final String state ) {
        SwingUtilities.invokeLater( new Runnable() {
                                        public void run() {
//                                            ((HostUIFrame) m_frame).lbl_partyState.setText( state );
                                        }
                                    } );
    }
    
    protected void updateRumourAvg() {
        SwingUtilities.invokeLater( new Runnable() {
                                        public void run() {
//                                            ((HostUIFrame) m_frame).lbl_rumourAvg.setText( m_avgFormat.format( ((double) m_introductionCount) / m_rumourCount ) );
                                        }
                                    } );
    }


    
}


