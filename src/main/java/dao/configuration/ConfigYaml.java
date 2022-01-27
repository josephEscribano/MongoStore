/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.configuration;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class ConfigYaml {
    
    private String ruta;
    private String database;
    
    private static ConfigYaml config;

    private ConfigYaml() {
        
    }
    
    public static ConfigYaml getInstance() {
        if (config == null) {
            try {
                Yaml yaml = new Yaml();
                InputStream in = Files.newInputStream(Paths.get("config/store-properties.yml"));
                config = (ConfigYaml) yaml.loadAs(in, ConfigYaml.class);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConfigYaml.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConfigYaml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return config;
    }



    
}
