import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        String archivo = "diccionario.txt";
        String cadena;
        int contador = 1;
        BinaryTree<Association> diccionario = new BinaryTree<>();

        //Para leer el archivo
        try{
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine()) != null) {
                String[] temp = cadena.split("");
                ArrayList<String> t = new ArrayList<>();
                String a = "";
                for (int i = 0; i < temp.length; i++) {
                    if (!temp[i].equals(" ") && !temp[i].equals("(") && !temp[i].equals(",") && !temp[i].equals(")")) {
                        a += temp[i];
                    }
                    if (temp[i].equals(",") || temp[i].equals(")")) {
                        t.add(a);
                        a = "";
                    }
                }
                if (contador == 1) {
                    BinaryTree tempo = new BinaryTree(new Association<>(t.get(0), t.get(1)));
                    diccionario = tempo;
                    contador=2;
                } else {


                    Association<String, String> temporal = new Association<>(t.get(0), t.get(1));
                    BinaryTree temp0 = diccionario;

                    boolean cond = true;
                    while (cond) {
                        if ((temp0.val.toString().compareTo(temporal.toString())) >= 0) {
                            if (temp0.left.val == null) {
                                temp0.setLeft(new BinaryTree<>(temporal));
                                cond = false;
                            } else {
                                temp0 = temp0.left;
                            }
                        } else {
                            if (temp0.right.val == null) {
                                temp0.setRight(new BinaryTree<>(temporal));
                                cond = false;
                            } else {
                                temp0 = temp0.right;
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("No se encontro el archivo");
        }
        System.out.println("Los valores del diccionario son (en inOrder): \n");
        System.out.println(diccionario.inorder(diccionario));

        try{
            String sCadena;
            BufferedReader bf = new BufferedReader(new FileReader("texto.txt"));
            while ((sCadena = bf.readLine())!=null) {
                System.out.println("La oracion original es: \n"+sCadena);
                System.out.println("\nLa oracion traducida es: ");
                String[] splt = sCadena.split(" ");
                for (int i = 0; i<splt.length;i++){
                    System.out.print(diccionario.buscar(splt[i])+" ");
                }
            }
        }catch(Exception e){

        }


    }
}
