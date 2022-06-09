package at.spengergasse.graphenprogramm.viewmodel;

import at.spengergasse.graphenprogramm.Xzep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphViewModel {

    public GraphViewModel(){}



    /* public static void importCsv(String filename) throws Xzep {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int limiter = 0;
            line = br.readLine();
            while (line != null){

                limiter++;
                line = br.readLine();
            }
            System.out.println("Limiter Counter: " +limiter);

            //br.reset();
            line = br.readLine();
            int [][] importMatrix = new int[limiter][limiter];
            int row = 0;
            while (line != null){
                //parse lines into 2-dim. int array
                String[] data = line.split(";");
                for (int column = 0; column < limiter; column++){
                    importMatrix[row][column] = Integer.parseInt(data[column]);
                }
                row++;
                line = br.readLine();
            }
            br.close();
            fr.close();
            Graph g = new Graph(importMatrix);
            System.out.println("Import End ______");
            System.out.println(g.toString());
        } catch (FileNotFoundException e) {
            throw new Xzep("- file not found xzep");
        } catch (IOException e) {
            throw new Xzep("- file could not be read");
        }

    } */
}
