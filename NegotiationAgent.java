import java.util.Random;

// A simple negotiation agent class
public class NegotiationAgent {

    private final int batna; // Best Alternative to a Negotiated Agreement
    private int currentOffer;

    public NegotiationAgent(int batna) {
        this.batna = batna;
    }

    // Method to evaluate an offer
    public boolean evaluateOffer(int offer) {
        System.out.println("Received offer: " + offer);
        if (offer >= batna) {
            System.out.println("Offer accepted (meets or exceeds BATNA).\n");
            return true;
        } else {
            System.out.println("Offer rejected (below BATNA).\n");
            return false;
        }
    }

    // Method to make a counteroffer
    public int makeCounterOffer() {
        Random random = new Random();
        int counterOffer = batna + random.nextInt(20) + 1; // Randomly generates a reasonable counteroffer
        System.out.println("Making counteroffer: " + counterOffer);
        currentOffer = counterOffer;
        return counterOffer;
    }

    public static void main(String[] args) {
        // Define two negotiation agents with BATNAs
        NegotiationAgent agentA = new NegotiationAgent(50);
        NegotiationAgent agentB = new NegotiationAgent(60);

        boolean agreementReached = false;
        int iteration = 0;
        int currentOffer = 70; // Initial offer by Agent A

        System.out.println("Starting negotiation:\n");
        while (!agreementReached && iteration < 10) { // Limit negotiation to 10 iterations
            iteration++;
            System.out.println("Iteration " + iteration + ":\n");

            // Agent B evaluates offer
            if (agentB.evaluateOffer(currentOffer)) {
                System.out.println("Agreement reached! Agent B accepts offer of " + currentOffer + ".");
                agreementReached = true;
                break;
            }

            // Agent B makes a counteroffer
            currentOffer = agentB.makeCounterOffer();

            // Agent A evaluates counteroffer
            if (agentA.evaluateOffer(currentOffer)) {
                System.out.println("Agreement reached! Agent A accepts counteroffer of " + currentOffer + ".");
                agreementReached = true;
                break;
            }

            // Agent A makes a counteroffer
            currentOffer = agentA.makeCounterOffer();
        }

        if (!agreementReached) {
            System.out.println("No agreement reached after 10 iterations. Negotiation ended.");
        }
    }
}
