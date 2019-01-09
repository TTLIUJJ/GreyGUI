package graGUI;

public enum Model {
    NONE(0, "选择灰色关联度算法"),
    Traditional(1, "传统灰色关联度算法"),
    General(2, "广义灰色关联度算法"),
    Dynamic(3, "动态灰色关联度算法"),
    Shannon(4, "信息熵灰色关联度算法");

    private int index;
    private String desc;

    private Model(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }
    public String getDesc() { return desc; }
}
