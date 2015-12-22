package calculator;

/**
 * Created by VeraL on 21.12.2015.
 */
public class Calculator {
    public static final int CONVERTER_SQRMM = 1000000;
    private DBData dbData;

    private Wardrobe wardrobe = new Wardrobe();
    Model model = new Model();
    public double calculateBoard(int height, int length, int width, int doors, int shelf,
                                 int rod, int pantograph, int hanger,
                                 int box, int roof, int bottom, int rearPanel, int radius,
                                 String flakeboard, int margin) {

        int totalRackNumber = doors + 1;
        int innerRackNumber = totalRackNumber - wardrobe.SIDE_RACK_NUMBER;
        int innerRackArea = height * (width - wardrobe.FLAKEBOARD_WIDTH) * innerRackNumber;
        int sideRackArea = height * width * wardrobe.SIDE_RACK_NUMBER;
        int shelfLength = (length - (totalRackNumber * wardrobe.FLAKEBOARD_WIDTH)) / doors;


        //Flakeboard price dependency
        int totalRackArea = sideRackArea + innerRackArea;
        double totalShelfArea = shelf * shelfLength * (width - wardrobe.FLAKEBOARD_WIDTH);
        double radiusElementArea = radius*((height * width) + (wardrobe.RADIUS_SH_NUMBER * (width * wardrobe.RADIUS_EL_WIDTH)));
        double roofArea = roof * height * width;
        double bottomArea = bottom * height * width;

        double flakeboardElArea = (totalRackArea + totalShelfArea + radiusElementArea + roofArea + bottomArea) / CONVERTER_SQRMM;
        double flakeboardPrice = dbData.checkFlakeboardPrice(flakeboard);

        double price1 = flakeboardElArea * flakeboardPrice;

        //furniture and additional elements cost calculation, getting the data from DB and controller
        double rodPrice = rod * dbData.checkRodPrice();
        double pantographPrice = pantograph * dbData.checkPantographPrice();
        double hangerPrice = hanger * dbData.checkHangerPrice();
        double boxPrice = box * dbData.checkBoxPrice();

        double price2 = rodPrice + pantographPrice + hangerPrice + boxPrice;

        double rearPanelArea = (height * length) / CONVERTER_SQRMM;
        double price3 = rearPanel * rearPanelArea * dbData.checkFiberboardPrice();

        double marginRate = dbData.checkMarginRate(margin);

        double finalPrice = (price1 + price2 + price3) * marginRate;
        return finalPrice;
    }


    public double calculateFacade(int height, int length, int doors, int margin, String facade) {

        double rearPanelArea = (height * length) / CONVERTER_SQRMM;
        double facadePrice = dbData.checkFacadePrice(facade) * rearPanelArea;
        double marginRate = dbData.checkMarginRate(margin);
        double profilePrice = dbData.checkProfilePrice() * doors;

        double finalPrice = (facadePrice + profilePrice) * marginRate;
        return finalPrice;
    }
}
