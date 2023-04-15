package O;

import java.util.ArrayList;
import java.util.Random;

public class Populacao {

    private static final Random generator = new Random(0);
    private final ArrayList<Trajectory> trajetorias;

    public Populacao(ArrayList<int[]> traj) {
        trajetorias = new ArrayList<>();

        for (int i = 0; i < traj.size(); i++) {
            ArrayList<Ponto> pontos = new ArrayList<>();
            int[] points = traj.get(i) 3;
            for (int j = 0; j < points.length - 1; j += 2) {
                int x = points[j];
                int y = points[j + 1];
                Ponto p1 = new Ponto(x, y);
                pontos.add(p1);
            }
            trajetorias.add(new Trajectory(pontos));
        }
    }

    private ArrayList<Ponto> creating(ArrayList<Ponto> x, ArrayList<Ponto> y) {
        ArrayList<Ponto> result = new ArrayList<>();
        result.addAll(x);
        result.addAll(y);
        for (int i = 0; i < result.size() - 1; i++) {
            if (Ponto.iseqauls(result.get(i), result.get(i + 1))) {
                result.remove(i);
            }
        }
        return result;
    }

    private ArrayList<Ponto> addiction(ArrayList<Ponto> x, int start, int end) {
        ArrayList<Ponto> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(x.get(i));
        }
        return result;
    }

    public void cut() {

        int size = trajetorias.get(0).getpontos().size();
        int size_1 = trajetorias.get(1).getpontos().size();
        int c = (size == 0) ? 1 : (generator.nextInt(size - 1) + 1);
        int d = (size_1 == 0) ? 1 : (generator.nextInt(size_1 - 1) + 1);
        ArrayList<Ponto> a = addiction(trajetorias.get(0).getpontos(), 0, c);
        ArrayList<Ponto> a_1 = addiction(trajetorias.get(0).getpontos(), c, size);
        ArrayList<Ponto> b = addiction(trajetorias.get(1).getpontos(), 0, d);
        ArrayList<Ponto> b_1 = addiction(trajetorias.get(1).getpontos(), d, size_1);


        trajetorias.get(0).getpontos().clear();
        trajetorias.get(0).getpontos().addAll(creating(a, b_1));

        trajetorias.get(1).getpontos().clear();
        trajetorias.get(1).getpontos().addAll(creating(b, a_1));
    }

    public static void mut(Populacao x, double prob, int[] sizes) {
        int size = sizes.length;
        for (int i = 0; i < size; i++) {
            if (sizes[i] > 2) {
                double r = generator.nextDouble(1);
                int size_traj = sizes[i] - 1;

                if (r < prob) {

                    int place = generator.nextInt(size_traj) + 1;
                    if (place == 0) {
                        place = 1;
                    }
                    Ponto replace = random_point_creation(generator.nextInt(100), generator.nextInt(100));
                    if (x.trajetorias.get(i).getpontos().contains(replace)) {
                        continue;
                    }
                    x.trajetorias.get(i).getpontos().remove(place);
                    x.trajetorias.get(i).getpontos().add(place, replace);
                }
            }
        }
    }

    public static void adiction_random(M.Populacao x, double prob, int[] sizes) {
        int size = sizes.length;
        for (int i = 0;i<size;i++){
            double r = generator.nextDouble(1);
            int size_traj = sizes[i];
            int place;
            if (r < prob) {
                if (size_traj>2){
                    place = generator.nextInt(size_traj) + 1;
                }else{
                    place = 1;
                }


                M.Ponto point_to_add = random_point_creation(generator.nextInt(100), generator.nextInt(100));
                if (x.trajetorias.get(i).getpontos().contains(point_to_add)) {
                    continue;
                }

                x.trajetorias.get(i).getpontos().add(place, point_to_add);
            }
        }
    }

    public static void Remove_random(N.Populacao x, double prob, int[] sizes) {
        for (int i = 0;i<sizes.length;i++) {
            double r = generator.nextDouble();
            if (r<prob && sizes[i]>2){
                int place = generator.nextInt(sizes[i] - 2) + 1;
                x.trajetorias.get(i).getpontos().remove(place);
            }
        }
    }
}
