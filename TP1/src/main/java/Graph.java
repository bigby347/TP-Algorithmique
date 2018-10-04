import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label> {

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source,dest,label));
    }

    public String toString() {
        String result = new String("");
        result = result.concat(cardinal + "\n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(e.source + " " + e.destination + " "
                        + e.label.toString() + "\n");
            }
        }
        return result;

    }

    public interface ArcFunction<Label,K> {
        public K apply(int source, int dest, Label label, K accu);
    }

    public interface ArcConsumer<Label> {
        public void apply(int source, int dest, Label label);
    }

    public <K> K foldEdges(ArcFunction<Label,K> f, K init) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                init = f.apply(e.source, e.destination, e.label, init);
            }
        };
        return init;
    }

    public void iterEdges(ArcConsumer<Label> f) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                f.apply(e.source, e.destination, e.label);
            }
        }
    }

    static final int BLANC=0,GRIS=1,NOIR=2;

    public int[][] parcoursProfondeur(){
        System.out.println("test");
        int c=0;
        int s=0;
        boolean verif=false;
        int a=0;
        int dateFin=0;
        Stack sEnCours = new Stack();
        int[][] resultat = new int[order()*2][2];
        sEnCours.push(s);
        while(c<(order()*2-1)){
            while(verif==false){
                System.out.println(s);
                s=incidency.get(s).get(a).destination;
                verif=true;
                for(int i=0;i<sEnCours.size();i++){
                    if(s==sEnCours.getValue(i)){
                        verif=false;
                    }
                }
                for(int i=0;i<resultat.length;i++){
                    if(s==resultat[i][0]){
                        verif=false;
                    }
                }
                if(verif==false){
                    if(a<incidency.get(sEnCours.peek()).size()-1){
                        a+=1;
                    }else{
                        resultat[c][0]=sEnCours.peek();
                        sEnCours.pop();
                        resultat[c][1]=dateFin;
                        dateFin+=1;
                        c+=1;
                        int j=0;
                        int i=0;
                        while(sEnCours.size()==0 || i!=incidency.size()*2-1) {
                            if(j!=resultat[i][0]){
                                sEnCours.push(j);
                            }else if(j==resultat[i][0] && i<(resultat.length-1)){
                                i+=1;
                            }else {
                                i=0;
                                j=0;
                            }
                        }
                    }
                    verif=true;
                }
            }
            verif=false;
            sEnCours.push(s);
            dateFin+=1;
        }
        return resultat;
    }

    public void affTab(int tab[][]){
        for (int i=0;i<tab.length;i++){
            for (int j=0;j<2;j++){
                System.out.print(tab[i][j]);
            }
            System.out.println();
        }
    }
}
