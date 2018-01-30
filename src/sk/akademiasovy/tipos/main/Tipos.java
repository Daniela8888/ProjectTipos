package sk.akademiasovy.tipos.main;

import java.util.Arrays;
import java.util.Random;

public class Tipos {

    private int[] arr;

    public int[] getArr() {
        return arr;
    }

    public Tipos(){
        arr= new int[5];
    }
    public void generate(){
        //generovanie 5 roznych cisel
        Random random = new Random();
        arr[0]=random.nextInt(35)+1;
        int j,i=1;
        boolean unique=true;
        while(i<5){
            arr[i]=random.nextInt(35)+1;
            unique=true;
            for(j=0;j<i;j++){
                if(arr[i]==arr[j])
                    unique=false;
            }
            if(unique==true){
                i++;
            }
        }
        Arrays.sort(arr);

    }
    public void print(){
        for(int j:arr){
            System.out.println(j+" ");

        }
    }
}
