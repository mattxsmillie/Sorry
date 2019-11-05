public class SorryGame{
    public Board myBoard;
    public Deck myDeck;
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

        System.out.println("drawing card 1");

        myBoard.move(myBoard.pieces[0], new Card("1"));
        System.out.println(myBoard.toString());

        System.out.println("Resetting board and drawing 10 random cards for Piece 1");
        myBoard = new Board();
        for(int i = 0; i < 10;i++) {
            System.out.println("Drew Card " + myDeck.drawStack.peek().toString());
            myBoard.move(myBoard.pieces[0], myDeck.drawCard());
        }
        System.out.println(myBoard.toString());
    }
}