package jspecify.example;

import org.jspecify.annotations.*;

public class SomeJavaClass {
    @NonNull
    public String foo() {return "foo";}

    @Nullable
    public String bar() {return "bar";}
}