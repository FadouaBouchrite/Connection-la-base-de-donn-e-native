package com.example.jdbc.controllers;

import com.example.jdbc.entities.Produit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

@RestController
public class ProduitController {
@GetMapping("/getProducts")
    public List<Produit> getProducts() throws ClassNotFoundException, SQLException {
    //declarer une liste qui va contenir les produits
    List<Produit> produits=new ArrayList<Produit>();
    //importer la classe driver dans le contexte de l'applicaton
    Class.forName("com.mysql.jdbc.Driver");
    //etablir une connection avec la base de donnée
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_CAT","root","");
    PreparedStatement ps=con.prepareStatement("SELECT * from PRODUITS where designation like ?");
    ps.setString(1,"medical");
    ResultSet rs=ps.executeQuery();
    while (rs.next()){

        Produit p=new Produit();
        p.setId(rs.getString("id"));
        p.setPrix(rs.getDouble("prix"));
        p.setDesignation(rs.getString("designation"));
        produits.add(p);

    }
return produits;
}


}
