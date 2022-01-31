/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@Log4j2
public class ConfigYaml {
    private String ruta;
    private String database;

    public ConfigYaml() {
        try {
            Yaml yaml = new Yaml();
            Iterable<Object> it;

            it = yaml.loadAll(new FileInputStream("config/store-properties.yml"));

            Map<String, String> m = (Map) it.iterator().next();

            this.ruta = m.get("database");
            this.database = m.get("databaseName");


        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    




    
}
