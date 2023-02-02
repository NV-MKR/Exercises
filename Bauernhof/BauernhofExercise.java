
class Farm {
    public static void main(String[] args) {

        getExplanationOfGame();

    }

    public static void getExplanationOfGame() {
        String explanation = "";

        explanation += "";

        System.out.println(explanation);

    }

}

class Cow {

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() <= 2) {
            System.out.println("Der Name muss mindestens 3 Buchstaben haben.");
        }
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
