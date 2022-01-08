package by.bsuir.psp_6.view;

import by.bsuir.psp_6.model.ClothesItem;
import by.bsuir.psp_6.service.ClothesItemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ClothesItemView implements Serializable {

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

  @Autowired
  private ClothesItemService clothesItemService;

  @FXML
  private TableView<ClothesItem> table;

  TableColumn<ClothesItem, Integer> size;

  @FXML
  private TextField tfName;

  @FXML
  private TextField tfArticular;

  @FXML
  private TextField tfModel;

  @FXML
  private TextField tfCreator;

  @FXML
  private TextField tfColor;

  @FXML
  private TextField tfSize;

  @FXML
  private TextField tfCreationDate;

  @FXML
  private TextField tfPrice;

  @FXML
  private TextField tfNameUpdate;

  @FXML
  private TextField tfArticularUpdate;

  @FXML
  private TextField tfModelUpdate;

  @FXML
  private TextField tfCreatorUpdate;

  @FXML
  private TextField tfColorUpdate;

  @FXML
  private TextField tfSizeUpdate;

  @FXML
  private TextField tfCreationDateUpdate;

  @FXML
  private TextField tfPriceUpdate;

  @FXML
  private TextField tfNameSearch;

  private ObservableList<ClothesItem> data;

  private ObservableList<ClothesItem> cacheData;

  @FXML
  public void initialize() {}

  @PostConstruct
  public void init() {
    List<ClothesItem> items = new ArrayList<>(clothesItemService.findAll().values());
    data = FXCollections.observableArrayList(items);

    TableColumn<ClothesItem, String> id = new TableColumn<>("id");
    id.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<ClothesItem, String> name = new TableColumn<>("name");
    name.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<ClothesItem, String> articular = new TableColumn<>("articular");
    articular.setCellValueFactory(new PropertyValueFactory<>("articular"));

    TableColumn<ClothesItem, String> model = new TableColumn<>("model");
    model.setCellValueFactory(new PropertyValueFactory<>("model"));

    TableColumn<ClothesItem, String> creator = new TableColumn<>("creator");
    creator.setCellValueFactory(new PropertyValueFactory<>("creator"));

    TableColumn<ClothesItem, String> color = new TableColumn<>("color");
    color.setCellValueFactory(new PropertyValueFactory<>("color"));

    size = new TableColumn<>("size");
    size.setCellValueFactory(new PropertyValueFactory<>("size"));

    TableColumn<ClothesItem, Date> creationDate = new TableColumn<>("creationDate");
    creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

    TableColumn<ClothesItem, Integer> price = new TableColumn<>("price");
    price.setCellValueFactory(new PropertyValueFactory<>("price"));

    table.getColumns().setAll(id, name, articular, model, creator, color, size, creationDate, price);

    table.setItems(data);
  }

  @FXML
  public void searchByName() {
    String name = tfNameSearch.getText();

    List<ClothesItem> searchedData = new ArrayList<>(clothesItemService.findAll().values())
        .stream()
        .filter(clothesItem -> clothesItem.getName().equals(name))
        .collect(Collectors.toList());

    data = FXCollections.observableArrayList(searchedData);
    table.setItems(data);

    cleanInputFields();
  }

  @FXML
  public void clean() {
    List<ClothesItem> data = new ArrayList<>(clothesItemService.findAll().values());
    table.setItems(FXCollections.observableArrayList(data));
    cleanInputFields();
  }

  @FXML
  public void sortBySize() {
    table.getSortOrder().add(size);
    cleanInputFields();
  }

  @SneakyThrows
  @FXML
  public void addItem() {
    String name = tfName.getText();
    String articular = tfArticular.getText();
    String model = tfModel.getText();
    String creator = tfCreator.getText();
    String color = tfColor.getText();
    int size = Integer.parseInt(tfSize.getText());
    Date creationDate = new SimpleDateFormat("dd.MM.yyyy").parse(tfCreationDate.getText());
    int price = Integer.parseInt(tfPrice.getText());

    ClothesItem clothesItem = ClothesItem.builder()
        .name(name)
        .articular(articular)
        .model(model)
        .creator(creator)
        .color(color)
        .size(size)
        .creationDate(creationDate)
        .price(price)
        .build();

    clothesItemService.save(clothesItem);
    data.add(clothesItem);

    cleanInputFields();
  }

  @SneakyThrows
  @FXML
  public void updateItem() {
    ClothesItem selectedItem = table.getSelectionModel().getSelectedItem();
    data.remove(selectedItem);

    selectedItem.setName(tfNameUpdate.getText());
    selectedItem.setArticular(tfArticularUpdate.getText());
    selectedItem.setModel(tfModelUpdate.getText());
    selectedItem.setCreator(tfCreatorUpdate.getText());
    selectedItem.setColor(tfColorUpdate.getText());
    selectedItem.setSize(Integer.parseInt(tfSizeUpdate.getText()));
    selectedItem.setCreationDate(simpleDateFormat.parse(tfCreationDateUpdate.getText()));
    selectedItem.setPrice(Integer.parseInt(tfPriceUpdate.getText()));

    data.add(selectedItem);
    clothesItemService.save(selectedItem);
    table.setItems(data);
    cleanInputFields();
  }

  @FXML
  public void deleteItem() {
    ClothesItem selectedItem = table.getSelectionModel().getSelectedItem();
    data.remove(selectedItem);
  }

  private void cleanInputFields() {
    tfNameSearch.setText("");

    tfName.setText("");
    tfArticular.setText("");
    tfModel.setText("");
    tfCreator.setText("");
    tfColor.setText("");
    tfSize.setText("");
    tfCreationDate.setText("");
    tfPrice.setText("");

    tfNameUpdate.setText("");
    tfArticularUpdate.setText("");
    tfModelUpdate.setText("");
    tfCreatorUpdate.setText("");
    tfColorUpdate.setText("");
    tfSizeUpdate.setText("");
    tfCreationDateUpdate.setText("");
    tfPriceUpdate.setText("");
  }

  @FXML
  public void selectItem() {
    ClothesItem selectedItem = table.getSelectionModel().getSelectedItem();
    if(selectedItem != null) {
      tfNameUpdate.setText(selectedItem.getName());
      tfArticularUpdate.setText(selectedItem.getArticular());
      tfModelUpdate.setText(selectedItem.getModel());
      tfCreatorUpdate.setText(selectedItem.getCreator());
      tfColorUpdate.setText(selectedItem.getColor());
      tfSizeUpdate.setText(String.valueOf(selectedItem.getSize()));
      tfCreationDateUpdate.setText(simpleDateFormat.format((selectedItem.getCreationDate())));
      tfPriceUpdate.setText(String.valueOf(selectedItem.getPrice()));
    }
  }
}
