package lazy.rawsky.module.startinv.utils;

public enum EnumArmorType {
    head(3),
    chest(2),
    legs(1),
    feet(0);

    int slotID;

    EnumArmorType(int slotID){
        this.slotID = slotID;
    }

    public static EnumArmorType fromString(String id){
        switch (id){
            case "head":
                return head;
            case "chest":
                return chest;
            case "legs":
                return legs;
            case "feet":
                return feet;
        }
        throw new RuntimeException("The given id doesn't correspond to any armor type.");
    }

    public int getSlotID(){
        return slotID;
    }
}
