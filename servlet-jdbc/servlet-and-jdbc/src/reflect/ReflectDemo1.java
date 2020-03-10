package reflect;

import org.w3c.dom.ls.LSOutput;

public class ReflectDemo1 {
    public static void main(String[] args){
        //1.Class.forName('全类名')
        try {
            Class cls1 = Class.forName("reflect.Person");
            System.out.println(cls1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
