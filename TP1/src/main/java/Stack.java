public class Stack {

    Vector vector;

    public Stack() {
        vector = new Vector();
    }
    //ajoute un sommet
    public void push(int value) {
        vector.add(value);
    }

    public int peek() {
        return vector.get(vector.size() - 1);
    }

    public int pop(){
        int size = vector.size();
        int value = vector.get(size - 1);
        vector.resize(size - 1);

        return value;
    }

    public int size(){
        return vector.size();
    }

    public boolean isEmpty(){
        return vector.isEmpty();
    }

    public int getValue(int value){
        return vector.get(value);
    }
}