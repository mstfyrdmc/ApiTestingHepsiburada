package Tests;

import Model.GroceryModel;
import com.thoughtworks.gauge.Step;

public class ApiTest {
    GroceryModel groceryModel=new GroceryModel();

    @Step("Urun Adi: <key>, urun fiyati: <key>,Stok Durumu: <key> olan ürün olduğu kontrol edilir.")
    public void checkProduct(String productName,int price,int unitsInStock){
        groceryModel.checkProductNamePriceAndStock(productName,price,unitsInStock);
    }

    @Step("Apideki ürünlerin varlığı kontrol edilir.")
    public void checkAllProductControl(){
        groceryModel.checkAllProductFromList();
    }

    @Step("Urun idsi: <key>, urun adi: <key>, urun fiyati: <key>,stok durumu <key> özellikli ürün eklenir.")
    public void postProduct(int id,String productName,int price,int unitsInStock){
        groceryModel.postProductWithIdNamePriceAndStock(id, productName,price,unitsInStock);
    }
}
