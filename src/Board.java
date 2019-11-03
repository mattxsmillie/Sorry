public class Board {
    public final int NUM_SPACES = 56;
    public final int NUM_PIECES = 4;
    public final int NUM_HOMESPACES = 6;
    public Space[] spaces;
    public Space startspace;
    public Space[] homespace;
    public Piece[] pieces;

    public Board(){
        //Create spaces around board
        spaces = new Space[NUM_SPACES];
        for (int i = 0; i < NUM_SPACES; i++){
            if(i==5 || i == 11 || i== 19  || i == 25 || i == 33 || i == 39 || i == 47 || i == 53){
                spaces[i] = new Space(true, false, false, 1);
            }else {
                spaces[i] = new Space(false, false, false,1);
            }
        }
        //create game pieces
        pieces = new Piece[NUM_PIECES];
        for (int i = 0; i < NUM_PIECES; i++){
            pieces[i] = new Piece("Green", i);
        }

        //create homespace
        homespace = new Space[NUM_HOMESPACES];
        for(int i = 0; i < NUM_HOMESPACES; i++){
            homespace[i] = new Space(false, false, true, NUM_PIECES);
        }
        //create startspaces
        startspace = new Space(false,false,true,NUM_PIECES);
        //populate startspace


    }

    public void move(Card c){

    }

    public String printBoardState(){
        for(int i = 0; i <1; i++){

        }
        return String.format("%s", "Boardstate:");
    }

    private class Space{
        public int number;
        public boolean slider; // true if sends player 5 forward, false if not
        public int[] occupyingpiece; //an array storing the indicies of pieces
        public boolean occupied;
        public boolean shareable;

        public Space(boolean Sliderstate, boolean Occupied, boolean Shareable, int spaces){
            slider = Sliderstate;
            occupied = Occupied;
            shareable = Shareable;
            if(Shareable) {
                occupyingpiece = new int[spaces];
                for (int i = 0; i < spaces; i++) {
                    occupyingpiece[i] = -1;
                }
            }else{
                occupyingpiece = new int[1];
                occupyingpiece[0]=-1;
            }
        }

        public int addPiece(Piece p){
            if(shareable){
                for(int i = 0; i < occupyingpiece.length; i++){
                    if(occupyingpiece[i] == -1){
                        occupyingpiece[i] = p.num;
                        return -1;
                    }
                }
            }else{
                //send original piece back to start
                int old = occupyingpiece[0];
                occupyingpiece[0] = p.num;
                return old;
            }
            return -1;
        }

        public int removePiece(Piece P){
            return -1;
        }


    }

    private class Piece{
        public String color;
        public int num;
        public Piece(String Color, int Num){
            color = Color;
            num = Num;
        }
    }


}


