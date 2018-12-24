package gui;

public enum  NondimensionType {
    NONE(0, "选择无量纲化算法"),
    Standardization(1, "标准化法"),
    ExtremeDifference(2, "极差化法"),
    Linearity(3, "线性比例法"),
    Normalization(4, "归一化法"),
    Vector(5, "向量规范法");

    private int index;
    private String desc;

    private NondimensionType(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }
    public String getDesc() { return desc; }

}
