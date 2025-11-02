package org.calculator.materialprice.domain;

public class MetalProduct {
    public String name;
    public String size;
    public String steelGrade;
    public String comment;

    @Override
    public String toString() {
        return "MetalProduct{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", steelGrade='" + steelGrade + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
