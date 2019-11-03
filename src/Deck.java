import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

    public class Deck {
        public final int NUM_CARDS = 45;
        public final int NUM_1_CARD = 5;
        public final int NUM_2_CARD = 4;
        public final int NUM_3_CARD = 4;
        public final int NUM_4_CARD = 4;
        public final int NUM_5_CARD = 4;
        public final int NUM_7_CARD = 4;
        public final int NUM_8_CARD = 4;
        public final int NUM_10_CARD = 4;
        public final int NUM_11_CARD = 4;
        public final int NUM_12_CARD = 4;
        public final int NUM_S_CARD = 4;
        public Card[] cards;
        public Stack<Card> drawStack;


    public Deck(){
        cards = new Card[NUM_CARDS];
        for(int i = 0; i < NUM_CARDS; i++){
            if(i < NUM_1_CARD){
                cards[i] = new Card("1");
            }else if(i < NUM_1_CARD + NUM_2_CARD){
                cards[i] = new Card("2");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD){
                cards[i] = new Card("3");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD){
                cards[i] = new Card("4");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD +  NUM_5_CARD){
                cards[i] = new Card("5");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD +  NUM_5_CARD + NUM_7_CARD){
                cards[i] = new Card("7");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD +  NUM_5_CARD + NUM_7_CARD + NUM_8_CARD){
                cards[i] = new Card("8");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD +  NUM_5_CARD + NUM_7_CARD + NUM_8_CARD + NUM_10_CARD){
                cards[i] = new Card("10");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD + NUM_5_CARD +
                    NUM_7_CARD + NUM_8_CARD + NUM_10_CARD + NUM_11_CARD){
                cards[i] = new Card("11");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD + NUM_5_CARD +
                    NUM_7_CARD + NUM_8_CARD + NUM_10_CARD + NUM_11_CARD + NUM_12_CARD){
                cards[i] = new Card("12");
            }else if(i < NUM_1_CARD + NUM_2_CARD + NUM_3_CARD + NUM_4_CARD +   NUM_5_CARD +
                    NUM_7_CARD + NUM_8_CARD + NUM_10_CARD + NUM_11_CARD + NUM_12_CARD + NUM_S_CARD){
                cards[i] = new Card("S");
            }
        }
        drawStack = new Stack<Card>();
        shuffleDeck();
    }

    public Card drawCard(){
        Card c = drawStack.pop();
        if(drawStack.empty()){
            shuffleDeck();
        }
        return c;
    }

    public void shuffleDeck(){
        int[] drawdeck = new int[NUM_CARDS - 1];
        Arrays.setAll(drawdeck, i -> i + 1);
        shuffleArray(drawdeck);
        for(int i = 0; i < NUM_CARDS - 1; i++){
            drawStack.push(cards[drawdeck[i]]);
        }
    }

    @Override
    public String toString(){
        String n = "";
        for(int i = 0; i < cards.length; i++){
            n = n + cards[i].toString() + "\n";
        }
        return String.format("Cards %s", n);
    }

    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
