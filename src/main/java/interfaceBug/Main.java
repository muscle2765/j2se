package interfaceBug;

/**
 * redis CRUD
 * @author zhuhongjiang
 */
public class Main {

    public static void main(String[] args){

        ImplClass1 class1 = new ImplClass1();
        class1.init();

        ImplClass2 class2 = new ImplClass2();
        class2.init();

        ImplClass3 class3 = new ImplClass3();
        class3.init();

        System.out.println(class1.getAttributeClass().getAttribute());

        System.out.println(class2.getAttributeClass().getAttribute());

        System.out.println(class3.getAttributeClass().getAttribute());

    }

}
