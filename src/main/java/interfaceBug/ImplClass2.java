package interfaceBug;

public class ImplClass2 implements TestInterface {

    public void init(){
        attributeClass.setAttribute(2);
    }


    @Override
    public AttributeClass getAttributeClass() {
        return attributeClass;
    }
}
