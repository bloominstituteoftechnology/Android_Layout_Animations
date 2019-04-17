package com.lambda.android_layout_animations;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemsList implements Serializable {
    private ArrayList<Item> alitemList=new ArrayList<Item>(100);
    private Item itemCurrent;

    public ItemsList(ArrayList<Item> alitemList) {

        this.alitemList = alitemList;
        itemCurrent=alitemList.get(alitemList.size()-1 );
    }
    public ArrayList<Item> toArrayList(){
        return alitemList;
    }

    public Item getCurrentItem(){
            return itemCurrent;
    }

    public ItemsList(Item item) {

        this.alitemList.add( item);
        itemCurrent=item;
    }
    public Item get(int iIndex){
        return alitemList.get( iIndex );
    }

    public ItemsList(String strID){

    }
    public void add(Item item){
        alitemList.add( item );
        return;
    }
    public Item getInitialItem(){
        return alitemList.get( 0 );

    }
    public Item getNextItem(int iIndex){
        return alitemList.get( iIndex+1 );
    }

    public String getStringAllIndex(){

        String strAllIndex="";
        for(int i=0;i<size();i++){
            strAllIndex+= alitemList.get( i ).getiID();
            if(i!=size()-1)strAllIndex+=",";
        }

        return strAllIndex;
    }
    public int size(){
        return alitemList.size();
    }
    public Item findItemByName(String strName){
        for(int i=0;i<size();i++){
            if(alitemList.get( i ).getStrName().equals( strName )){
                return alitemList.get( i );
            }

        }
        return null;
    }
    public String getListOfItemsToShop(){
        String strChosen="";
        if(size()==0)return "";
        for (int i=0;i<size();i++){
            if(alitemList.get( i ).isbToShop())strChosen+=alitemList.get( i ).getStrName()+",";
        }
        return strChosen.substring( 0,strChosen.length()-1 );
    }
    public ItemsList reset(){
        for(int i=0;i<size();i++){
            alitemList.get( i ).setbToShop( false );
        }
        return this;
    }

    public ItemsList getChoosen(){

        ItemsList ilChoosen=null;
        for(int i=0;i<size();i++){
            if(alitemList.get(i).isbToShop()){
                if(ilChoosen==null){
                    ilChoosen=new ItemsList( alitemList.get(i) );

                }else{
                    ilChoosen.add( alitemList.get(i) );
                }
            }

        }
        return ilChoosen;
    }

}
