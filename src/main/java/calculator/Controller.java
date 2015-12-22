package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by VeraL on 08.12.2015.
 */
public class Controller {
    private  Model model;
    private MainGson mg;
    private Wardrobe wardrobe;
    private DBData dbData;

    @FXML
    Button calcBoardPrice, currency, newCustomer;
    @FXML
    ComboBox height, length, width, doorsAmount, shelf, rod,pantograph, hanger, box,flakeboard, margin, facadeComboBox ;
    @FXML
    CheckBox roof, bottom, rearPanel, radiusElement;
    @FXML
    TextField customerName, currencyView, priceBoard, priceFacade, totalPriceSum, customerPhone, customerAdress;


   private Stage dialogStage;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    public void calcBoardPrice(ActionEvent event){
        try {
        Integer heightC = Integer.parseInt(height.getValue().toString());
        Integer lengthC = Integer.parseInt(length.getValue().toString());
        Integer widthC = Integer.parseInt(width.getValue().toString());
        Integer doorsC = Integer.parseInt(doorsAmount.getValue().toString());
        Integer shelfC = Integer.parseInt(shelf.getValue().toString());

        Integer rodC = Integer.parseInt(rod.getValue().toString());
        Integer pantographC = Integer.parseInt(pantograph.getValue().toString());
        Integer hangerC = Integer.parseInt(hanger.getValue().toString());

        Integer boxC = Integer.parseInt(box.getValue().toString());
        int roof1 = 0;
        if(roof.isSelected()){
            roof1=1;
        }

        int bottom1 = 0;
        if(bottom.isSelected()){
            bottom1 = 1;
        }

        int rearPanel1 = 0;
        if(rearPanel.isSelected()){
            rearPanel1 = 1;
        }

        int radiusElement1 = 0;
        if(radiusElement.isSelected()){
            radiusElement1 = 1;
        }

        Integer marginC = Integer.parseInt(margin.getValue().toString());
        String flakeboardC = flakeboard.getValue().toString();
        String facadeC = facadeComboBox.getValue().toString();

        double rateFromView = Double.valueOf(currencyView.getText());

        int boardPrice = (int)((model.getBoardPrice(heightC, lengthC, widthC, doorsC, shelfC,
                rodC, pantographC, hangerC, boxC, roof1, bottom1, rearPanel1, radiusElement1, flakeboardC, marginC))*rateFromView);
        String finalBoardPrice= Integer.toString(boardPrice);
        priceBoard.setText(finalBoardPrice + "грн.");

        int facadePrice = (int)((model.getFacadePrice(heightC, lengthC, doorsC, marginC, facadeC))*rateFromView);
        String finalFacadePrice= Integer.toString(facadePrice);
        priceFacade.setText(finalFacadePrice + "грн.");

        int totalPrice = facadePrice+boardPrice;
        String finalTotalPrice = Integer.toString(totalPrice);

            totalPriceSum.setText(finalTotalPrice + "грн.");
        }catch (NullPointerException e){

            e.printStackTrace();
            System.out.println("Введите все значения в форму");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("УПС...ОШИБОЧКА");
          alert.setHeaderText("Недостачно данных для просчета");
            alert.setContentText("Введите все значения в форму");
            alert.showAndWait();
        }

        }


    @FXML
    public void addToDB (ActionEvent e){
      try{
        Date currentDate = new Date();
        String date = currentDate.getDate();
        Integer heightC = Integer.parseInt(height.getValue().toString());
        Integer lengthC = Integer.parseInt(length.getValue().toString());
        Integer widthC = Integer.parseInt(width.getValue().toString());
        Integer doorsC = Integer.parseInt(doorsAmount.getValue().toString());
        String flakeboardC = flakeboard.getValue().toString();
        String facadeC = facadeComboBox.getValue().toString();
          Integer marginC = Integer.parseInt(margin.getValue().toString());
          String currency = currencyView.getText();
          String price = totalPriceSum.getText();


          Wardrobe wardrobe12 = new Wardrobe(date,heightC,lengthC,widthC,
                  doorsC,flakeboardC,facadeC,marginC,currency, price);

        String name= customerName.getText();
        String phone= customerPhone.getText();
        String adress= customerAdress.getText();
        model.createCustomer(name, phone, adress, wardrobe12);
      }catch (NullPointerException ex){

          ex.printStackTrace();
          System.out.println("Недостачно данных для добавления в базу");
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.initOwner(dialogStage);
          alert.setTitle("УПС...ОШИБОЧКА");
          alert.setHeaderText("Недостачно данных для добавления в базу");
          alert.setContentText("Введите все значения в форму для просчета и данные о заказчике");
          alert.showAndWait();
      }

    }

    public void setFields() {

        for (int i = wardrobe.MIN_HEIHT; i <= wardrobe.MAX_HEIGHT; i+= 50) {
            height.getItems().add(i);
        }
        for (int i = wardrobe.MIN_LENGTH; i <= wardrobe.MAX_LENGTH; i = i + 50) {
            length.getItems().add(i);
        }
        for (int i = wardrobe.MIN_WIDTH; i <= wardrobe.MAX_WIDTH; i = i + 50) {
            width.getItems().add(i);
        }
        doorsAmount.getItems().addAll("2", "3", "4", "5", "6", "7");
        for (int i = wardrobe.MIN_SHELF_AMOUNT; i <= wardrobe.MAX_SHELF_AMOUNT; i++) {
            shelf.getItems().add(i);
        }

        rod.getItems().addAll("0","1", "2", "3", "4", "5");
        pantograph.getItems().addAll("0","1", "2", "3");
        hanger.getItems().addAll("0", "1", "2", "3");

        for (int i = wardrobe.MIN_BOX_AMOUNT; i <= wardrobe.MAX_BOX_AMOUNT; i++) {
            box.getItems().add(i);
        }

        for (String s : model.showFlakeboard()) {
            flakeboard.getItems().add(s);
        }

        for (String s : model.showFacade()) {
            facadeComboBox.getItems().add(s);
        }


        for (Integer s : model.showMargin()) {
            margin.getItems().add(s);
        }

    }

    @FXML
    public void showArchive (ActionEvent e){
        try {
            model.openMySQLWorkbench();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Неверно указан путь");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("УПС...ОШИБОЧКА");
            alert.setHeaderText("Нет права доступа");
            alert.setContentText("Отказ в доступе - обратитесь к системному администратору");
            alert.showAndWait();
        }

        }


    @FXML
    public void openCalc(ActionEvent e) {
        model.openCalc();
    }

    @FXML
    public void getUSDRate(ActionEvent e){
        currencyView.setText(model.setRate());

    }


}
