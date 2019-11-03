public class Card{
    public String value;

    public Card(String v){
        value = v;
    }

    @Override
    public String toString() {
        return String.format("Card   Value: %s", value);
    }
}