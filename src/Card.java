public class Card{
    public String value;

    public Card(String v){
        value = v;
    }

    public int getMoveDistance(){
        switch(value){
            case "1": return 1;
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "7": return 7;
            case "8": return 8;
            case "10": return -1;
            case "11": return 11;
            case "12": return 12;
            case "S": return 0;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Card   Value: %s", value);
    }
}