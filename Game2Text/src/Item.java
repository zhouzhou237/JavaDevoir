public class Item {
    String weapon;              //创建武器
    Consumable consumable;      //创建消耗品

    public Item(String weapon){
        this.weapon = weapon;
    }

    public String getWeapon(){
        return weapon;
    }

    public String useWeapon(){
        return weapon;
    }
}