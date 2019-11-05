public class Board {
    public final int NUM_SPACES = 60;
    public final int NUM_PIECES = 4;
    public final int NUM_HOMESPACES = 6;
    public Space[] spaces; //spaces are numbered from 1, not 0
    public Space startspace;
    public Space[] homespaces;
    public Piece[] pieces;

    public Board(){
        //Create spaces around board
        spaces = new Space[NUM_SPACES + 1];
        for (int i = 1; i < NUM_SPACES + 1; i++){
            if(i==6 || i== 21  || i == 36 ||  i == 51){
                spaces[i] = new Space(i,4, false, 1);
            }else if(i == 13 || i == 28 || i == 43 || i== 58){
                spaces[i] = new Space(i,3, false, 1);
            }else {
                spaces[i] = new Space(i,0, false,1);
            }
        }

        //create homespace
        homespaces = new Space[NUM_HOMESPACES];
        for(int i = 0; i < NUM_HOMESPACES; i++){
            homespaces[i] = new Space(61 + i,0, true, NUM_PIECES);
        }
        //create startspaces
        startspace = new Space(0,0, true,NUM_PIECES);
        //populate startspace

        //create game pieces, place on start
        pieces = new Piece[NUM_PIECES];
        for (int i = 0; i < NUM_PIECES; i++){
            pieces[i] = new Piece("Green", i);
            startspace.addPiece(pieces[i]);
        }
    }

    public int findPiece(Piece p){
        if(startspace.findPiece(p)){
            return 0;
        }else{
            for(int i = 0; i < NUM_HOMESPACES; i++){
                if(homespaces[i].findPiece(p)) {
                    return i+60;
                }
            }
            for(int i = 1; i < NUM_SPACES + 1; i++) {
                if (spaces[i].findPiece(p)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void move(Piece p, Card c){
        //find piece p  on board
        //start
        if(!homespaces[5].findPiece(p)) {
            if (startspace.findPiece(p)) {
                if (c.value.equals("1") || c.value.equals("2")) {
                    startspace.removePiece(p);
                    spaces[1].addPiece(p);
                }
            } else {
                //board
                int d = c.getMoveDistance();
                for (int i = 1; i < NUM_SPACES + 1; i++) {
                    if (spaces[i].findPiece(p)) {
                        spaces[i].removePiece(p);
                        if (i <= 59 && i + d > 59) {
                            int h = d - (59 - i);
                            if (h > 5) {
                                homespaces[5].addPiece(p);
                            } else {
                                homespaces[h].addPiece(p);
                            }
                        } else {
                            int dist;
                            if(i + d == 0){//cycle backward
                                spaces[60].addPiece(p);
                            }else if (i + d > 60) {//cycle forward
                                dist = i + d - 60;
                                spaces[(i + d + spaces[i + d - 60].sliderval) - 60].addPiece(p);
                            }else if (i + d + spaces[i + d].sliderval > 60){
                                spaces[(i + d + spaces[i + d].sliderval) - 60].addPiece(p);
                            } else {
                                    spaces[i + d + spaces[i + d].sliderval].addPiece(p);
                            }
                        }
                        break;
                    }
                }
                for (int i = 0; i < NUM_HOMESPACES - 1; i++) {
                    if (homespaces[i].findPiece(p)) {
                        homespaces[i].removePiece(p);
                        if (i + d > 5) {
                            homespaces[5].addPiece(p);
                        }else if (i + d < 0) {
                            homespaces[0].addPiece(p);
                        } else {
                            homespaces[i + d].addPiece(p);
                        }
                        break;
                    }
                }
            }
        }
        //home
        //determine space to move it to


    }

    @Override
    public String toString(){
        String h = "";
        for(int i = 0; i <NUM_HOMESPACES; i++){
            h = h + homespaces[i].toString() + "\n";
        }
        String s = "";
        for(int i = 1; i <NUM_SPACES + 1; i++){
            s = s + spaces[i].toString() + "\n";
        }
        return String.format("Start space: %n%s%n" +
                            "Board Spaces: %n%s%n" +
                            "Home Spaces: %n%s%n", startspace.toString(), s, h);
    }

    private class Space{
        public int number; //position on board.
        public int sliderval; // number of spaces forward the space sends a piece
        public int[] occupyingpiece; //an array storing the indicies of pieces occupying space. -1 if empty.
        public boolean shareable;

        public Space(int num, int snum,boolean Shareable, int spaces){
            number = num;
            sliderval = snum;
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
            if(shareable){ //place piece on the space
                for(int i = 0; i < occupyingpiece.length; i++){
                    if(occupyingpiece[i] == -1){
                        occupyingpiece[i] = p.num; //place piece there
                        return -1;
                    }
                }
            }else{
                //check if occupied
                if(occupyingpiece[0] != -1){//send original piece back to start
                    if(occupyingpiece[0] != p.num) { //ensure its not the piece itself
                        int old = occupyingpiece[0];
                        startspace.addPiece(pieces[old]);
                    }
                }
                occupyingpiece[0] = p.num;
            }
            return -1;
        }

        public int removePiece(Piece p){
            for(int i = 0; i < occupyingpiece.length; i++){
                if(occupyingpiece[i] == p.num){
                    occupyingpiece[i] = -1;
                    return p.num;
                }
            }
            return -1;
        }

        //determines whether piece is on this space or not
        public boolean findPiece(Piece p){
            for(int i = 0; i< occupyingpiece.length; i++){
                if(occupyingpiece[i] == p.num){
                    return true;
                }
            }
            return false;
        }

        public String toString(){
            String p = "";
            for (int i= 0; i < occupyingpiece.length; i ++){
                if(occupyingpiece[i] != -1){
                    p = p + pieces[occupyingpiece[i]].toString() + " ";
                }
            }
            return String.format("Space: %d Slides %d%s %s", number,sliderval,
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
            return String.format("Piece #%d, %s", num, color);
        }
    }


}


