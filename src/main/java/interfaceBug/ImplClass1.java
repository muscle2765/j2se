package interfaceBug;

public class ImplClass1 implements TestInterface {

    public void init(){
        attributeClass.setAttribute(1);
    }


    @Override
    public AttributeClass getAttributeClass() {
        return attributeClass;
    }
}
