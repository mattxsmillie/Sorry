public class Board {
    public final int NUM_SPACES = 57;
    public final int NUM_PIECES = 4;
    public final int NUM_HOMESPACES = 6;
    public Space[] spaces;
    public Space startspace;
    public Space[] homespaces;
    public Piece[] pieces;

    public Board(){
        //Create spaces around board
        spaces = new Space[NUM_SPACES];
        for (int i = 1; i < NUM_SPACES; i++){
            if(i==6 || i == 12 || i== 20  || i == 26 || i == 34 || i == 40 || i == 48 || i == 54){
                spaces[i] = new Space(i,true, false, false, 1);
            }else {
                spaces[i] = new Space(i,false, false, false,1);
            }
        }
        //create game pieces
        pieces = new Piece[NUM_PIECES];
        for (int i = 0; i < NUM_PIECES; i++){
            pieces[i] = new Piece("Green", i);
        }

        //create homespace
        homespaces = new Space[NUM_HOMESPACES];
        for(int i = 0; i < NUM_HOMESPACES; i++){
            homespaces[i] = new Space(57,false, false, true, NUM_PIECES);
        }
        //create startspaces
        startspace = new Space(0,false,false,true,NUM_PIECES);
        //populate startspace


    }

    public void move(Card c){

    }

    @Override
    public String toString(){
        String h = "";
        for(int i = 0; i <NUM_HOMESPACES; i++){
            h = h + homespaces[i].toString() + "\n";
        }
        String s = "";
        for(int i = 1; i <NUM_SPACES; i++){
            s = s + spaces[i].toString() + "\n";
        }
        return String.format("Start space: %n%s%n" +
                            "Board Spaces: %n%s%n" +
                            "Home Spaces: %n%s%n", startspace.toString(), s, h);
    }

    private class Space{
        public int number;
        public boolean slider; // true if sends player 5 forward, false if not
        public int[] occupyingpiece; //an array storing the indicies of pieces occupying space. -1 if false.
        public boolean occupied;
        public boolean shareable;

        public Space(int num, boolean Sliderstate, boolean Occupied, boolean Shareable, int spaces){
            number = num;
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

        public String toString(){
            String p = "";
            for (int i= 0; i < occupyingpiece.length; i ++){
                if(occupyingpiece[i] != -1){
                    p = p + pieces[occupyingpiece[i]].toString() + " ";
                }
            }
            return String.format("Space: %d%s%s%s%s", number,
                    slider ? ", Slider Space" : "",
                    occupied ? ", Occupied" : "",
                    shareable ? ", Shareable": "",
                    p);
        }
    }

    private class Piece{
        public String color;
        public int num;
        public Piece(String Color, int Num){
            color = Color;
            num = Num;
        }

        @Override
        public String toString(){
            return String.format("Piece #%n, Color: %n", num, color);
        }
    }


}


