package interfaceBug;

public class ImplClass3 implements TestInterface {

    public void init(){
        attributeClass.setAttribute(3);
    }


    @Override
    public AttributeClass getAttributeClass() {
        return attributeClass;
    }
}
