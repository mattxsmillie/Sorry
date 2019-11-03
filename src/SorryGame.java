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
        System.out.print(myBoard.toString());
    }
}