package Data.Person;

import javafx.beans.property.StringProperty;

public abstract class Person  {

    private String name;

    abstract public String getName();
    abstract public void setName();
}
