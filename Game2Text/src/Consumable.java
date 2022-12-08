public class Consumable implements ConsumableInterface{
    String classType;       //创建类型：食物或药品
    int amount;             //创建数量
    //创建消耗品属性
    public Consumable(String classType, int amount){
        this.classType = classType;
        this.amount = amount;
    }

    public String getClassType(){
        return classType;
    }

    public int getAmount(){
        return amount;
    }

    public void gainAmount(int gainAmount){
        amount = amount + gainAmount;
    }

    public void looseAmount(int looseAmount){
        amount = amount - looseAmount;
    }
}
