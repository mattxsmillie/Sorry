import java.io.*;

public class SorryGame{
    public final boolean detailedDistribution = true; //special mode, removes extra distribution of end tile
    public Board myBoard;
    public Deck myDeck;
    public final int NUM_STEPS = 10000; //number of observations of each turn counted
    public final int NUM_TURNS = 55; //max number of turns
    public static void main(String[] args){
        new SorryGame();
    }
    public SorryGame(){
        myDeck = new Deck();
        System.out.print(myDeck.toString());
//        for(int i = 0; i < 60; i++){
//            System.out.println(myDeck.drawCard().toString());
//        }



        myBoard = new Board();

        System.out.println(myBoard.toString());


        /*
        System.out.println("drawing card 1");
//        myBoard.move(myBoard.pieces[0], new Card("1"));
//        myBoard.move(myBoard.pieces[0], new Card("10"));
        myBoard.move(myBoard.pieces[0], new Card("1"));
        myBoard.move(myBoard.pieces[0], new Card("12"));
        System.out.println(myBoard.findPiece(myBoard.pieces[0]));
        myBoard.move(myBoard.pieces[0], new Card("12"));
        System.out.println(myBoard.findPiece(myBoard.pieces[0]));
        myBoard.move(myBoard.pieces[0], new Card("12"));
        System.out.println(myBoard.findPiece(myBoard.pieces[0]));
        myBoard.move(myBoard.pieces[0], new Card("11"));
        System.out.println(myBoard.findPiece(myBoard.pieces[0]));
        myBoard.move(myBoard.pieces[0], new Card("2"));
        System.out.println(myBoard.findPiece(myBoard.pieces[0]));
        myBoard.move(myBoard.pieces[0], new Card("2"));
        System.out.println(myBoard.findPiece(myBoard.pieces[0]));

//        for(int i = 0; i<NUM_TURNS; i++) {
//            System.out.println(myDeck.drawStack.peek().toString());
//            myBoard.move(myBoard.pieces[0], myDeck.drawCard());
//            System.out.println(myBoard.findPiece(myBoard.pieces[0]));
//        }
        */
        System.out.println(myBoard.toString());
        System.out.println("@ @ @");
        Writer w = null;
        try {
            w = new FileWriter("output.csv");
        } catch (IOException e) {e.printStackTrace();}
        try {
            w.write("observation, turn, space\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        myBoard = new Board();
        for(int i = 1; i < NUM_TURNS; i++){ // for turn i
            System.out.println("Turn " + i);
            for(int j = 0; j < NUM_STEPS; j++){ //perform j observations: for observation j
//                System.out.println("Observation " + j);
                for(int k = 0; k < i;k++){
                    myBoard.move(myBoard.pieces[0], myDeck.drawCard());
                    for(int m = 0; m < 3; m++){myDeck.drawCard();}
                }
                int s = myBoard.findPiece(myBoard.pieces[0]);
                try {
                    w.write(String.format("%d, %d, %d %n",j, i, s));
                } catch (IOException e) {e.printStackTrace();}
                myBoard=new Board();
            }
        }
        System.out.println(myBoard.toString());
        try {
            w.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    public void writeBoardToCsv(Board b){

    }
}