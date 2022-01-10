/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HpManager;

import databases.Koneksi;
import modul.HpModul;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class HpManager {
    
    private final Connection koneksiDatabase;

    public HpManager() {
        this.koneksiDatabase = Koneksi.koneksiDB();
    }
    
    public List<HpModul> tampilSemua(){
        String namaTable = "spesifikasi";
        String query = "SELECT * FROM "+namaTable;
        HpModul modul;
        List<HpModul> list = new ArrayList<>();
        try {
            Statement preparedStatement = koneksiDatabase.createStatement();
            ResultSet hasilQuery = preparedStatement.executeQuery(query);
            
            while(hasilQuery.next()){
                modul = new HpModul();
                modul.setId(hasilQuery.getInt("id"));
                modul.setBrand(hasilQuery.getString("brand"));
                modul.setKapasitas_batrai(hasilQuery.getString("kapasitas_batrai"));
                modul.setMemori(hasilQuery.getString("memori"));
                modul.setJenis_OS(hasilQuery.getString("jenis_OS"));
                list.add(modul);
            }            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(HpManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public HpModul byId(int id){
        String namaTable = "spesifikasi";
        String query = "SELECT * FROM "+namaTable+" WHERE id = "+id;
        HpModul modul = null;
        try {
            Statement preparedStatement = koneksiDatabase.createStatement();
            ResultSet hasilQuery = preparedStatement.executeQuery(query);
            
            while(hasilQuery.next()){
                modul = new HpModul();
                modul.setId(hasilQuery.getInt("id"));
                modul.setBrand(hasilQuery.getString("brand"));
                modul.setKapasitas_batrai(hasilQuery.getString("kapasitas_batrai"));
                modul.setMemori(hasilQuery.getString("memori"));
                modul.setJenis_OS(hasilQuery.getString("jenis_OS"));
            }            
            return modul;
        } catch (SQLException ex) {
            Logger.getLogger(HpManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }    
    
    public boolean insert(String brand, String kapasitas_batrai, String memori, String jenis_OS) {
        String namaTable = "spesifikasi";
        String query = "INSERT INTO "+namaTable+" (`brand`, `kapasitas_batrai`, `memori`, `jenis_OS`) VALUES ('"+brand+"', '"+kapasitas_batrai+"','"+memori+"', '"+jenis_OS+"');";
        
        try {
            PreparedStatement preparedStatement = koneksiDatabase.prepareStatement(query);
            preparedStatement.execute();   
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HpManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean update(String brand, String kapasitas_batrai, String memori, String jenis_OS, int id) {
        String namaTable = "spesifikasi";
        String query = "UPDATE "+namaTable+" SET `brand`='"+brand+"', `kapasitas_batrai`='"+kapasitas_batrai+"', `memori`='"+memori+"', `jenis_OS`='"+jenis_OS+"'  WHERE  `id`="+id+";";
        
        try {
            PreparedStatement preparedStatement = koneksiDatabase.prepareStatement(query);
            preparedStatement.execute();   
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HpManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean delete(String id) {
        String namaTable = "spesifikasi";
        String query = "DELETE FROM "+namaTable+" WHERE id = "+id;
        
        try {
            PreparedStatement preparedStatement = koneksiDatabase.prepareStatement(query);
            preparedStatement.execute();   
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HpManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
