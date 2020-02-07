package Data.Person;

import javafx.beans.property.StringProperty;

public abstract class Person  {

    private StringProperty name;

    abstract public StringProperty getName();
    abstract public void setName();
}
